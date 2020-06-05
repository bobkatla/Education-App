package com.example.grow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle readInstanceState) {
        super.onCreate(readInstanceState);
        setContentView(R.layout.activity_main);

        //connect to the xml with specific views
        Button b1 = findViewById(R.id.teacher);
        Button b2 = findViewById(R.id.student);

        //Open the Scheduling activity
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeacher();
            }
        });

        //Open the Todo activity
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudent();
            }
        });
    }

    private void openTeacher(){
        Intent intent =  new Intent(this, Login.class);
        startActivity(intent);
    }

    private void openStudent(){
        Intent intent =  new Intent(this, Todo.class);
        startActivity(intent);
    }
}
