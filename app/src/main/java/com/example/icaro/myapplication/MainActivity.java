package com.example.icaro.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final HashSet<CalendarDay> dates = new HashSet<>();




        setContentView(R.layout.activity_main);
        MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.calendarView);
        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2018, 3, 3))
                .setMaximumDate(CalendarDay.from(2019, 0, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mcv.setSelectedDate(CalendarDay.today().getDate());
        mcv.getContext();
        CalendarDay date = CalendarDay.from(2018, 03, 18);
        dates.add(date);
        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView mcv, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(getApplicationContext(), date.toString(), Toast.LENGTH_LONG);
                SimpleDateFormat mFormat = new SimpleDateFormat();
                dates.add(date);





                Log.d("teste",mFormat.format(date.getDate()));
            }
        });

        mcv.addDecorators(new EventDecorator(this,Color.RED, dates));
    }


}

