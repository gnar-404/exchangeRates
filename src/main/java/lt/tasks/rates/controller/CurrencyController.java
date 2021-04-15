package lt.tasks.rates.controller;

import io.swagger.annotations.ApiOperation;
import lt.tasks.rates.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/download")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/csv", method = RequestMethod.GET)
//    // FIXME uncomment after adding swagger deps
    @ApiOperation(value = "Creates a new address", notes = "Creates a new address")
    public ResponseEntity getCurrencies() {

        ByteArrayInputStream currencies = currencyService.getCurrenciesFile();
        InputStreamResource file = new InputStreamResource(currencies);

        // FIXME should I pass it as env variable?
        String filename = "currencies.csv";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
//    }
    }


//    @RequestMapping(value = "/csv", method = RequestMethod.GET)
////    // FIXME uncomment after adding swagger deps
//    @ApiOperation(value = "Creates a new address", notes = "Creates a new address")
//    public ResponseEntity getCurrencies(@RequestBody CurrencyDto currencyDownloadRequestDto) {
//
//        ByteArrayInputStream currencies = currencyService.getCurrenciesFile(currencyDownloadRequestDto);
//        InputStreamResource file = new InputStreamResource(currencies);
//
//        // FIXME should I pass it as env variable?
//        String filename = "currencies.csv";
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//                .contentType(MediaType.parseMediaType("application/csv"))
//                .body(file);
////    }
//    }
}
