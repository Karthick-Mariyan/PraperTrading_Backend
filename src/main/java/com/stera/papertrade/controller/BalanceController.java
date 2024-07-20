package com.stera.papertrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.stera.papertrade.model.Balance;
import com.stera.papertrade.serviceinterface.BalanceInterface;


@RestController
public class BalanceController {

    @Autowired
    private BalanceInterface balanceService;

    @GetMapping("/getCurrentBalance/{profileId}")
    public ResponseEntity<Balance> getMethodName(@PathVariable Integer profileId) {
        return balanceService.getCurrentBalance(profileId);
    }
    
}
