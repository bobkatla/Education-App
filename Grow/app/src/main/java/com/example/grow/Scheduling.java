package com.example.grow;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

//Class to schedule activity based on user input of date and time, hence, extend the time and date picker dialog
public class Scheduling extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private TextView mTimeView;
    private TextView mDateView;
    private EditText mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);

        final DatabaseHelper2 helper = new DatabaseHelper2(this);

        mTimeView = findViewById(R.id.timeView);
        mDateView = findViewById(R.id.dateView);
        mTask = findViewById(R.id.inputActivity);

        //Set the string for if statement later to check error
        final String checkDate = "No date picked";
        final String checkTime = "No time picked";
        final String checkTask = "Doing something";
        mDateView.setText(checkDate);
        mTimeView.setText(checkTime);
        mTask.setText(checkTask);

        //Open the time fragment for time picking
        Button button = (Button) findViewById((R.id.button_timepicker));
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment(); //Refer to the TimePickerFragment class
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        }));

        //Open the date fragment for date picking
        Button buttonDate = (Button) findViewById(R.id.button_date);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment(); //Refer to the DatePickerFragment class
                datePicker.show(getSupportFragmentManager(), "Date picker");
            }
        });

        //Input the activity
        Button buttonInput = (Button) findViewById(R.id.insertButton);
        buttonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //the if to check if the user input the date or not, if not show error
                if ((mDateView.getText().toString() != checkDate) && (mTimeView.getText().toString() != checkTime) && (mTask.getText().toString() != checkTask)) {
                    //input the activity into database
                    if (helper.addTask(mDateView.getText().toString(), mTimeView.getText().toString(), mTask.getText().toString())) {
                        Toast.makeText(Scheduling.this, "Put the activity successfully", Toast.LENGTH_LONG).show();
                        //auto refresh the page/activity for so the user can easily put another input
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(Scheduling.this, "Error with insert the activity", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Scheduling.this, "Error: time or date or activity has not been selected", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Go back to homepage if it is done
        TextView home = (TextView) findViewById(R.id.toHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
    }

    //The required method that call when the time is picked, noted that this method only applied for API above Kitkat
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //create a time var to put in the updateTimeText and starAlarm methods
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

    //Method to update the text show the picked time
    private void updateTimeText(Calendar c) {
        String timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime()); //convert to string
        mTimeView.setText(timeText);
    }

    //The method starts the alarm, noted that this method only applied for API above Kitkat
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class); //refer to the AlertReceiver class
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }

    //The required method that call when the date is picked, noted that this method only applied for API above Kitkat
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        //Convert to string
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView tv = (TextView) findViewById(R.id.dateView);
        tv.setText(currentDateString);
    }

    //Go back to homepage method
    private void openHome(){
        Intent intent =  new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
