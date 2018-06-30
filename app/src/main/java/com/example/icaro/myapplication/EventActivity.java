package com.example.icaro.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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

        final CheckBox cbox = (CheckBox) findViewById(R.id.ckBox);
        final TextView ano = (TextView) findViewById(R.id.evAno);                          //
        final TextView mes = (TextView) findViewById(R.id.evMes);                          //
        final TextView dia = (TextView) findViewById(R.id.evDia);                        //
        //CheckBox duracao = (CheckBox) findViewById(R.id.chkDuracao);            // Instanciacion de recursos
        final TextView horaInit = (TextView) findViewById(R.id.horaInit);
        final TextView horaFim = (TextView) findViewById(R.id.horaFim);
        final EditText titulo = (EditText) findViewById(R.id.evTitulo);
        final Spinner dropdown = (Spinner) findViewById(R.id.tipoEvento);
        final Spinner dropdownHora = (Spinner) findViewById(R.id.horaLembrete);

        final EditText descricao = (EditText) findViewById(R.id.evDescricao);   //
        Button add = (Button) findViewById(R.id.btnAddEvento);

        String[] items = new String[]{"Prova", "Trabalho"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
final int i = dropdown.getSelectedItemPosition();

        String[] itemsAlarm = new String[]{"1 hora", "2 horas", "3 horas"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsAlarm);
        dropdownHora.setAdapter(adapter2);
        final int iA = dropdownHora.getSelectedItemPosition();
        Log.d("teste", String.valueOf(iA));


        Log.d("teste", dateSelected);

        dia.setText(dateSelected.substring(0,2));
        mes.setText(dateSelected.substring(3,5));
        ano.setText(dateSelected.substring(6,10));
        anost=ano.getText().toString();
        messt=mes.getText().toString();
        diast=dia.getText().toString();

        final HashSet<CalendarDay> dates = new HashSet<>();
        final EditText timeEditText = (EditText) findViewById(R.id.horaInit);
        new SetTime(timeEditText);
        final EditText timeEditText2 = (EditText) findViewById(R.id.horaFim);
        new SetTime(timeEditText2);
        setResult(-1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=dropdown.getSelectedItemPosition();
                String j=Integer.toString(i);
                int iA=dropdownHora.getSelectedItemPosition();
                database.insertData(anost,messt,diast,titulo.getText().toString(), descricao.getText().toString(), horaInit.getText().toString(), horaFim.getText().toString(),j);
                if(cbox.isChecked()){
                    gerarNoticicao(converteDia(anost,messt,diast),timeEditText.getText().toString(),iA);
                }

                Intent devolve = new Intent();
                devolve.putExtra("data", anost+messt+diast);
                devolve.putExtra("tipo",i);
                setResult(0,devolve);

                finish();
            }
        });
    }

public void gerarNoticicao(Calendar calendar, String hora,int iA){
    final EditText titulo = (EditText) findViewById(R.id.evTitulo);
    final Spinner dropdown = (Spinner) findViewById(R.id.tipoEvento);



    Intent myIntent = new Intent(this, MyReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

    AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    //Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    Log.d("teste", hora);
    int i=dropdown.getSelectedItemPosition();
if(i==0){
    myIntent.putExtra("tipo","Prova");
}else{
    myIntent.putExtra("tipo","Trabalho");

}
    myIntent.putExtra("titulo",titulo.getText().toString());
    myIntent.putExtra("data",calendar);
    myIntent.putExtra("hora",hora);

    int horaCon = Integer.parseInt(hora.substring(0,2));
    int minCon = Integer.parseInt(hora.substring(3,5));
    Log.d("teste", String.valueOf(horaCon));
    Log.d("teste", String.valueOf(minCon));
    Log.d("teste", String.valueOf(iA));
int aux=minCon-(iA+1);
    Log.d("teste", String.valueOf(aux));

    calendar.set(Calendar.HOUR_OF_DAY, horaCon);
    calendar.set(Calendar.MINUTE,aux);
    calendar.set(Calendar.SECOND, 0);

    manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent);
}
public Calendar converteDia(String dia,String mes,String ano){


        Calendar ca =Calendar.getInstance();
        ca.set(Integer.valueOf(ano),Integer.valueOf(mes),Integer.valueOf(dia));
        return ca;
}
}

