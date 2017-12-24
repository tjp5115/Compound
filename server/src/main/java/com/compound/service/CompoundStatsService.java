package com.compound.service;

import com.compound.request.json.compound.CompoundJsonRoot;
import com.compound.request.json.compound.CompoundStats;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Collection;

public class CompoundStatsService {
    public CompoundStats buildRequest(String type, String symbol, String function) throws IOException {
        YearOverYearService yearOverYearService = new YearOverYearService();
        CompoundJsonRoot compoundJsonRoot = (CompoundJsonRoot) yearOverYearService.buildRequest(type, symbol, function);

        Collection<SummaryStatistics> stockYearStats = collectStats(compoundJsonRoot);

        CompoundStats compoundStats = new CompoundStats();
        return compoundStats;
    }

    Collection<SummaryStatistics> collectStats(CompoundJsonRoot compoundJsonRoot) {
        Collection<SummaryStatistics> stockYearStats = new ArrayList<>();

        compoundJsonRoot.Year.forEach((year, stock) -> {
            SummaryStatistics stockStats = new SummaryStatistics();
            long numberOfWeeksInYear = IsoFields.WEEK_BASED_YEAR.rangeRefinedBy(LocalDate.of(year, 1, 1)).getMaximum();
            for (int week = 0; week < numberOfWeeksInYear; ++week) {
                stockStats.addValue(
                    stock.WeekOfYearToStock.get(week) == null
                        ? null
                        : stock.WeekOfYearToStock.get(week).value
                );
            }
            stockYearStats.add(stockStats);
        });

        return stockYearStats;
    }


}
