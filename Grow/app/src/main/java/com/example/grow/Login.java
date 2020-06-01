package com.example.grow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DatabaseHelper dbHelper = new DatabaseHelper(this);

        final EditText mName = findViewById(R.id.user);
        final EditText mPassword = findViewById(R.id.pass);

        Button loginB = findViewById(R.id.login);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mName.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()) {
                    if (dbHelper.login(mName.getText().toString(), mPassword.getText().toString())) {
                        openScheduling();
                    } else {
                        Toast.makeText(Login.this, "Wrong password or username", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if(mName.getText().toString().isEmpty()) mName.setError("Enter Username");
                    if(mPassword.getText().toString().isEmpty()) mPassword.setError("Enter Password");
                }
            }
        });

        findViewById(R.id.toRegis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegis();
            }
        });
    }

    private void openScheduling(){
        Intent intent =  new Intent(this, Scheduling.class);
        startActivity(intent);
    }

    private void openRegis(){
        Intent intent =  new Intent(this, Regis.class);
        startActivity(intent);
    }
}
