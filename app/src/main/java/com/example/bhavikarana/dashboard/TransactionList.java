package com.example.bhavikarana.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RustBucket on 14-10-2018.
 */

public class TransactionList extends AppCompatActivity {

    private Activity context;

    DatabaseReference databaseReference;

    List<Transaction> transactionList = new ArrayList<>();

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_add:
////                        startActivity(new Intent(TransactionList.this, TransactionList.class));
////                        Toast.makeText(TransactionList.this, "Action Add Click", Toast.LENGTH_LONG).show();
//                        break;
//                    case R.id.some_other_action:
////                        startActivity(new Intent(TransactionList.this, MainActivity.class));
////                        Toast.makeText(TransactionList.this, "Some Other Action Click", Toast.LENGTH_LONG).show();
//                        break;
//                }
//
//                return true;
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.investment_layout);

        final TextView investmentMade = (TextView) findViewById(R.id.total_investment);
        final TextView returnsAmount = (TextView) findViewById(R.id.returns);
        final TextView maturity_date = (TextView) findViewById(R.id.maturity_date);

        databaseReference = FirebaseDatabase.getInstance().getReference("transactions");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot transactionShot: dataSnapshot.getChildren()){
                    Transaction transaction = transactionShot.getValue(Transaction.class);
                    transactionList.add(transaction);
                    investmentMade.setText("Rs. " + this.calculateTotalInvestment() + "");
                    returnsAmount.setText("Rs. " + this.calculateReturns() + "");
                    maturity_date.setText(this.calculateMaturityDate());
                }
            }

            private String calculateMaturityDate() {
                return "12/10/2018";
            }

            private float calculateReturns() {
                float amount = 1;
                for (Transaction t:
                        transactionList) {
                    amount += t.getCash()*1/25;
                    Log.d("Logging Returns: ", String.valueOf(t.getCash()*1/25));
                }
                return amount;
            }

            private int calculateTotalInvestment() {
                int amount = 0;
                for (Transaction t:
                        transactionList) {
                    amount += t.getCash();
                    Log.d("Logging Investments: ", String.valueOf(t.getCash()));
                }
                return amount;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

