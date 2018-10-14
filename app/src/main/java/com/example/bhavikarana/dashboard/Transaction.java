package com.example.bhavikarana.dashboard;

/**
 * Created by RustBucket on 13-10-2018.
 */


public class Transaction {
    private String transactionId;
    private String merchantName;
    private int cash;

    public Transaction(){

    }


    public Transaction(String transactionId, String merchantName, int cash) {
        this.transactionId = transactionId;
        this.merchantName = merchantName;
        this.cash = cash;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public int getCash() {
        return cash;
    }
}