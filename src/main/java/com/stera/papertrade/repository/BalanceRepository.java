package com.stera.papertrade.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stera.papertrade.model.TradeProfile;

@Repository
public interface BalanceRepository extends MongoRepository<TradeProfile,Integer> {

}
