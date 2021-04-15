package lt.tasks.rates.repository;

import java.io.IOException;
import java.io.InputStream;

public interface DataDownloader {
    InputStream download(String baseCurrency,String targetCurrency, String startDate, String endDate) throws IOException;
}
