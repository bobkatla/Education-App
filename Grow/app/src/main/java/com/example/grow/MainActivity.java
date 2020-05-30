package com.example.grow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name, password;
    ArrayAdapter arrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle readInstanceState) {
        super.onCreate(readInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper helper = new DatabaseHelper(this);
        final ArrayList array_list = helper.getAllContacts();
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, array_list);
        listView.setAdapter(arrayAdapter);

        findViewById(R.id.Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper.delete()) {
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "NOT Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    if (helper.update(name.getText().toString(), password.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "NOT Updated",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    if(name.getText().toString().isEmpty()) name.setError("Enter Username");
                    if(password.getText().toString().isEmpty()) password.setError("Enter Password");
                }
            }
        });

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array_list.clear();
                array_list.addAll(helper.getAllContacts());
                arrayAdapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    if (helper.checkExist(name.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Error: the name is existed", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (helper.insert(name.getText().toString(), password.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "NOT Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    if(name.getText().toString().isEmpty()) name.setError("Enter Username");
                    if(password.getText().toString().isEmpty()) password.setError("Enter Password");
                }
            }
        });

        findViewById(R.id.Testtest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScheduling();
            }
        });
    }

    private void openScheduling(){
        Intent intent =  new Intent(this, Scheduling.class);
        startActivity(intent);
    }
}
