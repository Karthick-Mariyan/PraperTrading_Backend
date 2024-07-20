package com.stera.papertrade.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Balance {
    private Double currentBalance;
    
    public Balance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }


}
