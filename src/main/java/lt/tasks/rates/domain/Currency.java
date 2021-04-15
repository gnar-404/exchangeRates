package lt.tasks.rates.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.math.BigDecimal;

@JacksonXmlRootElement(namespace = "CcyAmt")
public class Currency {

    @JacksonXmlProperty(localName = "Ccy")
    private String currency;

    @JacksonXmlProperty(localName = "Amt")
    private BigDecimal currencyAmount;

    public Currency() {
        // Required by JacksonXml
    }

    public Currency(String currency, BigDecimal currencyAmount) {
        this.currency = currency;
        this.currencyAmount = currencyAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }
}
