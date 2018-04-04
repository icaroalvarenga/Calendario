package com.example.icaro.myapplication;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class MainActivity extends AppCompatActivity {
    MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.calendarView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2018, 3, 3))
                .setMaximumDate(CalendarDay.from(2019, 0, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();



        mcv.setSelectedDate(CalendarDay.today().getDate());
        String data  = mcv.getSelectedDate().getDate().toString();


        OnDateSelectedListener
    }


}
