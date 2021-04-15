package lt.tasks.rates.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class CurrencyRates {
    @JacksonXmlProperty(localName = "FxRate")
    @JacksonXmlCData
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CurrencyRate> currencyRates;

    public CurrencyRates() {
    }

    public CurrencyRates(List<CurrencyRate> currencyRates) {
        this.currencyRates = currencyRates;
    }

    public List<CurrencyRate> getCurrencyRates() {
        return currencyRates;
    }

    public void setCurrencyRates(List<CurrencyRate> currencyRates) {
        this.currencyRates = currencyRates;
    }
}
