package lt.tasks.rates.repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;

@Component
public class CurrencyRepository implements DataDownloader {
     public InputStream download(String baseCurrency,String targetCurrency, String startDate, String endDate) throws IOException {

     // FIXME extract base url to config variable
        String url ="http://www.lb.lt/webservices/fxrates/FxRates.asmx/getFxRatesForCurrency?tp="
                + baseCurrency
                + "&ccy="
                + targetCurrency
                + "&dtFrom="
                + startDate
                + "&dtTo="
                + endDate;
        //FIXME
         // Check spring webclient
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.addRequestProperty("Accept", "application/xml");

        return urlConnection.getInputStream();
    }
}
