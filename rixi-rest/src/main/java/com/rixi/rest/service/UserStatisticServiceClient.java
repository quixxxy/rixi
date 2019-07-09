/**
 * Copyright 2016 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.rixi.rest.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.Map;

@FeignClient(name = "rixi-statistic")
public interface UserStatisticServiceClient {

    @Async
    @RequestMapping(method = RequestMethod.POST, value = "/statistics/{bucket}/{when}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void increaseCount(@PathVariable("bucket") String bucket, @PathVariable("when") long when);

    @RequestMapping(method = RequestMethod.GET, value = "/statistics/{bucket}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Map<LocalDateTime, Long> getCount(@PathVariable("bucket") String bucket);
}
