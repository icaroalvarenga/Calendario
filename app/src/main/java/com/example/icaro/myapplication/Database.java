package com.example.icaro.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.HashSet;

public class Database extends SQLiteOpenHelper {
    private SQLiteDatabase database;

    public Database(Context context) {
        super(context, "calendario", null, 6);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        // db.execSQL(" CREATE TABLE 'feriado' ('_id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'ano' INTEGER NOT NULL, 'mes' INTEGER NOT NULL, 'dia' INTEGER NOT NULL);");
        db.execSQL(" CREATE TABLE 'eventos' ('_id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'ano' STRING NOT NULL, 'mes' STRING NOT NULL, 'dia' STRING NOT NULL, " +
                "'titulo' STRING, 'descricao' STRING, 'horaInit' STRING, 'horaFim' STRING, 'tipo' STRING);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       // db.execSQL("DROP TABLE IF EXISTS " + "feriado");
        db.execSQL("DROP TABLE IF EXISTS " + "eventos");

        onCreate(db);
    }

    /*   public void addEvento (int ano,int mes,int dia, String titulo, String descricao, String horaInit, String  horaFim){
           database.execSQL("INSERT INTO 'eventos' ('ano', 'mes', 'dia', 'titulo', 'descricao', 'horaInit', 'horaFim') VALUES " +
                   "(ano,mes,dia,titulo,descricao,horaInit,horaFim)");
       }
    */
    public boolean insertData(String ano, String mes, String dia, String titulo, String descricao, String horaInit, String horaFim, String tipo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ano", ano);
        contentValues.put("mes", mes);
        contentValues.put("dia", dia);
        contentValues.put("titulo", titulo);
        contentValues.put("descricao", descricao);
        contentValues.put("horaInit", horaInit);
        contentValues.put("horaFim", horaFim);
        contentValues.put("tipo", tipo);

        long result = db.insert("eventos", null, contentValues);
        if (result == -1)
            return false;
        else {
            Log.d("teste", "true");
            return true;

        }
    }

    public Cursor carregaDados() {
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();


        cursor = db.query("eventos", null, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor carregaDados(int id){
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();



        String TABLE = "eventos";
        String where = "_id=?";
        String[] args = {String.valueOf(id)};
// Execute
        cursor = db.query(TABLE,null, where,args, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public boolean updateDados(Integer id,String ano, String mes, String dia, String titulo, String descricao, String horaInit, String horaFim, String tipo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ano", ano);
        contentValues.put("mes", mes);
        contentValues.put("dia", dia);
        contentValues.put("titulo", titulo);
        contentValues.put("descricao", descricao);
        contentValues.put("horaInit", horaInit);
        contentValues.put("horaFim", horaFim);
        contentValues.put("tipo", tipo);

        //String[] args = {String.valueOf(ano), String.valueOf(mes), String.valueOf(dia), String.valueOf(titulo), String.valueOf(descricao), String.valueOf(horaInit),
               // String.valueOf(horaFim), String.valueOf(tipo)};

        long result = db.update("eventos",contentValues,"_id="+id,null);

        if (result == -1)
            return false;
        else {
            Log.d("teste", "true");
            return true;

        }
    }

     public Cursor carregaDados(int ano,int mes, int dia){
         Cursor cursor;
         SQLiteDatabase db = this.getWritableDatabase();



         String TABLE = "eventos";
         String where = "ano=? AND mes=? AND dia=?";
         String[] args = {String.valueOf(ano), String.valueOf(mes), String.valueOf(dia)};
// Execute
         cursor = db.query(TABLE,null, where,args, null, null, null);
         //cursor = db.query("eventos", null, "('ano' = "  +  ano + " AND "+ " 'mes' = " + mes + " AND " + " 'dia' = " +dia ,null, null, null, null, null);

         if(cursor!=null){
             cursor.moveToFirst();
         }
         db.close();
         return cursor;
     }

    public HashSet<CalendarDay> carregaDadosProva() {
        final HashSet<CalendarDay> dataDeProvas = new HashSet<>();
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        String TABLE = "eventos";
        String where = "tipo=?";
        String[] args = {"0"};
cursor = db.query(TABLE,null, where,args, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String ano = cursor.getString(cursor.getColumnIndex("ano"));
                String mes = cursor.getString(cursor.getColumnIndex("mes"));
                String dia = cursor.getString(cursor.getColumnIndex("dia"));
                CalendarDay cday = new CalendarDay(Integer.valueOf(ano),Integer.valueOf(mes)-1,Integer.valueOf(dia));


                dataDeProvas.add(cday);
            } while (cursor.moveToNext());
            cursor.close();
            return dataDeProvas;
        }
        cursor.close();
        return dataDeProvas;
    }
    public HashSet<CalendarDay> carregaDadosTrabalho() {
        final HashSet<CalendarDay> dataDeTrabalho = new HashSet<>();
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        String TABLE = "eventos";
        String where = "tipo=?";
        String[] args = {"1"};
        cursor = db.query(TABLE,null, where,args, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String ano = cursor.getString(cursor.getColumnIndex("ano"));
                String mes = cursor.getString(cursor.getColumnIndex("mes"));
                String dia = cursor.getString(cursor.getColumnIndex("dia"));
                CalendarDay cday = new CalendarDay(Integer.valueOf(ano),Integer.valueOf(mes)-1,Integer.valueOf(dia));


                dataDeTrabalho.add(cday);
            } while (cursor.moveToNext());
            cursor.close();
            return dataDeTrabalho;
        }
        cursor.close();
        return dataDeTrabalho;
    }




    /*   String metodo(String parametro){
           String selectQuery =
                   "SELECT * FROM correspondente WHERE num_vidas =" + parametro;

           SQLiteDatabase banco = this.getWritableDatabase();
           Cursor cursor = banco.rawQuery(selectQuery, null);

           cursor.moveToFirst();

           String nomeString = cursor.getString(cursor.getColumnIndex('nome_da_coluna'));

           StringBuilder conversor = new StringBuilder();
           conversor.append(nomeString);
           return conversor.toString();

       }
   */
    private CalendarDay ConvertToDate(String data) {
        int ano;
        int mes;
        int dia;


        ano = Integer.parseInt(data.substring(0, 4));
        mes = Integer.parseInt(data.substring(4, 6));
        dia = Integer.parseInt(data.substring(6, 8));

        CalendarDay cday = new CalendarDay(ano, mes - 1, dia);
        return cday;
    }

}




