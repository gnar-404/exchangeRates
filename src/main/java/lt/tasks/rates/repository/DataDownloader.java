package lt.tasks.rates.repository;

import java.io.IOException;
import java.io.InputStream;

public interface DataDownloader {
    public InputStream download(String url) throws IOException;
}
