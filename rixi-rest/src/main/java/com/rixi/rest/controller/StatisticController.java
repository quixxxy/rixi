package com.rixi.rest.controller;

import com.rixi.rest.model.UserStatistic;
import com.rixi.rest.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statistic", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserStatistic getUserStatistic() {
        return statisticService.getUserStatistic();
    }

}
