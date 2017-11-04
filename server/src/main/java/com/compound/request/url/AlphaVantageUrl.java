package com.compound.request.url;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class AlphaVantageUrl extends GenericUrl {

  private static final String URL = "https://www.alphavantage.co/query?";
  private static final String API_KEY = "F3B3RCENZP5N8YWC";
  public AlphaVantageUrl(){
    super(URL);
    put("apikey", API_KEY);
  }

}
