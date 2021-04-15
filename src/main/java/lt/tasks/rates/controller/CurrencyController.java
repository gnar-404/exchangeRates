package lt.tasks.rates.controller;

import io.swagger.annotations.ApiOperation;
import lt.tasks.rates.dto.CurrencyDto;
import lt.tasks.rates.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.TreeSet;


@RestController
@RequestMapping("/api/download")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/csv")
    @ApiOperation(value = "Returns file containing currency changes", notes = "Returns file containing currency changes")
    public ResponseEntity getCurrencies(@RequestParam String baseCurrency, @RequestParam List<String> currencies, @RequestParam TreeSet<String> dates){
        CurrencyDto currencyDto = new CurrencyDto(baseCurrency, currencies, dates);
        try {
            InputStreamResource file = currencyService.getFile(currencyDto);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "currencies.csv")
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(file);
        }
        catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
