package com.compound;

import com.compound.request.json.alpha.vantage.AlphaVantageJson;
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
  static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  static final JsonFactory JSON_FACTORY = new GsonFactory();

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/stock")
  public AlphaVantageStock stock(@RequestParam(required = true, defaultValue="MSFT") String stock){
    HttpRequestFactory requestFactory =
        HTTP_TRANSPORT.createRequestFactory(request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));
    AlphaVantageUrl url = new AlphaVantageUrl("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=MSFT&apikey=F3B3RCENZP5N8YWC");

    AlphaVantageJson json = null;
    try {
      HttpRequest request = requestFactory.buildGetRequest(url);
      HttpResponse response = request.execute();
      json = response.parseAs(AlphaVantageJson.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return json.weeklyData.values().iterator().next();
  }

}
