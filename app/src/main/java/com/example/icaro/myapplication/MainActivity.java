package com.example.icaro.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    HashSet<CalendarDay> dates = new HashSet<>();
    final HashSet<CalendarDay> dates2 = new HashSet<>();

    //final HashSet<CalendarDay> dataDeProvas = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent data = new Intent(this, EventActivity.class);
        ListView myDate;


        Eventos eventos=new Eventos();
        //Database dtb=new Database(getBaseContext());
        //dates=dtb.carregaDadosProva();
        final ListSqliteActivity database = new ListSqliteActivity();



        myDate=(ListView) findViewById(R.id.myDate);

        setContentView(R.layout.activity_main);
        MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.calendarView);

        mcv.state().edit()

                .setMinimumDate(CalendarDay.from(2018, 3, 3))
                .setMaximumDate(CalendarDay.from(2019, 0, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mcv.setSelectedDate(CalendarDay.today().getDate());
        mcv.getContext();



        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
                                        @Override
                                            public void onDateSelected(@NonNull final MaterialCalendarView mcv, @NonNull final CalendarDay date, boolean selected) {

                                            FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);

                                            Database crud = new Database(getBaseContext());
                                            Cursor cursor = crud.carregaDados();

                                            String[] nomeCampos = new String[] {"titulo", "horaInit"};
                                            int[] idViews = new int[] {R.id.tituloView, R.id.horaView};

                                            SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                                                    R.layout.lista_eventos,cursor,nomeCampos,idViews, 0);
                                            ListView lista = (ListView) findViewById(R.id.myDate);
                                            lista.setAdapter(adaptador);


                                            addButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(mcv.getContext(), EventActivity.class);
                                                    //Log.d("teste", DateFormat.getDateInstance(DateFormat.SHORT).format(date.getDate()));
                                                    //intent.putExtra("data",mFormat.format(date.getDate()));
                                                    intent.putExtra("data",DateFormat.getDateInstance(DateFormat.SHORT).format(date.getDate()));

                                                    startActivityForResult(intent, 1);
                                                }
                                            });
                                               /* Intent intent = new Intent(mcv.getContext(), EventActivity.class);
                                                Log.d("teste", DateFormat.getDateInstance(DateFormat.SHORT).format(date.getDate()));
                                                //intent.putExtra("data",mFormat.format(date.getDate()));
                                                intent.putExtra("data",DateFormat.getDateInstance(DateFormat.SHORT).format(date.getDate()));

                                                startActivity(intent);*/


                                         }
                                     }


        );




        //mcv.addDecorators(new EventDecoratorEventos(Color.RED,dtb.carregaDadosProva()));
        mcv.addDecorators(new EventDecorator(this,Color.BLACK, eventos.getEventosNaoAula()));
        mcv.addDecorators(new EventDecoratorFeriado(this, Color.WHITE, eventos.getEventosFeriados()));






    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.calendarView);
        Date da;
        int dia;
        int mes;
        int ano;
        final String dateSelected = data.getStringExtra("data");
        Log.d("teste", dateSelected);
        ano= Integer.parseInt(dateSelected.substring(0,4));
        mes= Integer.parseInt(dateSelected.substring(4,6));
        dia= Integer.parseInt(dateSelected.substring(6,8));
        //da=ConvertToDate(dateSelected);
        CalendarDay cday = new CalendarDay(ano,mes-1,dia);
        Log.d("teste", cday.toString());
        Log.d("teste", String.valueOf(resultCode));
        Log.d("teste", String.valueOf(requestCode));
        if (requestCode == 1) {
            if(resultCode == 0) {
                dates.add(cday);
                mcv.addDecorators(new EventDecoratorEventos(Color.RED, dates));
            }
            if(resultCode == 1) {
                dates2.add(cday);
                mcv.addDecorators(new EventDecoratorEventos(Color.GREEN, dates2));
            }

            }
        }




    }





