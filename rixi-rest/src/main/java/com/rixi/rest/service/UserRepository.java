package com.rixi.rest.service;

import com.rixi.rest.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByCreateDateBetween(Date from, Date to);
}
