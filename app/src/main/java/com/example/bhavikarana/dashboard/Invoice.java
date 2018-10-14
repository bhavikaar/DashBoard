package com.example.bhavikarana.dashboard;

public class Invoice {


    //these are the entries for the cards, each has its own getter and setter for ease
    private String type_of_purchase;
    private String merchant_name;
    private String payment_method;
    private String amount_paid;

    public Invoice(String tp, String mn, String pm, String ap){
        this.amount_paid=ap;
        this.merchant_name=mn;
        this.payment_method=pm;
        this.type_of_purchase=tp;
    }

    public String getType_of_purchase() {
        return type_of_purchase;
    }

    public void setType_of_purchase(String type_of_purchase) {
        this.type_of_purchase = type_of_purchase;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

}
