package com.example.icaro.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HashSet<CalendarDay> dates = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent data = new Intent(this, EventActivity.class);
        ListView myDate;


        Eventos eventos=new Eventos();
        Database dtb=new Database(getBaseContext());
        final ListSqliteActivity database = new ListSqliteActivity();



        myDate=(ListView) findViewById(R.id.myDate);

        setContentView(R.layout.activity_main);
        final MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.calendarView);

        mcv.state().edit()

                .setMinimumDate(CalendarDay.from(2018, 3, 3))
                .setMaximumDate(CalendarDay.from(2019, 0, 12))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mcv.setSelectedDate(CalendarDay.today().getDate());
        loadEventos(CalendarDay.today());
        mcv.getContext();


        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);

        addEventos(CalendarDay.today());


        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
                                        @Override
                                            public void onDateSelected(@NonNull final MaterialCalendarView mcv, @NonNull final CalendarDay date, boolean selected) {

                                            //FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);

                                            loadEventos(date);

                                            addEventos(date);





                                         }
                                     }


        );




        mcv.addDecorators(new EventDecoratorEventos(Color.RED,dtb.carregaDadosProva()));
        mcv.addDecorators(new EventDecoratorEventos(Color.RED,dtb.carregaDadosTrabalho()));

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


        if (requestCode == 1) {

            if(resultCode == 0) {
                final String dateSelected = data.getStringExtra("data");
                ano= Integer.parseInt(dateSelected.substring(0,4));
                mes= Integer.parseInt(dateSelected.substring(4,6));
                dia= Integer.parseInt(dateSelected.substring(6,8));
                int u=data.getIntExtra("tipo",-1);
                //da=ConvertToDate(dateSelected);
                CalendarDay cday = new CalendarDay(ano,mes-1,dia);

                dates.add(cday);
                mcv.addDecorators(new EventDecoratorEventos(Color.RED, dates));

                loadEventos(cday);

            }


            }
        }

    public String formataData(java.util.Date data){
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return format.format(data.getTime());
    }

    public void loadEventos (final CalendarDay date){
        final MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.calendarView);
        final int id2=0;
        Database crud = new Database(getBaseContext());
        final Cursor cursor = crud.carregaDados(date.getYear(),date.getMonth()+1,date.getDay());
        String[] nomeCampos = new String[] {"titulo", "horaInit","_id","tipo"};
        int[] idViews = new int[] {R.id.tituloView, R.id.horaView,id2,R.id.tipoView};

        final SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.lista_eventos,cursor,nomeCampos,idViews, 0)
        {@Override
        public View getView(int position, View convertView, ViewGroup parent){
            // Get the current item from ListView
            View view = super.getView(position,convertView,parent);
            final TextView tipo = (TextView) view.findViewById(R.id.tipoView);

            if(tipo.getText().toString().equals("0"))
            {
                // Set a background color for ListView regular row/item
                view.findViewById(R.id.tag).setBackgroundColor(Color.rgb(200,44,20));
            }
            else
            {
                // Set the background color for alternate row/item
                view.findViewById(R.id.tag).setBackgroundColor(Color.rgb(140,170,30));
            }
            return view;
        }
        };
        ListView lista = (ListView) findViewById(R.id.myDate);




        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(mcv.getContext(), ListItemDetail.class);
                int i = (int)id;
                intent.putExtra("id",i);

                startActivity(intent);

            }
        });

    }
    public void addEventos (final CalendarDay date){
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);
        final MaterialCalendarView mcv = (MaterialCalendarView) findViewById(R.id.calendarView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcv.getContext(), EventActivity.class);
                ////intent.putExtra("data",DateFormat.getDateInstance(DateFormat.SHORT).format(date.getDate()));
                intent.putExtra("data",formataData(date.getDate()));

                startActivityForResult(intent, 1);

            }
        });
    }

}



