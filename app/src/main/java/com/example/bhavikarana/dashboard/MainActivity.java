package com.example.bhavikarana.dashboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText merchantName;
    EditText cashAmount;
    Button sendCash;

    DatabaseReference databaseReference;

    ListView listViewTransactions;

    List<Transaction> transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_add:
//                        startActivity(new Intent(MainActivity.this, MainActivity.class));
//                        Toast.makeText(MainActivity.this, "Action Add Click", Toast.LENGTH_LONG).show();
//                        break;
//                    case R.id.some_other_action:
//                        startActivity(new Intent(MainActivity.this, TransactionList.class));
//                        Toast.makeText(MainActivity.this, "Some Other Action Click", Toast.LENGTH_LONG).show();
//                        break;
//                }
//                return true;
//            }
//        });

        databaseReference = FirebaseDatabase.getInstance().getReference("transactions");

        merchantName = (EditText) findViewById(R.id.merchantName);
        cashAmount = (EditText) findViewById(R.id.cashPrice);
        sendCash = (Button) findViewById(R.id.button);

        transactionList = new ArrayList<>();

        sendCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTransaction();
            }
        });
    }


    private void addTransaction(){
        String merchant = merchantName.getText().toString().trim();
        String cash = cashAmount.getText().toString();

        if (!TextUtils.isEmpty(merchant)){
            String id = databaseReference.push().getKey();

            Transaction artist = new Transaction(id, merchant, Integer.parseInt(cash));

            databaseReference.child(id).setValue(artist);

            Toast.makeText(this, "Transaction Completed", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Transaction details", Toast.LENGTH_LONG).show();
        }
    }
}
