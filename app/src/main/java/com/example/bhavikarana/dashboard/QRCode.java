package com.example.bhavikarana.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class QRCode extends AppCompatActivity{

    IntentIntegrator qrScan;
    TextView result;
    Button buttonScan;

    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        buttonScan = (Button) findViewById(R.id.buttonScan);
        result = (TextView) findViewById(R.id.tv);

        qrScan = new IntentIntegrator(this);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        TextView res = (TextView) findViewById(R.id.tv);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textview
                    ((TextView) res).setText("MerchantName: "+obj.getString("merchant_name")+"\n Cash"+obj.getString("cash"));
                    buttonScan.setEnabled(false);
                    buttonScan.setVisibility(View.GONE);

                    databaseReference = FirebaseDatabase.getInstance().getReference("transactions");

                    Log.d("SUPPPP", "DSAFASDFSDFADSFASDFADSF");
                    String merchant = obj.getString("merchant_name");
                    int cash = Integer.parseInt(obj.getString("cash"));


                    String id = databaseReference.push().getKey();

                    Transaction artist = new Transaction(id, merchant, cash);

                    databaseReference.child(id).setValue(artist);



                        //textViewAddress.setText(obj.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
