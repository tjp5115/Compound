package com.compound.request.json.compound;

import com.google.api.client.util.Key;

import java.time.MonthDay;
import java.util.LinkedHashMap;
import java.util.Map;

public class CompoundJsonDate {
  @Key
  public Map<Integer,CompoundJsonStock> WeekOfYearToStock;

  public CompoundJsonDate() {
    WeekOfYearToStock = new LinkedHashMap<>();
  }
}
