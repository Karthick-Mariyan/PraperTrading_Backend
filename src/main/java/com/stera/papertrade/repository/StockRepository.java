package com.stera.papertrade.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stera.papertrade.model.Stock;

public interface StockRepository extends MongoRepository<Stock,String>{
    
}
