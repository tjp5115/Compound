package com.compound.request.builder;

import java.util.Map;

public abstract class RequestBuilderFactory {


  protected static Map<String, RequestBuilder> requestMap;
  public RequestBuilder getRequestBuilder(String type, String function, String stock){
    RequestBuilder requestBuilder = requestMap.get(type);
    return requestBuilder.newInstance(function, stock);
  }
}
