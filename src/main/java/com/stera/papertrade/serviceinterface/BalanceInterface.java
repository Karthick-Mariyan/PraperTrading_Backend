package com.stera.papertrade.serviceinterface;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.stera.papertrade.model.Balance;
import com.stera.papertrade.model.TradeProfile;

public interface BalanceInterface {
    public boolean checkForEnoughBalance(TradeProfile profile, double purchasePrice);
    public ResponseEntity<Balance> getCurrentBalance(Integer profileId);
    public Optional<TradeProfile> affectBalance(TradeProfile profile, double purchasePrice, boolean affectSide); //Affect Side Determines To Affect Up or Down in Balance -> True while purchase : False while sale
}
