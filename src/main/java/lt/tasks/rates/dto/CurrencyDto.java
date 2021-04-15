package lt.tasks.rates.dto;

import java.util.*;

public class CurrencyDto {
    // FIXME add validations
    // NOTE range is just a list of dates
    // Must be at least one
    // MUST NOT BE EMPTY STRINGS
    // CHECK WITH REGEX
    private TreeSet<String> dates;
    // Base currency type
    // Must exist
    // MUST NOT BE EMPTY STRINGS
    private String baseCurrency;
    // Currency codes to get data of
    // Must be at least one
    // MUST NOT BE EMPTY STRINGS
    // CHECK WITH REGEX IF ONLY THREE LETTERS IN  CURRENCY CODE
    private List<String> targetCurrencies;

    public CurrencyDto() {
    }

    public CurrencyDto(String baseCurrency, List<String> targetCurrencies, TreeSet<String> dates) {
        this.dates = dates;
        this.baseCurrency = baseCurrency;
        this.targetCurrencies = targetCurrencies;
    }

    public TreeSet<String> getDates() {
        return dates;
    }

    public void setDates(TreeSet<String> dates) {
        this.dates = dates;
    }

    public List<String> getTargetCurrencies() {
        return targetCurrencies;
    }

    public void setTargetCurrencies(List<String> targetCurrencies) {
        this.targetCurrencies = targetCurrencies;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
}
