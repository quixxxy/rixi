package com.rixi.rest.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rixi.rest.model.User;

public interface UserRepository extends MongoRepository<User, String>
{

}
