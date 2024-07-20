package com.stera.papertrade.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stera.papertrade.model.TradeProfile;

@Repository
public interface TradeProfileRepository extends MongoRepository<TradeProfile,Integer>{

    Collection<TradeProfile> findAllByuserId(Object object);
    
}
