package com.rixi.rest.service;

import com.rixi.rest.model.UserStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Component
public class StatisticService {

    private static final int HOURS_IN_DAY = 24;

    @Autowired
    private UserStatisticServiceClient userStatisticServiceClient;

    public UserStatistic getStatistic(String bucket) {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.DAYS);
        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);

        long[][] data = new long[2][];

        Map<LocalDateTime, Long> viewCount = userStatisticServiceClient.getCount(bucket);

        if (viewCount != null) {

            long[] yesterdaysStat = calculate(viewCount, yesterday);
            long[] todaysStat = calculate(viewCount, today);

            data[0] = yesterdaysStat;
            data[1] = todaysStat;
        } else {
            data[0] = new long[HOURS_IN_DAY];;
            data[1] = new long[HOURS_IN_DAY];;
        }

        UserStatistic userStatistic = new UserStatistic();

        userStatistic.setSeries(getSeries());
        userStatistic.setLabels(getLabels());
        userStatistic.setData(data);

        return userStatistic;
    }

    private String[] getSeries() {
        return new String[]{"Yesterday", "Today"};
    }

    private String[] getLabels() {
        String[] series = new String[HOURS_IN_DAY];
        for (int i = 0; i < HOURS_IN_DAY; i++) {
            series[i] = i + ":00";
        }
        return series;
    }

    private long[] calculate(Map<LocalDateTime, Long> viewCount, LocalDateTime date) {
        long[] result = new long[HOURS_IN_DAY];

        for (Map.Entry<LocalDateTime, Long> entry : viewCount.entrySet()) {

            if (entry.getKey().truncatedTo(ChronoUnit.DAYS).isEqual(date)) {
                int hour = entry.getKey().getHour();
                result[hour] = result[hour] + entry.getValue();
            }
        }
        return result;
    }
}
