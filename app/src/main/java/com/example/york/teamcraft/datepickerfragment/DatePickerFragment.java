package com.example.york.teamcraft.datepickerfragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.DatePicker;

/**
 * Created by York on 2017/9/30.
 */

public class DatePickerFragment extends android.support.v4.app.DialogFragment implements DatePickerDialog.OnDateSetListener{
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d("date", Integer.toString(year));
        Log.d("date", Integer.toString(month));
        Log.d("date", Integer.toString(dayOfMonth));
        onDateSetListener.onDateSet(view, year, month, dayOfMonth);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onDateSetListener = (DatePickerDialog.OnDateSetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }
}
