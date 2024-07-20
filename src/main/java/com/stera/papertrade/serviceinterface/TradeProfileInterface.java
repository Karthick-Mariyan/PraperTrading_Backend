package com.stera.papertrade.serviceinterface;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.stera.papertrade.model.TradeProfile;

public interface TradeProfileInterface {

    public ResponseEntity<String> saveTradeProfile(TradeProfile entity);
    public String modifyTradeProfile(TradeProfile entity);
    public boolean checkTradeProfile(Integer entityId);
    public Optional<TradeProfile> getTradeProfile(Integer entityId);
    public ResponseEntity<String> updateUserBalanceDetails(Integer entityId, Long currentBalance);

}
