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
import android.widget.Toast;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.sql.Date;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent data = new Intent(this, EventActivity.class);
        final Button addButton= (Button) findViewById(R.id.addButton);
        final HashSet<CalendarDay> dates = new HashSet<>();




       /* Database database = new Database(this);
        SQLiteDatabase conn =  database.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("ano","2018");
        cv.put("mes","04");
        cv.put("dia","12");
        conn.insertOrThrow("feriado", null, cv);


        Cursor cursor = conn.query("feriado", null, null, null, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            int id2;
            int ano;
            int dia;
            int mes;
            do {

                id2 =cursor.getInt(0);
                ano = cursor.getInt(1);
                mes = cursor.getInt(2);
                dia = cursor.getInt(3);
                dates.add(CalendarDay.from(ano,mes,dia));
                //Log.d("TESTE","LIDO: "+id2+"-"+data);
            } while(cursor.moveToNext());
        }*/


    setContentView(R.layout.activity_main);
        MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.calendarView);
        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2018, 3, 3))
                .setMaximumDate(CalendarDay.from(2019, 0, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        //mcv.setSelectedDate(CalendarDay.today().getDate());
        mcv.getContext();
        CalendarDay date = CalendarDay.from(2018, 03, 18);
        dates.add(date);
        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Intent it = new Intent(MainActivity.this, EventActivity.class);
                startActivity(it);
            }
        });
        /*mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView mcv, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(getApplicationContext(), date.toString(), Toast.LENGTH_LONG);
                SimpleDateFormat mFormat = new SimpleDateFormat();
                final int dia=date.getDay();
                final int mes = date.getMonth();
                final int ano = date.getYear();
                data.putExtra("dia",dia);
                data.putExtra("mes",mes);
                data.putExtra("ano",ano);




                dates.add(date);





                Log.d("teste",mFormat.format(date.getDate()));
            }
        }


        );
*/


        mcv.addDecorators(new EventDecorator(this,Color.RED, dates));

    }


}

