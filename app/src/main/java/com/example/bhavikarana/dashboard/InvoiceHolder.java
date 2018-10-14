package com.example.bhavikarana.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class InvoiceHolder extends RecyclerView.ViewHolder {

    private TextView txtTypeOfPurchase, txtMerchantName, txtPaymentMethod, txtAmountPaid;
    public InvoiceHolder(View itemView) {
        super(itemView);
        txtTypeOfPurchase = itemView.findViewById(R.id.txtTypeOfPurchase);
        txtMerchantName = itemView.findViewById(R.id.txtMerchantName);
        txtPaymentMethod = itemView.findViewById(R.id.txtPaymentMethod);
        txtAmountPaid = itemView.findViewById(R.id.txtAmountPaid);
    }

    public void setDetails(Invoice invoice) {
        txtTypeOfPurchase.setText(invoice.getType_of_purchase());
        txtMerchantName.setText(invoice.getMerchant_name());
        txtPaymentMethod.setText(invoice.getPayment_method());
        txtAmountPaid.setText("INR "+invoice.getAmount_paid());
    }
}
