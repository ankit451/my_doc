package com.example.android.pokedr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import Utils.SharedPref;

/**
 * Created by Siddhant Choudhary on 22-01-2017.
 */
public class ProfileActivity extends AppCompatActivity {

    TextView txtname;
    String gender,weight,height,age;
    EditText gen,wgt,hgt,ag;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        txtname=(TextView)findViewById(R.id.details_etname);

        gen=(EditText)findViewById(R.id.details_etsex);
        wgt=(EditText)findViewById(R.id.details_etweight);
        hgt=(EditText)findViewById(R.id.details_etheight);
        ag=(EditText)findViewById(R.id.details_etage);

        btn=(Button)findViewById(R.id.details_submit_btn);
        SharedPref sp= new SharedPref(getApplicationContext());
        HashMap<String,String> map=sp.getuserdetails();
        txtname.setText(map.get("uname"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender=gen.getText().toString();
                weight=wgt.getText().toString();
                height=hgt.getText().toString();
                age=ag.getText().toString();
                Toast.makeText(getApplicationContext(),"Details Submitted Successfully",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
