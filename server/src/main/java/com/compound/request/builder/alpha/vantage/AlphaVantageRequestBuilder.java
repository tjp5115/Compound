package com.compound.request.builder.alpha.vantage;

import com.compound.request.builder.RequestBuilder;
import com.compound.request.builder.RequestBuilderError;
import com.compound.request.json.alpha.vantage.AlphaVantageJson;
import com.compound.request.json.alpha.vantage.AlphaVantageJsonWeekly;
import com.compound.request.json.alpha.vantage.AlphaVantageStock;
import com.compound.request.json.compound.CompoundJsonDate;
import com.compound.request.json.compound.CompoundJsonRoot;
import com.compound.request.json.compound.CompoundJsonStock;
import com.compound.request.url.AlphaVantageUrl;
import com.google.api.client.http.GenericUrl;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

public class AlphaVantageRequestBuilder implements RequestBuilder {

    private static final Map<String, Class> JSON_CLASS_MAP;

    static {
        Map<String, Class> tempJsonMap = new HashMap<>();
        tempJsonMap.put("TIME_SERIES_INTRADAY", null);
        tempJsonMap.put("TIME_SERIES_DAILY", null);
        tempJsonMap.put("TIME_SERIES_DAILY_ADJUSTED", null);
        tempJsonMap.put("TIME_SERIES_WEEKLY", AlphaVantageJsonWeekly.class);
        tempJsonMap.put("TIME_SERIES_WEEKLY_ADJUSTED", null);
        tempJsonMap.put("TIME_SERIES_MONTHLY", null);
        tempJsonMap.put("TIME_SERIES_MONTHLY_ADJUSTED", null);
        JSON_CLASS_MAP = Collections.unmodifiableMap(tempJsonMap);
    }


    public AlphaVantageRequestBuilder() {
    }

    private static final String FUNCTION_KEY = "function";
    private static final String SYMBOL_KEY = "symbol";
    private GenericUrl alphaVantageURL;
    private AlphaVantageJson jsonRoot;

    private AlphaVantageRequestBuilder(String function, String symbol) throws ClassCastException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        alphaVantageURL = new AlphaVantageUrl();
        alphaVantageURL.put(FUNCTION_KEY, function);
        alphaVantageURL.put(SYMBOL_KEY, symbol);
        jsonRoot = (AlphaVantageJson) JSON_CLASS_MAP.get(function).getDeclaredConstructor().newInstance();
    }

    @Override
    public RequestBuilder newInstance(String function, String symbol) {
        RequestBuilder requestBuilder;
        try {
            requestBuilder = new AlphaVantageRequestBuilder(function, symbol);
        } catch (Exception e) {
            requestBuilder = new RequestBuilderError();
        }
        return requestBuilder;
    }

    @Override
    public Object getJsonRoot() {
        return jsonRoot;
    }

    @Override
    public GenericUrl getUrl() {
        return alphaVantageURL;
    }

    /**
     * Takes the AlphaVantageJson String, and outputs a JSON which is broken up into stock's info per
     * TIME_PERIOD_SELECTED for each year the stock has been available.
     * @param json - Will be one of the JSONs in the JSON_CLASS_MAP
     * @return JSON that represents a stock's info per
     * @throws ClassCastException
     */
    @Override
    public CompoundJsonRoot buildRequest(Object json) throws ClassCastException {
        //todo make this generic. Only able to parse by week.
        jsonRoot = (AlphaVantageJson) json;

        Map<String, AlphaVantageStock> alphaVantageJson = jsonRoot.getStockData();

        List<LocalDate> keys = new ArrayList<>();

        for (String key : alphaVantageJson.keySet()) {
            LocalDate date = LocalDate.parse(key, DateTimeFormatter.ISO_LOCAL_DATE);
            keys.add(date);
        }

        Collections.sort(keys);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        Map<Integer, CompoundJsonDate> stockYear = new LinkedHashMap<>();
        for (LocalDate date : keys) {
            if (stockYear.containsKey(date.getYear()) == false) {
                CompoundJsonDate newStockDay = new CompoundJsonDate();
                stockYear.put(date.getYear(), newStockDay);
            }
            CompoundJsonDate stockDay = stockYear.get(date.getYear());
            CompoundJsonStock stock = new CompoundJsonStock(jsonRoot.getStockData().get(date.toString()).close);
            int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
            stockDay.WeekOfYearToStock.put(weekNumber, stock);
        }
        CompoundJsonRoot compoundJsonRoot = new CompoundJsonRoot();
        compoundJsonRoot.Year = stockYear;
        return compoundJsonRoot;
    }
}
