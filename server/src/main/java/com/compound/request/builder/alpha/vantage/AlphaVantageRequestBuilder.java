package com.compound.request.builder.alpha.vantage;

import com.compound.request.builder.RequestBuilder;
import com.compound.request.builder.RequestBuilderError;
import com.compound.request.json.alpha.vantage.AlphaVantageJson;
import com.compound.request.json.alpha.vantage.AlphaVantageJsonWeekly;
import com.compound.request.json.alpha.vantage.AlphaVantageStock;
import com.compound.request.json.compound.CompoundJsonRoot;
import com.compound.request.json.compound.CompoundJsonStock;
import com.compound.request.url.AlphaVantageUrl;
import com.google.api.client.http.GenericUrl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class AlphaVantageRequestBuilder implements RequestBuilder{

  private static final Map<String,Class> JSON_CLASS_MAP;
  static{
    Map<String,Class> tempJsonMap = new HashMap<>();
    tempJsonMap.put("TIME_SERIES_INTRADAY",null);
    tempJsonMap.put("TIME_SERIES_DAILY",null);
    tempJsonMap.put("TIME_SERIES_DAILY_ADJUSTED",null);
    tempJsonMap.put("TIME_SERIES_WEEKLY", AlphaVantageJsonWeekly.class);
    tempJsonMap.put("TIME_SERIES_WEEKLY_ADJUSTED",null);
    tempJsonMap.put("TIME_SERIES_MONTHLY",null);
    tempJsonMap.put("TIME_SERIES_MONTHLY_ADJUSTED",null);
    JSON_CLASS_MAP = Collections.unmodifiableMap(tempJsonMap);
  }


  public AlphaVantageRequestBuilder(){};

  private static final String FUNCTION_KEY = "function";
  private static final String SYMBOL_KEY = "symbol";
  private GenericUrl alphaVantageURL;
  private AlphaVantageJson jsonRoot;
  private AlphaVantageRequestBuilder(String function, String symbol) throws ClassCastException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    alphaVantageURL = new AlphaVantageUrl();
    alphaVantageURL.put(FUNCTION_KEY, function);
    alphaVantageURL.put(SYMBOL_KEY, symbol);
    jsonRoot = (AlphaVantageJson)JSON_CLASS_MAP.get(function).getDeclaredConstructor().newInstance();
  }

  @Override
  public RequestBuilder newInstance(String function, String stock) {
    RequestBuilder requestBuilder;
    try {
      requestBuilder = new AlphaVantageRequestBuilder(function, stock);
    } catch (Exception e){
      requestBuilder = new RequestBuilderError();
    }
    return requestBuilder;
  }

  @Override
  public Object getJsonRoot() {
    return jsonRoot;
  }

  @Override
  public GenericUrl getUrl() {
    return alphaVantageURL;
  }

  @Override
  public CompoundJsonRoot buildRequest(Object json) throws ClassCastException{
    jsonRoot = (AlphaVantageJson) json;

    Map<String, AlphaVantageStock> alphaVantageJson = jsonRoot.getStockData();

    List<String> keys = new ArrayList<>(alphaVantageJson.keySet());
    Collections.reverse(keys);

    Map<String,CompoundJsonStock> weeklyData = new LinkedHashMap<>();

    for(String key : keys){
      CompoundJsonStock value = new CompoundJsonStock(jsonRoot.getStockData().get(key).close);
      weeklyData.put(key,value);
    }
    CompoundJsonRoot compoundJsonRoot = new CompoundJsonRoot();
    compoundJsonRoot.dateStock = weeklyData;
    return compoundJsonRoot;
  }
}
