package com.stera.papertrade.serviceinterface;

import org.springframework.http.ResponseEntity;

import com.stera.papertrade.model.TradedAsset;

public interface TradeAssetInterface {
    public ResponseEntity<String> buyAsset(TradedAsset asset, Integer profileId);
    public ResponseEntity<String> sellAsset(TradedAsset asset, Integer profileId);
}
