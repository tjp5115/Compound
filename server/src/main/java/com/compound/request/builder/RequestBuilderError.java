package com.compound.request.builder;

import com.compound.request.json.error.JsonErrorMessage;
import com.google.api.client.http.GenericUrl;

public class RequestBuilderError implements RequestBuilder{
  @Override
  public RequestBuilder newInstance(String function, String stock) {
    return this;
  }

  @Override
  public Class getJsonRoot() {
    return null;
  }

  @Override
  public GenericUrl getUrl() {
    return null;
  }

  @Override
  public JsonErrorMessage buildRequest(Object json) throws ClassCastException {
    return (JsonErrorMessage)json;
  }
}
