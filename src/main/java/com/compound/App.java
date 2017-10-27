package com.compound;


import com.compound.request.json.alpha.vantage.AlphaVantageJson;
import com.compound.request.url.AlphaVantageUrl;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;

/**
 * Hello world!
 *
 */
public class App 
{
  static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  static final JsonFactory JSON_FACTORY = new GsonFactory();


  public static void main( String[] args ) throws Exception{
    HttpRequestFactory requestFactory =
        HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
          @Override
          public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));
          }
        });
    AlphaVantageUrl url = new AlphaVantageUrl("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=MSFT&apikey=F3B3RCENZP5N8YWC");

    HttpRequest request = requestFactory.buildGetRequest(url);
    HttpResponse response = request.execute();
    AlphaVantageJson json = response.parseAs(AlphaVantageJson.class);
    System.out.println(json);
  }
}
