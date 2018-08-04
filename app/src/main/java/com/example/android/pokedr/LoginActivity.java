package com.example.android.pokedr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Utils.SharedPref;

/**
 * Created by Siddhant Choudhary on 21-01-2017.
 */
public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    EditText txtName;
    EditText txtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        btnlogin=(Button)findViewById(R.id.btnLogin);
        txtName=(EditText)findViewById(R.id.username);
        txtpass=(EditText)findViewById(R.id.pass);

        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (txtName.getText().toString().trim().length() > 0) {

                    String name = txtName.getText().toString().trim();
                    String pass= txtpass.getText().toString().trim();
                    Intent intent = new Intent(LoginActivity.this,
                            FrontActivity.class);
                    intent.putExtra("name", name);
                    SharedPref sp=new SharedPref(getApplicationContext());
                    sp.createLoginSession(name,pass);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
