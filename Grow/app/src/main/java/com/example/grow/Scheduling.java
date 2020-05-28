package com.example.grow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class Scheduling extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);

        mTextView = findViewById(R.id.textView);

        Button button = (Button) findViewById((R.id.button_timepicker));
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        }));

        Button buttonCancelAlarm = (Button) findViewById((R.id.button_cancel));
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        }));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText("Hour: " + hourOfDay + " Minute: " + minute);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c);

        mTextView.setText(timeText);
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }
}
