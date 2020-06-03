package com.example.grow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Todo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        final DatabaseHelper2 helper = new DatabaseHelper2(this);
        final TextView theDate = (TextView) findViewById(R.id.dateViewTodo);
        final String check = "No date picked";
        theDate.setText(check);

        Button buttonDate = (Button) findViewById(R.id.button_dateTodo);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date picker");
            }
        });

        Button showButton = (Button) findViewById(R.id.showTodo);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (theDate.getText().toString() != check) {
                    final ArrayList<Data> data = helper.getAllTasks(theDate.getText().toString());
                    mRecyclerView = findViewById(R.id.recViewSchedule);
                    mLayoutManger = new LinearLayoutManager(Todo.this);
                    mAdapter = new LsAdapter(data);
                    mRecyclerView.setLayoutManager(mLayoutManger);
                    mRecyclerView.setAdapter(mAdapter);

                    ItemTouchHelper.SimpleCallback iTCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
                        @Override
                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                            return false;
                        }

                        @Override
                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                            data.remove(viewHolder.getAdapterPosition());
                            mAdapter.notifyDataSetChanged();
                        }
                    };
                    new ItemTouchHelper(iTCallback).attachToRecyclerView(mRecyclerView);
                } else {
                    Toast.makeText(Todo.this, "Please choose a date", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView tv = (TextView) findViewById(R.id.dateViewTodo);
        tv.setText(currentDateString);
    }
}
