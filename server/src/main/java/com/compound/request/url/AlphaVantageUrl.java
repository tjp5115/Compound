package com.compound.request.url;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class AlphaVantageUrl extends GenericUrl {
  public AlphaVantageUrl(String encodedUrl){
    super(encodedUrl);
  }

  @Key
  public String fields;
}
