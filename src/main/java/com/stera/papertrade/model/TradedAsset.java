package com.stera.papertrade.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("TradedAsset")
public class TradedAsset {
    @Id
    private Integer tradingId;
    private String stockName;
    private Date doaction; //Date Of action
    private boolean action; // Buy = True && Sell = False
    private double purchasePrice; //Purchase Price
    private Integer purhcaseQuantity;
    public Integer getTradingId() {
        return tradingId;
    }
    public void setTradingId(Integer tradingId) {
        this.tradingId = tradingId;
    }
    public String getStockName() {
        return stockName;
    }
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
    public Date getDoaction() {
        return doaction;
    }
    public void setDoaction(Date doaction) {
        this.doaction = doaction;
    }
    public boolean isAction() {
        return action;
    }
    public void setAction(boolean action) {
        this.action = action;
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public Integer getPurhcaseQuantity() {
        return purhcaseQuantity;
    }
    public void setPurhcaseQuantity(Integer purhcaseQuantity) {
        this.purhcaseQuantity = purhcaseQuantity;
    }
    public TradedAsset(Integer tradingId, String stockName, Date doaction, boolean action,
            double purchasePrice, Integer purhcaseQuantity) {
        this.tradingId = tradingId;
        this.stockName = stockName;
        this.doaction = doaction;
        this.action = action;
        this.purchasePrice = purchasePrice;
        this.purhcaseQuantity = purhcaseQuantity;
    }
    
}