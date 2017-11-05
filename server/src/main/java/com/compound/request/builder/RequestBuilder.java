package com.compound.request.builder;

import com.compound.request.json.compound.CompoundJsonRoot;
import com.google.api.client.http.GenericUrl;

public interface RequestBuilder {


  RequestBuilder newInstance(String function, String stock);

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
  CompoundJsonRoot buildRequest(Object json) throws ClassCastException;

}
