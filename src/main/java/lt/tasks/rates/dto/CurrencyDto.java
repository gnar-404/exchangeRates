package lt.tasks.rates.dto;

import java.util.Date;
import java.util.List;

public class CurrencyDto {
    // FIXME add validations
    // NOTE range is just a list of dates
    private List<Date> dates;
    private List<String> currencies;
    private FileFormat fileFormat;

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public FileFormat getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(FileFormat fileFormat) {
        this.fileFormat = fileFormat;
    }
}
