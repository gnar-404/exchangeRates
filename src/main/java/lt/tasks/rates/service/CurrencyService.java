package lt.tasks.rates.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lt.tasks.rates.Currency;
import lt.tasks.rates.dto.CurrencyDto;
import lt.tasks.rates.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;
    
    private InputStreamResource buildCSV() throws IOException {
        // FIXME has to return file resource from downloaded data to later put it to response entity
        String filename = "tutorials.csv";
        URL xxx = new URL("https://google.com");
        InputStreamResource file = new InputStreamResource(xxx.openStream());
        return file;
    }
    
//
//    private List<?> getCurrencies(){
//        return new ArrayList<>();
//    }

    private List<Currency> readCsvFile(InputStream inputStream){
        BufferedReader csvInputBuffer = new BufferedReader(new InputStreamReader(inputStream));

        List<lt.tasks.rates.Currency> currencies = new CsvToBeanBuilder(csvInputBuffer)
                .withType(lt.tasks.rates.Currency.class)
                .withSeparator(';')
                .build()
                .parse();

        // 0 index is stores column names
        return currencies.subList(1,currencies.size());
    }

    // Single date download   |
    // Date range download    |
    // ratio comparison for single and range date
    // all must be filterable
    public ByteArrayInputStream getCurrenciesFile(CurrencyDto currencyDto) throws IOException {
        List<Currency> csvContents = readCsvFile(currencyRepository.download("https://www.lb.lt/lt/currency/daylyexport/?csv=1&class=Eu&type=day&date_day=2021-04-13"));



        List<Currency> file = currencyRepository.download("https://www.lb.lt/lt/currency/daylyexport/?csv=1&class=Eu&type=day&date_day=2021-04-13");
        String testString = "miau, mur, pur";

        ByteArrayInputStream xxx = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
        return xxx;
    }
}
