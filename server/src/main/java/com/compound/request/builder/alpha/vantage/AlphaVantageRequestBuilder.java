package com.compound.request.builder.alpha.vantage;

import com.compound.request.builder.RequestBuilder;
import com.compound.request.json.alpha.vantage.AlphaVantageJson;
import com.compound.request.json.alpha.vantage.AlphaVantageJsonWeekly;
import com.compound.request.url.AlphaVantageUrl;
import com.google.api.client.http.GenericUrl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AlphaVantageRequestBuilder implements RequestBuilder{

  private static final Map<String, Class> jsonMap;
  static{
    Map<String,Class> tempJsonMap = new HashMap<>();
    tempJsonMap.put("TIME_SERIES_INTRADAY",null);
    tempJsonMap.put("TIME_SERIES_DAILY",null);
    tempJsonMap.put("TIME_SERIES_DAILY_ADJUSTED",null);
    tempJsonMap.put("TIME_SERIES_WEEKLY",AlphaVantageJsonWeekly.class);
    tempJsonMap.put("TIME_SERIES_WEEKLY_ADJUSTED",null);
    tempJsonMap.put("TIME_SERIES_MONTHLY",null);
    tempJsonMap.put("TIME_SERIES_MONTHLY_ADJUSTED",null);
    jsonMap = Collections.unmodifiableMap(tempJsonMap);
  }


  public AlphaVantageRequestBuilder(){};

  private static final String FUNCTION_KEY = "function";
  private static final String SYMBOL_KEY = "symbol";
  private GenericUrl alphaVantageURL;
  private Class jsonRoot;
  private AlphaVantageRequestBuilder(String function, String symbol){
    alphaVantageURL = new AlphaVantageUrl();
    alphaVantageURL.put(FUNCTION_KEY, function);
    alphaVantageURL.put(SYMBOL_KEY, symbol);
    jsonRoot = jsonMap.get(function);
  }

  @Override
  public RequestBuilder newInstance(String function, String stock) {
    return new AlphaVantageRequestBuilder(function, stock);
  }

  @Override
  public Class getJsonRoot() {
    return jsonRoot;
  }

  @Override
  public GenericUrl getUrl() {
    return alphaVantageURL;
  }
}
