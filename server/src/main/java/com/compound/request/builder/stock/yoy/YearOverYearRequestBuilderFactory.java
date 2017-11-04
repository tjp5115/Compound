package com.compound.request.builder.stock.yoy;

import com.compound.request.builder.RequestBuilder;
import com.compound.request.builder.RequestBuilderFactory;
import com.compound.request.builder.alpha.vantage.AlphaVantageRequestBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class YearOverYearRequestBuilderFactory extends RequestBuilderFactory {
  static{
    Map< String, RequestBuilder> tempRequestMap = new HashMap<>();
    tempRequestMap.put("ALPHA_VANTAGE", new AlphaVantageRequestBuilder());
    requestMap = Collections.unmodifiableMap(tempRequestMap);
  }
}
