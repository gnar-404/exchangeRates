package lt.tasks.rates;

import com.opencsv.bean.CsvBindByPosition;

import java.text.ParseException;

public class Currency {

    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private String currencyCode;

    @CsvBindByPosition(position = 2)
    private String ratio;

    @CsvBindByPosition(position = 3)
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    // FIXME make object return BigDecimal
    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    // FIXME make object return Date
    public String getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        this.date = date;
    }
}
