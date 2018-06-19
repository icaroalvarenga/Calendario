package com.example.icaro.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
    String items[]=new String[]{"a","b"};

    HashSet<CalendarDay> dates = new HashSet<>();
    final HashSet<CalendarDay> dates2 = new HashSet<>();

    //final HashSet<CalendarDay> dataDeProvas = new HashSet<>();

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
                                            //Button btnMaisContent = (Button) findViewById(R.id.btnMaisContent);





                                            final Intent intent = new Intent(mcv.getContext(), EventActivity.class);
                                            final int id2=0;
                                            final int tipo=0;
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
                                                Log.d("teste", tipo.getText().toString());

                                                if(tipo.getText().toString().equals("0"))
                                                {
                                                    // Set a background color for ListView regular row/item
                                                    view.setBackgroundColor(Color.parseColor("#ffff8080"));
                                                }
                                                else
                                                {
                                                    // Set the background color for alternate row/item
                                                    view.setBackgroundColor(Color.parseColor("#FF4db8ff"));
                                                }
                                                return view;
                                            }
                                            };
                                            ListView lista = (ListView) findViewById(R.id.myDate);




                                            lista.setAdapter(adaptador);
                                            /*--------
                                            for (int i = 1; i < lista.getCount(); i++) {
                                                TextView tv = (TextView)lista.getChildAt(i);
                                                String teste;
                                                teste=tv.findViewById(R.id.tipoView).toString();
                                                if(teste.equals(0));
                                                tv.setBackgroundColor(Color.RED);
                                            }




                                            /*--------*/

                                            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view,
                                                                        int position, long id) {
                                                    Intent intent = new Intent(mcv.getContext(), ListItemDetail.class);
                                                    int i = (int)id;
                                                    intent.putExtra("id",i);

                                                    startActivity(intent);
                                                    Log.d("teste", String.valueOf(id));
                                                }
                                            });

                                           /* btnMaisContent.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(mcv.getContext(), EventActivity.class);



                                                    String[] nomeCampos = new String[] {"titulo", "descricao","ano", "mes","dia","horaInit","horaFim"};
                                                    int[] idViews = new int[] {R.id.evTitulo, R.id.evDescricao, R.id.evAno,R.id.evMes,R.id.evDia,R.id.horaInit,R.id.horaFim};
                                                    SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                                                            R.layout.eventos_edit,cursor,nomeCampos,idViews, 0);



                                                    startActivityForResult(intent, 1);
                                                }
                                            });
*/


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




        mcv.addDecorators(new EventDecoratorEventos(Color.RED,dtb.carregaDadosProva()));
        mcv.addDecorators(new EventDecoratorEventos(Color.GREEN,dtb.carregaDadosTrabalho()));

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
                Log.d("teste", dateSelected);
                ano= Integer.parseInt(dateSelected.substring(0,4));
                mes= Integer.parseInt(dateSelected.substring(4,6));
                dia= Integer.parseInt(dateSelected.substring(6,8));
                int u=data.getIntExtra("tipo",-1);
                //da=ConvertToDate(dateSelected);
                CalendarDay cday = new CalendarDay(ano,mes-1,dia);
                if(u==0){
                dates.add(cday);
                mcv.addDecorators(new EventDecoratorEventos(Color.RED, dates));}
                if(u==1) {
                    dates2.add(cday);
                    mcv.addDecorators(new EventDecoratorEventos(Color.GREEN, dates2));
                }
            }


            }
        }




    }





