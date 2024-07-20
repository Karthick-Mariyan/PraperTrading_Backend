package com.stera.papertrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stera.papertrade.model.TradeProfile;
import com.stera.papertrade.serviceinterface.TradeProfileInterface;



@RestController
public class TradeProfileController {
    @Autowired
    private TradeProfileInterface tradeProfileService;
    
    @PostMapping("/createtradeprofile")
    public ResponseEntity<String> RegisterProfile(@RequestBody TradeProfile entity) {
      
        return tradeProfileService.saveTradeProfile(entity);
        
    }


    // TODO
    // @GetMapping("/getTradeProfile/{userId}")
    // public Collection<TradeProfile> RegisterProfile(@PathVariable String userId) {
    //     return tradeProfileService.getAllTradeProfileByUserId(userId);
    // }


    
    @PostMapping("/updateProfileBalance/{profileId}")
    public ResponseEntity<String> RegisterProfile(@PathVariable Integer profileId, @RequestBody Long currentBalance) {
        return tradeProfileService.updateUserBalanceDetails(profileId, currentBalance);
        
    }
    
}
