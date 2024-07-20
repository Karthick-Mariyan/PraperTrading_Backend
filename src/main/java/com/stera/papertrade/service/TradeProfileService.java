package com.stera.papertrade.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stera.papertrade.model.TradeProfile;
import com.stera.papertrade.repository.TradeProfileRepository;
import com.stera.papertrade.serviceinterface.BalanceInterface;
import com.stera.papertrade.serviceinterface.TradeProfileInterface;
import com.stera.papertrade.serviceinterface.UserProfileInterface;



@Service
final public class TradeProfileService implements TradeProfileInterface {
     private static final Logger LOG = LogManager.getLogger(TradeProfileService.class);

    @Autowired
    private TradeProfileRepository tradeProfileRepository;

    @Autowired
    private UserProfileInterface userProfile;

    @Autowired
    private BalanceInterface balanceService;

    public ResponseEntity<String> saveTradeProfile(TradeProfile entity){
        Optional<TradeProfile> oldTPEntity = tradeProfileRepository.findById(entity.getTradeProfileId());
        if(oldTPEntity.isPresent()){
           return new ResponseEntity<String>("Trade Profile Already Exists With Same Trade Id! try Modify", HttpStatus.CONFLICT);
        }
        else{
            Long initBalance = (long) 1000000;
            entity.setInitialBalance(initBalance);
            entity.setCurrentBalance(initBalance.doubleValue());
            userProfile.updateUserWithNewTradeProfileId(entity);
            tradeProfileRepository.save(entity);
            LOG.debug("Profile Registration Complete! : "+ oldTPEntity.toString());
            return new ResponseEntity<String>("Trade Profile Registerd!", HttpStatus.OK);
        }
    }

    public String modifyTradeProfile(TradeProfile entity){
        Optional<TradeProfile> oldEntity = tradeProfileRepository.findById(entity.getTradeProfileId());
        if(oldEntity.isPresent()){
            if(entity.getTradeProfileName() != null){
                oldEntity.get().setTradeProfileName(entity.getTradeProfileName());
            }
            if(entity.getTradedAssets() != null){
                oldEntity.get().setTradedAssets(entity.getTradedAssets());
            }
            if(entity.getCurrentBalance() != null){
                oldEntity.get().setCurrentBalance(entity.getCurrentBalance());
            }
            tradeProfileRepository.save(oldEntity.get());
        }else{
            tradeProfileRepository.save(entity);
        }
        return null;
    }


    public boolean checkTradeProfile(Integer entityId){
        return false;
    }

    @Override
    public ResponseEntity<String> updateUserBalanceDetails(Integer entityId, Long currentBalance) {
        Optional<TradeProfile> tpofUser =  tradeProfileRepository.findById(entityId);
        if(tpofUser.isPresent()){
            tpofUser = balanceService.affectBalance(tpofUser.get(), currentBalance.doubleValue(), false);
            tradeProfileRepository.save(tpofUser.get());
            return new ResponseEntity<String>("Trade Profile Updated!", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Trade Profile Not Present!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<TradeProfile> getTradeProfile(Integer entityId) {
        return tradeProfileRepository.findById(entityId);
    }



}
