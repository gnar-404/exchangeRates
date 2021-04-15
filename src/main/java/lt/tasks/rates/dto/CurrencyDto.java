package lt.tasks.rates.dto;

import java.util.*;


public class CurrencyDto {

    private TreeSet<String> dates;

    private String baseCurrency;

    private List<String> targetCurrencies;

    public CurrencyDto(String baseCurrency, List<String> targetCurrencies, TreeSet<String> dates) {
        validate(baseCurrency, targetCurrencies, dates);
        this.dates = dates;
        this.baseCurrency = baseCurrency;
        this.targetCurrencies = targetCurrencies;
    }

    public TreeSet<String> getDates() {
        return dates;
    }

    public List<String> getTargetCurrencies() {
        return targetCurrencies;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    private void validate(String baseCurrency, List<String> targetCurrencies, TreeSet<String> dates) {
        if (!baseCurrency.matches("^[A-Z]{2}")) {
            throw new RuntimeException("Wrong base currency pattern.");
        }

        targetCurrencies.forEach(currency -> {
            if(!currency.matches("^[A-Z]{3}")){
                throw new RuntimeException("Wrong target currency pattern.");
            }
        });

        dates.forEach(date -> {
            if(!date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}")){
                throw new RuntimeException("Wrong date pattern.");
            }
        });


    }
}
