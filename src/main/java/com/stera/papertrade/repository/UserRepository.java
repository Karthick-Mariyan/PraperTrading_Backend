package com.stera.papertrade.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stera.papertrade.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String>{
    Optional<User> findBymail(String userEmail);
}

