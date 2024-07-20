package com.stera.papertrade.model;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("TradeProfile")
public class TradeProfile {
    @Id
    private Integer tradeProfileId;
    private String userId;
    private String tradeProfileName;
    private ArrayList<TradedAsset> tradedAssets;
    private Double currentBalance;
    private Long initialBalance;

    public Integer getTradeProfileId() {
        return tradeProfileId;
    }
    public void setTradeProfileId(Integer tradeProfileId) {
        this.tradeProfileId = tradeProfileId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTradeProfileName() {
        return tradeProfileName;
    }
    public void setTradeProfileName(String tradeProfileName) {
        this.tradeProfileName = tradeProfileName;
    }
    public ArrayList<TradedAsset> getTradedAssets() {
        return tradedAssets;
    }
    public void setTradedAssets(ArrayList<TradedAsset> tradedAssets) {
        this.tradedAssets = tradedAssets;
    }
    public Double getCurrentBalance() {
        return currentBalance;
    }
    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }
    public Long getInitialBalance() {
        return initialBalance;
    }
    public void setInitialBalance(Long initialBalance) {
        this.initialBalance = initialBalance;
    }
    public TradeProfile(Integer tradeProfileId, String userId, String tradeProfileName,
            ArrayList<TradedAsset> tradedAssets) {
        this.tradeProfileId = tradeProfileId;
        this.userId = userId;
        this.tradeProfileName = tradeProfileName;
        this.tradedAssets = tradedAssets;
    }


   
}

