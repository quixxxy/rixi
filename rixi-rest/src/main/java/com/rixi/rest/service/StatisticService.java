package com.rixi.rest.service;

import com.rixi.rest.model.User;
import com.rixi.rest.model.UserStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class StatisticService {

    private static final int HOURS_IN_DAY = 24;

    @Autowired
    private UserRepository userRepository;

    public UserStatistic getUserStatistic() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.DAYS);
        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);

        Date yesterdayDate = Date.from(yesterday.atZone(ZoneId.systemDefault()).toInstant());
        Date todayDate = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());

        int[][] data = new int[2][];

        int[] yesterdaysStat = calculate(userRepository.findByCreateDateBetween(yesterdayDate, todayDate));
        int[] todaysStat = calculate(userRepository.findByCreateDateBetween(todayDate, new Date()));

        data[0] = yesterdaysStat;
        data[1] = todaysStat;

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

    private int[] calculate(List<User> users) {
        int[] result = new int[HOURS_IN_DAY];
        for (User user : users) {
            Instant instant = Instant.ofEpochMilli(user.getCreateDate().getTime());
            LocalDateTime creationDate = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());

            int hour = creationDate.getHour();
            result[hour] = result[hour] + 1;
        }
        return result;
    }
}
