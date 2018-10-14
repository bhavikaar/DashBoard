package com.example.bhavikarana.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceHolder> {

    private Context context;
    private ArrayList<Invoice> invoices;

    public InvoiceAdapter(Context context, ArrayList<Invoice> invoices) {
        this.context = context;
        this.invoices = invoices;
    }

    @Override
    public void onBindViewHolder(InvoiceHolder holder, int position) {
        Invoice in=invoices.get(position);
        holder.setDetails(in);
    }

    @NonNull
    @Override
    public InvoiceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.invoice_card, viewGroup, false);
        return new InvoiceHolder(view);
    }

    @Override
    public int getItemCount() {
        return this.invoices.size();
    }


}
