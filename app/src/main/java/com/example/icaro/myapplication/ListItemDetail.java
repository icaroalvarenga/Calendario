package com.example.icaro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.HashSet;

public class ListItemDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos_edit);
        final Database database = new Database(this);
        final String anost;
        final String messt;
        final String diast;
        final TextView ano = (TextView) findViewById(R.id.evAno);                          //
        final TextView mes = (TextView) findViewById(R.id.evMes);                          //
        final TextView dia = (TextView) findViewById(R.id.evDia);                        //
        //CheckBox duracao = (CheckBox) findViewById(R.id.chkDuracao);            // Instanciacion de recursos
        final TextView horaInit = (TextView) findViewById(R.id.horaInit);
        final TextView horaFim = (TextView) findViewById(R.id.horaFim);
        final EditText titulo = (EditText) findViewById(R.id.evTitulo);
        final Spinner dropdown = (Spinner) findViewById(R.id.tipoEvento);
        final EditText descricao = (EditText) findViewById(R.id.evDescricao);   //
        Intent intent = getIntent();
        final int id = intent.getIntExtra("id",0);

        String[] items = new String[]{"Prova", "Trabalho"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Database crud = new Database(getBaseContext());
        final Cursor cursor = crud.carregaDados(id);

        int index = cursor.getColumnIndexOrThrow("titulo");
        titulo.setText(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("descricao");
        descricao.setText(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("ano");
        ano.setText(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("mes");
        mes.setText(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("dia");
        dia.setText(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("horaInit");
        horaInit.setText(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("horaFim");
        horaFim.setText(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("tipo");
        final int i = Integer.parseInt(cursor.getString(index));

        dropdown.setSelection(i);



        Button update = (Button) findViewById(R.id.btnUpdateEvento);



        final EditText timeEditText = (EditText) findViewById(R.id.horaInit);
        new SetTime(timeEditText);
        final EditText timeEditText2 = (EditText) findViewById(R.id.horaFim);
        new SetTime(timeEditText2);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=dropdown.getSelectedItemPosition();
                String j=Integer.toString(i);
                database.updateDados(id,ano.getText().toString(),mes.getText().toString(),dia.getText().toString(),titulo.getText().toString(), descricao.getText().toString(), horaInit.getText().toString(), horaFim.getText().toString(),j);



                finish();
            }
        });
    }

        /*String[] nomeCampos = new String[] {"titulo", "descricao","horaInit","horaFim","tipo","ano","mes","dia"};
        int[] idViews = new int[] {R.id.evTitulo, R.id.evDescricao,R.id.horaInit,R.id.horaFim,R.id.tipoEvento,R.id.evAno,R.id.evMes,R.id.evDia};
        final SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.eventos ,cursor,nomeCampos,idViews, 0);
        ListView lista = (ListView) findViewById(R.id.myDate);
        lista.setAdapter(adaptador);*/

}
