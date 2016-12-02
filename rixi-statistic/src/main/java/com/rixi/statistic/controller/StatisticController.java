/**
 * Copyright 2016 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.rixi.statistic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController {

    private static final Map<String, Map<LocalDateTime, Long>> STORAGE = new ConcurrentHashMap<>();

    @RequestMapping(value = "/{bucketName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<LocalDateTime, Long> getViewCount(@PathVariable String bucketName) {
        return STORAGE.get(bucketName);
    }

    @RequestMapping(value = "/{bucketName}/{when}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void increaseViewCount(@PathVariable String bucketName, @PathVariable long when) {
        if (!STORAGE.containsKey(bucketName)) {
            STORAGE.put(bucketName, new ConcurrentHashMap<LocalDateTime, Long>());
        }
        Map<LocalDateTime, Long> bucket = STORAGE.get(bucketName);

        LocalDateTime time = LocalDateTime.ofEpochSecond(when / 1000, 0, ZoneOffset.ofHours(-8)).truncatedTo(ChronoUnit.HOURS);
        Long count = bucket.get(time);
        if (count == null) {
            bucket.put(time, 1L);
        } else {
            bucket.put(time, count + 1L);
        }
    }
}
