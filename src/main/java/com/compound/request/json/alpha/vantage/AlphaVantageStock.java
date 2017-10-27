package com.compound.request.json.alpha.vantage;

import com.google.api.client.util.Key;
import com.google.gson.annotations.SerializedName;

public class AlphaVantageStock {

  @Key("1. open")
  public String open;

  @Key("2. high")
  public String high;

  @Key("3. low")
  public String low;

  @Key("4. close")
  public String close;

  @Key("5. volume")
  public String volume;


}
