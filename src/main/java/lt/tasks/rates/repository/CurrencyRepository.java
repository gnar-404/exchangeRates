package lt.tasks.rates.repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRepository implements DataDownloader {
    @Autowired
    private Environment environment;

     public InputStream download(String baseCurrency,String targetCurrency, String startDate, String endDate) throws IOException {

        String url =environment.getProperty("lithuanian.bank.getFxRatesForCurrency.url")
                +"?tp="
                + baseCurrency
                + "&ccy="
                + targetCurrency
                + "&dtFrom="
                + startDate
                + "&dtTo="
                + endDate;

        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.addRequestProperty("Accept", "application/xml");

        return urlConnection.getInputStream();
    }
}
