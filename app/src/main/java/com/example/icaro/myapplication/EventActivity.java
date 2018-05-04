package com.example.icaro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class EventActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos);
        TextView txt = (TextView) findViewById(R.id.diaView);
        // capturando dado
        Intent intent = getIntent();
        String dateSelected = intent.getStringExtra("data");
        Date date = new Date();
        date = ConvertToDate(dateSelected);


        // fazendo alguma coisa com o dado capturado
       // ListView txt = (ListView) findViewById(R.id.myDate);
        txt.setText(dateSelected);

    }
    private Date ConvertToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }
}

