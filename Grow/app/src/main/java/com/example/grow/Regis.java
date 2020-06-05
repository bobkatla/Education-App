package com.example.grow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Regis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        //connect to the xml with specific views
        final DatabaseHelper helper = new DatabaseHelper(this);
        final EditText name = findViewById(R.id.RegUser);
        final EditText pass = findViewById(R.id.RegPass);

        findViewById(R.id.Registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the user input into the fields if not show error
                if (!name.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                    //Check if the username already existed in the database
                    if (helper.checkExist(name.getText().toString())) {
                        Toast.makeText(Regis.this, "Error: the name is already existed", Toast.LENGTH_LONG).show();
                    } else {
                        //input the user info into the database if it is unique
                        if (helper.insert(name.getText().toString(), pass.getText().toString())) {
                            Toast.makeText(Regis.this, "Sign up successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Regis.this, "Error with system can't sign up", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    if(name.getText().toString().isEmpty()) name.setError("Enter Username");
                    if(pass.getText().toString().isEmpty()) pass.setError("Enter Password");
                }
                //Refresh the page after the registration is done
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
            }
        });

        //Back to login page
        findViewById(R.id.toLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    private void openLogin(){
        Intent intent =  new Intent(this, Login.class);
        startActivity(intent);
    }
}
