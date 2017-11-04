package com.compound;

import com.compound.request.builder.RequestBuilder;
import com.compound.request.builder.alpha.vantage.AlphaVantageRequestBuilder;
import com.compound.request.builder.stock.yoy.YearOverYearRequestBuilderFactory;
import com.compound.request.json.alpha.vantage.AlphaVantageJson;
import com.compound.request.json.alpha.vantage.AlphaVantageJsonWeekly;
import com.compound.request.json.alpha.vantage.AlphaVantageStock;
import com.compound.request.url.AlphaVantageUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CompoundController {
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = new GsonFactory();
  private static final YearOverYearRequestBuilderFactory REQUEST_BUILDER_FACTORY = new YearOverYearRequestBuilderFactory();
  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/stock/year-over-year/")
  public Object stock(@RequestParam(defaultValue="ALPHA_VANTAGE") String type,
                                @RequestParam() String symbol,
                                @RequestParam(defaultValue="TIME_SERIES_WEEKLY") String function){
    HttpRequestFactory requestFactory =
        HTTP_TRANSPORT.createRequestFactory(request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));

    RequestBuilder requestBuilder = REQUEST_BUILDER_FACTORY.getRequestBuilder(type, function, symbol);
    Object json = null;
    try {
      HttpRequest request = requestFactory.buildGetRequest(requestBuilder.getUrl());
      HttpResponse response = request.execute();
      json = response.parseAs(requestBuilder.getJsonRoot());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return json;
  }

}
