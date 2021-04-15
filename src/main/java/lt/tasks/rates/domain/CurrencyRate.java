package lt.tasks.rates.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@JacksonXmlRootElement(namespace = "FxRate")
public class CurrencyRate  {
    // FIXME turn to enum
    // tp
    @JacksonXmlProperty(localName = "Tp")
    private String currency;

    @JacksonXmlProperty(localName = "Dt")
    private String date;

    @JacksonXmlProperty(localName = "CcyAmt")
    @JacksonXmlCData
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Currency> currencies;

    public CurrencyRate() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = dateToString(date);
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    private String dateToString(Date date){
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);

        return sdf.format(date);
    }
}
