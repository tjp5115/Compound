package com.compound.request.builder;

import com.google.api.client.http.GenericUrl;

public interface RequestBuilder {

  RequestBuilder newInstance(String function, String stock);

  /**
   * get the root class for the json
   * @return
   */
   Class getJsonRoot();

  /**
   * get the URL that was built
   * @return
   */
  GenericUrl getUrl();


}
