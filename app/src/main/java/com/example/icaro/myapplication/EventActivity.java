package com.example.icaro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class EventActivity extends Activity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventoAdapter eAdapter;
        listView = (ListView) findViewById(R.id.lista_eventos);
        ArrayList<Evento> eventoList = new ArrayList<>();
        eventoList.add(new Evento(24, 04 , 2018, 00));
        eventoList.add(new Evento(25, 04 , 2018, 00));
        eventoList.add(new Evento(26, 04 , 2018, 00));
        eventoList.add(new Evento(27, 04 , 2018, 00));
        eventoList.add(new Evento(28, 04 , 2018, 00));
        eventoList.add(new Evento(29, 04 , 2018, 00));
        eventoList.add(new Evento(30, 04 , 2018, 00));


        eAdapter = new EventoAdapter(this,eventoList);
        listView.setAdapter(eAdapter);

    }

    }

