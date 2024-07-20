package com.stera.papertrade.controller;

import org.springframework.web.bind.annotation.RestController;

import com.stera.papertrade.model.TradedAsset;
import com.stera.papertrade.serviceinterface.TradeAssetInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TradedAssestController {

    @Autowired
    private TradeAssetInterface tradeAssetService;
    
    @PostMapping("/buy/{profileId}")
    public ResponseEntity<String> butAsset(@PathVariable Integer profileId, @RequestBody TradedAsset asset) {
        return tradeAssetService.buyAsset(asset, profileId);
    }
    @PostMapping("/sell/{profileId}")
    public ResponseEntity<String> sellAsset(@PathVariable Integer profileId, @RequestBody TradedAsset asset) {
        return tradeAssetService.sellAsset(asset, profileId);
    }
    
}
