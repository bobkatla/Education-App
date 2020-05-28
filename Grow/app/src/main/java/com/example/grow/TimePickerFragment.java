package com.example.grow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;
public class TimePickerFragment extends DialogFragment {
    Calendar cur = Calendar.getInstance();
    int hour = cur.get(Calendar.HOUR_OF_DAY);
    int minute = cur.get(Calendar.MINUTE);
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
