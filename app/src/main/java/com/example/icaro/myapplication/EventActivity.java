package com.example.icaro.myapplication;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class EventActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SimpleDateFormat mFormat = new SimpleDateFormat();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos);
        final Database database = new Database(this);
        //TextView txt = (TextView) findViewById(R.id.diaView);
        // capturando dado
        Intent intent = getIntent();
        String dateSelected = intent.getStringExtra("data");


        final TextView ano = (TextView) findViewById(R.id.evAno);                          //
        final TextView mes = (TextView) findViewById(R.id.evMes);                          //
        final TextView dia = (TextView) findViewById(R.id.evDia);                        //
        //CheckBox duracao = (CheckBox) findViewById(R.id.chkDuracao);            // Instanciacion de recursos
        final TextView horaInit = (TextView) findViewById(R.id.horaInit);
        final TextView horaFim = (TextView) findViewById(R.id.horaFim);
        final EditText titulo = (EditText) findViewById(R.id.evTitulo);
        Spinner dropdown = (Spinner) findViewById(R.id.tipoEvento);
        final EditText descricao = (EditText) findViewById(R.id.evDescricao);   //
        Button add = (Button) findViewById(R.id.btnAddEvento);

        String[] items = new String[]{"Prova", "Trabalho"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        Log.d("teste", dateSelected);

        dia.setText(dateSelected.substring(0,2));
        mes.setText(dateSelected.substring(3,5));
        ano.setText(dateSelected.substring(6,10));
        // fazendo alguma coisa com o dado capturado
       // ListView txt = (ListView) findViewById(R.id.myDate);
       // txt.setText(dateSelected);

        final EditText timeEditText = (EditText) findViewById(R.id.horaInit);
        new SetTime(timeEditText);
        final EditText timeEditText2 = (EditText) findViewById(R.id.horaFim);
        new SetTime(timeEditText2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.insertData(Integer.parseInt(ano.getText().toString()),Integer.parseInt(mes.getText().toString()),Integer.parseInt(dia.getText().toString()),titulo.getText().toString(), descricao.getText().toString(), horaInit.getText().toString(), horaFim.getText().toString());
            }
        });
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

