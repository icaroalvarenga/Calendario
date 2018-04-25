package com.example.icaro.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

public class ListSqliteActivity extends Activity {


    private SQLiteDatabase database;
    private CursorAdapter dataSource;

    //Atenção: é necessário inserir o PK (chave primária _id) como último campo
    private static final String campos[] = {"ano", "mes", "dia", "_id"};

    ListView listView;
    Database helper;

    /** Chamado quando a Activity é exeutada pela primeira vez. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos);

        listView = (ListView) findViewById(R.id.calendarView);

        //cria instância da classe BaseDAO, responsável pela criação do Banco de Dados
        helper = new Database(this);

        //executa rotinas internas para abrir/utilizar o banco de dados
        database = helper.getWritableDatabase();
    }

   public void addButton(View v){
        //insere dados no banco de dados
        database.execSQL("INSERT INTO feriado (dia, mes, ano) VALUES " +
                "('25', '4', '2018')");

       database.execSQL("INSERT INTO feriado (dia, mes, ano) VALUES " +
               "('26', '4', '2018')");
        Toast.makeText(this, "Registros inseridos com sucesso", Toast.LENGTH_SHORT).show();
    }

    public ArrayList getEventos(int dia, int mes, int ano){

        //executa consulta geral de todos os registros cadastrados no banco de dados
        ArrayList datas=new ArrayList();
        String whereClause = "dia = ? and mes = ? and ano = ? ";
        String[] whereArgs = new String[] {
                String.valueOf(dia),String.valueOf(mes),String.valueOf(ano)
                };

        Cursor cursor = database.query( "feriado", null, whereClause, whereArgs,
                null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            int id2;
            int ano2;
            int dia2;
            int mes2;
            do {

                id2 =cursor.getInt(0);
                ano2 = cursor.getInt(1);
                mes2 = cursor.getInt(2);
                dia2 = cursor.getInt(3);
                datas.add(CalendarDay.from(ano2,mes2,dia2));
                //Log.d("TESTE","LIDO: "+id2+"-"+data);
            } while(cursor.moveToNext());
        }

        return datas;
    }

    //método executado quando o usuário clica no botão voltar do aparelho
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        //deleta registros inseridos, simplesmente para limpar essa base que é de teste
        database.execSQL("DELETE FROM feriado");

        //fecha a conexão com o Banco de dados
        database.close();
    }
}
