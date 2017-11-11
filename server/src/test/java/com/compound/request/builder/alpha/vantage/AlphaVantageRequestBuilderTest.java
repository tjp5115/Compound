package com.compound.request.builder.alpha.vantage;

import com.compound.request.json.alpha.vantage.AlphaVantageJson;
import com.compound.request.json.alpha.vantage.AlphaVantageJsonWeekly;
import com.compound.request.json.alpha.vantage.AlphaVantageStock;
import com.compound.request.json.compound.CompoundJsonRoot;
import junit.framework.TestCase;

import java.util.HashMap;

public class AlphaVantageRequestBuilderTest extends TestCase {
  public void testBuildRequest() throws Exception {
    AlphaVantageJsonWeekly json = new AlphaVantageJsonWeekly();
    json.weeklyData = new HashMap<>();

    AlphaVantageStock stock = new AlphaVantageStock();
    stock.close = 1.2;
   // json.weeklyData.put("2017-11-07",stock);

    AlphaVantageRequestBuilder builder = new AlphaVantageRequestBuilder();
    CompoundJsonRoot root = builder.buildRequest(json);

  }

}