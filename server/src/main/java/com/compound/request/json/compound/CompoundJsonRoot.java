package com.compound.request.json.compound;

import com.google.api.client.util.Key;

import java.util.Map;

public class CompoundJsonRoot {
  @Key
  public Map<String,CompoundJsonStock> dateStock;
}
