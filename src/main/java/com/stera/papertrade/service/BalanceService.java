package com.stera.papertrade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stera.papertrade.model.Balance;
import com.stera.papertrade.model.TradeProfile;
import com.stera.papertrade.repository.BalanceRepository;
import com.stera.papertrade.serviceinterface.BalanceInterface;


@Service
public class BalanceService implements BalanceInterface{


    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public boolean checkForEnoughBalance(TradeProfile profile, double  purchasePrice) {
        if (profile.getCurrentBalance() >= purchasePrice){
            return true;
        }
        return false;
    }

    @Override
    public Optional<TradeProfile> affectBalance(TradeProfile profile, double purchasePrice, boolean affectSide) {
        if(affectSide){
            profile.setCurrentBalance( profile.getCurrentBalance() - purchasePrice);
        }else{
            profile.setCurrentBalance( profile.getCurrentBalance() + purchasePrice);
        }
        
        return  Optional.ofNullable(profile);
    }

    @Override
    public ResponseEntity<Balance> getCurrentBalance(Integer profileId) {
        Optional<TradeProfile> tradeProfile = balanceRepository.findById(profileId);
        if(tradeProfile.isPresent()){
            Balance balance = new Balance(tradeProfile.get().getCurrentBalance());
            return new ResponseEntity<Balance>(balance, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    
    
}
