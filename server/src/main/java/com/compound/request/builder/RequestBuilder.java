package com.compound.request.builder;

import com.google.api.client.http.GenericUrl;

public interface RequestBuilder {


  RequestBuilder newInstance(String function, String symbol);

  /**
   * get the root class for the json
   * @return
   */
   Object getJsonRoot();

  /**
   * get the URL that was built
   * @return
   */
  GenericUrl getUrl();

  /**
   * build the request
   * @param json
   */
  Object buildRequest(Object json) throws ClassCastException;

}
