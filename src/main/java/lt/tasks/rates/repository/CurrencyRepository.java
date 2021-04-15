package lt.tasks.rates.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import lt.tasks.rates.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRepository implements DataDownloader {
    // FIXME sample url
    // Has to come from front end
    // private static final String GET_API_URL = "https://www.lb.lt/lt/currency/daylyexport/?csv=1&class=Eu&type=day&date_day=2021-04-13";
//    @Override
//    public List<Currency> download(String url) throws IOException {
//        URL downloadUrl = new URL(url);
//        BufferedReader csvInputBuffer = new BufferedReader(new InputStreamReader(downloadUrl.openStream()));
//
//        List<lt.tasks.rates.Currency> currencies = new CsvToBeanBuilder(csvInputBuffer)
//                .withType(lt.tasks.rates.Currency.class)
//                .withSeparator(';')
//                .build()
//                .parse();
//
//        // 0 index is stores column names
//        return currencies.subList(1,currencies.size());
//    }


    @Override
    public InputStream download(String url) throws IOException {
        URL downloadUrl = new URL(url);
        return downloadUrl.openStream();
    }
}
