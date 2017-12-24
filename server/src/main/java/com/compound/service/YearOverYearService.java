package com.compound.service;

import com.compound.request.builder.RequestBuilder;
import com.compound.request.builder.stock.yoy.YearOverYearRequestBuilderFactory;
import com.compound.request.json.error.JsonErrorMessage;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;

public class YearOverYearService {

  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = new GsonFactory();
  private static final YearOverYearRequestBuilderFactory REQUEST_BUILDER_FACTORY = new YearOverYearRequestBuilderFactory();

  public Object buildRequest(String type, String symbol, String function ){
    HttpRequestFactory requestFactory =
        HTTP_TRANSPORT.createRequestFactory(request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));

    RequestBuilder requestBuilder = REQUEST_BUILDER_FACTORY.getRequestBuilder(type, function, symbol);
    // The builder is taking care of what this should be casted too.
    Object json;
    try {
      HttpRequest request = requestFactory.buildGetRequest(requestBuilder.getUrl());
      HttpResponse response = request.execute();
      json = response.parseAs(requestBuilder.getJsonRoot().getClass());

      json = requestBuilder.buildRequest(json);

    } catch (Exception e) {
      e.printStackTrace();
      json = new JsonErrorMessage("Error parsing request.");
    }
    return json;
  }
}
