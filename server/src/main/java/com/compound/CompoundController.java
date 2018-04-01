package com.compound;

import com.compound.request.json.error.JsonErrorMessage;
import com.compound.service.CompoundStatsService;
import com.compound.service.YearOverYearService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CompoundController {
  static Logger logger = Logger.getLogger(CompoundController.class);

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/year-over-year/stock")
  public Object stock(@RequestParam(defaultValue="ALPHA_VANTAGE") String type,
                      @RequestParam() String symbol,
                      @RequestParam(defaultValue="TIME_SERIES_WEEKLY") String function){
    YearOverYearService yearOverYearService = new YearOverYearService();
    return yearOverYearService.buildRequest(type,symbol,function);
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/compound/stats")
  public Object stats(@RequestParam(defaultValue="ALPHA_VANTAGE") String type,
                      @RequestParam() String symbol,
                      @RequestParam(defaultValue="TIME_SERIES_WEEKLY") String function){

    CompoundStatsService compoundStatsService = new CompoundStatsService();
    try {
      return compoundStatsService.buildRequest(type,symbol,function);
    }catch(NumberFormatException nfe){
      logger.error("Number Format Exception while building request for:" +
          " type[" + type + "], symbol["+symbol+"], function["+function+"]");
      return new JsonErrorMessage("Number Formatting Error");
    }catch (IOException e) {
      logger.error("Error opening CSV file.");
      return new JsonErrorMessage("IO Error");
    }
  }

}
