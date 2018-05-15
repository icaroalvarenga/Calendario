package com.example.icaro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import android.widget.Spinner;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class EventActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SimpleDateFormat mFormat = new SimpleDateFormat();
        //final MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.calendarView);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos);
        final Database database = new Database(this);
        final String anost;
        final String messt;
        final String diast;
        //TextView txt = (TextView) findViewById(R.id.diaView);
        // capturando dado
        Intent intent = getIntent();
        final String dateSelected = intent.getStringExtra("data");


        final TextView ano = (TextView) findViewById(R.id.evAno);                          //
        final TextView mes = (TextView) findViewById(R.id.evMes);                          //
        final TextView dia = (TextView) findViewById(R.id.evDia);                        //
        //CheckBox duracao = (CheckBox) findViewById(R.id.chkDuracao);            // Instanciacion de recursos
        final TextView horaInit = (TextView) findViewById(R.id.horaInit);
        final TextView horaFim = (TextView) findViewById(R.id.horaFim);
        final EditText titulo = (EditText) findViewById(R.id.evTitulo);
        final Spinner dropdown = (Spinner) findViewById(R.id.tipoEvento);
        final EditText descricao = (EditText) findViewById(R.id.evDescricao);   //
        Button add = (Button) findViewById(R.id.btnAddEvento);

        String[] items = new String[]{"Prova", "Trabalho"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
final int i = dropdown.getSelectedItemPosition();
        Log.d("teste", dateSelected);

        dia.setText(dateSelected.substring(0,2));
        mes.setText(dateSelected.substring(3,5));
        ano.setText(dateSelected.substring(6,10));
        anost=ano.getText().toString();
        messt=mes.getText().toString();
        diast=dia.getText().toString();
        // fazendo alguma coisa com o dado capturado
       // ListView txt = (ListView) findViewById(R.id.myDate);
       // txt.setText(dateSelected);
        final HashSet<CalendarDay> dates = new HashSet<>();
        final EditText timeEditText = (EditText) findViewById(R.id.horaInit);
        new SetTime(timeEditText);
        final EditText timeEditText2 = (EditText) findViewById(R.id.horaFim);
        new SetTime(timeEditText2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                database.insertData(Integer.parseInt(anost),Integer.parseInt(messt),Integer.parseInt(diast),titulo.getText().toString(), descricao.getText().toString(), horaInit.getText().toString(), horaFim.getText().toString());

                int i=dropdown.getSelectedItemPosition();
                Intent devolve = new Intent();
                devolve.putExtra("data", anost+messt+diast);
                setResult(i,devolve);

                finish();
            }
        });
    }



}

