package lt.tasks.rates.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lt.tasks.rates.domain.CurrencyRate;
import lt.tasks.rates.domain.CurrencyRates;
import lt.tasks.rates.dto.CurrencyDto;
import lt.tasks.rates.exception.NoDataFoundException;
import lt.tasks.rates.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<CurrencyRates> getCurrencyRates(CurrencyDto currencyDto) {
        List<CurrencyRates> currencyRates = new ArrayList<>();

        // Taking ranges to avoid need for weekend filtering
        currencyDto.getTargetCurrencies().stream().forEach(targetCurrency -> {
            try {

                InputStream file = currencyRepository.download(currencyDto.getBaseCurrency(), targetCurrency, currencyDto.getDates().first(), currencyDto.getDates().last());
                CurrencyRates rates = mapXml(file);

                rates.setCurrencyRates(filterCurrencyRatesByDate(rates, currencyDto.getDates()));

                currencyRates.add(rates);

            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }

        });
        return currencyRates;
    }

    public String getFile(CurrencyDto currencyDto) {
        List<CurrencyRates> currencyRates = getCurrencyRates(currencyDto);
        return buildCSV(currencyRates);
    }

    public String buildCSV(List<CurrencyRates> currencyRates) {
        List<String> headerRow = buildCsvHeader(currencyRates.get(0));
        List<List<String>> entryRows = buildCsvEntries(currencyRates);

        StringBuilder sb = new StringBuilder();
        sb.append(String.join(",", headerRow));
        sb.append("\n");

        for (List<String> entryRow : entryRows) {
            sb.append(String.join(",", entryRow));
            sb.append("\n");
        }

        return sb.toString();

    }

    private CurrencyRates mapXml(InputStream file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(file, CurrencyRates.class);
    }

    private List<CurrencyRate> filterCurrencyRatesByDate(CurrencyRates currencyRates, SortedSet<String> dates) {
        List<CurrencyRate> rates = currencyRates
                .getCurrencyRates()
                .stream()
                .filter(currencyRate -> dates.contains(currencyRate.getDate()))
                .collect(Collectors.toList());
        if(rates.isEmpty()){
            throw new NoDataFoundException();
        } else {
            return rates;
        }
    }

    private List<String> buildCsvHeader(CurrencyRates baseRates) {

        List<String> headerLine = new ArrayList<>();

        headerLine.add("Base currency");
        headerLine.add("Target currency");
        // Create existing date columns
        for (CurrencyRate rate : baseRates.getCurrencyRates()) {
            headerLine.add(rate.getDate());
        }

        headerLine.add("Value Change ( Units )");

        return headerLine;
    }

    private BigDecimal calculateCurrencyRateChange(CurrencyRates currencyRates) {
        BigDecimal oldestValue = currencyRates.getCurrencyRates().get(0).getCurrencies().get(1).getCurrencyAmount();
        BigDecimal latestValue = currencyRates.getCurrencyRates().get(currencyRates.getCurrencyRates().size() - 1).getCurrencies().get(1).getCurrencyAmount();

        return oldestValue.subtract(latestValue);
    }

    private List<List<String>> buildCsvEntries(List<CurrencyRates> currencyRates) {
        List<List<String>> entryLines = new ArrayList<>();
        for (CurrencyRates entry : currencyRates) {
            List<String> entryLine = new ArrayList<>();

            String baseCurrencyName = entry.getCurrencyRates().get(0).getCurrency();
            entryLine.add(baseCurrencyName);

            String targetCurrencyName = entry.getCurrencyRates().get(0).getCurrencies().get(1).getCurrency();
            entryLine.add(targetCurrencyName);

            for (CurrencyRate rate : entry.getCurrencyRates()) {
                entryLine.add(rate.getCurrencies().get(1).getCurrencyAmount().toString());
            }

            // Value difference
            entryLine.add(calculateCurrencyRateChange(entry).toString());

            entryLines.add(entryLine);
        }
        return entryLines;
    }

}