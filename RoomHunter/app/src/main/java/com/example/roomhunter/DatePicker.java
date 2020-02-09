package com.example.roomhunter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DatePicker extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedBundleInstance){
        //use current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //listener to be implemented by the parent activity
        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener)getActivity();

        //create and return a new instance of DatePickerDialog
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }
}
