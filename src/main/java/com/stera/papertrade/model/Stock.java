package com.stera.papertrade.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Stock")
public class Stock {
    @Id
    private String StockName;
    private double CurrentPrice;
    public String getStockName() {
        return StockName;
    }
    public void setStockName(String stockName) {
        StockName = stockName;
    }
    public double getCurrentPrice() {
        return CurrentPrice;
    }
    public void setCurrentPrice(double currentPrice) {
        CurrentPrice = currentPrice;
    }
    public Stock(String stockName, double currentPrice) {
        StockName = stockName;
        CurrentPrice = currentPrice;
    }
    
}
