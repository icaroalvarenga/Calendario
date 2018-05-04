package com.example.icaro.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.ParseException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent data = new Intent(this, EventActivity.class);
        ListView myDate;
        final HashSet<CalendarDay> dates = new HashSet<>();
        Eventos eventos=new Eventos();
        final ListSqliteActivity database = new ListSqliteActivity();



        myDate=(ListView) findViewById(R.id.myDate);

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

       /* mcv.setOnDateChangedListener(new OnDateSelectedListener() {
                                         @Override
                                         public void onDateSelected(@NonNull MaterialCalendarView mcv, @NonNull CalendarDay date, boolean selected) {
                                             //Toast.makeText(getApplicationContext(), date.toString(), Toast.LENGTH_LONG);
                                             SimpleDateFormat mFormat = new SimpleDateFormat();
                                             //dates.add(date);
                                             //database.getEventos(date.getDay(),date.getMonth(),date.getYear());
                                             Log.d("teste", mFormat.format(date.getDate()));
                                         }
                                     }


        );*/
        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
                                        @Override
                                            public void onDateSelected(@NonNull MaterialCalendarView mcv, @NonNull CalendarDay date, boolean selected) {
                                             SimpleDateFormat mFormat = new SimpleDateFormat();
                                             //dates.add(date);
                                             //database.getEventos(date.getDay(),date.getMonth(),date.getYear());
                                             //Log.d("teste", mFormat.format(date.getDate()));
                                            {
                                                Intent intent = new Intent(mcv.getContext(), EventActivity.class);
                                                Log.d("teste", mFormat.format(date.getDate()));
                                                intent.putExtra("data",mFormat.format(date.getDate()));
                                                startActivity(intent);

                                            }
                                         }
                                     }


        );


        mcv.addDecorators(new EventDecorator(this, Color.BLACK, eventos.getEventosNaoAula()));
        mcv.addDecorators(new EventDecoratorFeriado(this, Color.WHITE, eventos.getEventosFeriados()));


        //mcv.addDecorators(new EventDecorator(this, Color.RED, dates));




    }

    }




