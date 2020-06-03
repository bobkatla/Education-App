package com.example.grow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle readInstanceState) {
        super.onCreate(readInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.teacher);
        Button b2 = findViewById(R.id.student);
//        Button b3 = findViewById(R.id.testlah);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeacher();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudent();
            }
        });

//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openTest();
//            }
//        });
    }

    private void openTeacher(){
        Intent intent =  new Intent(this, Login.class);
        startActivity(intent);
    }

    private void openStudent(){
        Intent intent =  new Intent(this, Todo.class);
        startActivity(intent);
    }

//    private void openTest(){
//        Intent intent =  new Intent(this, Test.class);
//        startActivity(intent);
//    }
}
