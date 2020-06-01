package com.example.grow;

import androidx.appcompat.app.AppCompatActivity;

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

        final DatabaseHelper helper = new DatabaseHelper(this);
        final EditText name = findViewById(R.id.RegUser);
        final EditText pass = findViewById(R.id.RegPass);

        findViewById(R.id.Registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                    if (helper.checkExist(name.getText().toString())) {
                        Toast.makeText(Regis.this, "Error: the name is already existed", Toast.LENGTH_LONG).show();
                    } else {
                        if (helper.insert(name.getText().toString(), pass.getText().toString())) {
                            Toast.makeText(Regis.this, "Inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Regis.this, "NOT Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    if(name.getText().toString().isEmpty()) name.setError("Enter Username");
                    if(pass.getText().toString().isEmpty()) pass.setError("Enter Password");
                }
            }
        });
    }
}
