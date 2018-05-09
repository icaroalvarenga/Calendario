package com.example.icaro.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private SQLiteDatabase database;

    public Database(Context context){
        super(context, "calendario", null, 3);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
       // db.execSQL(" CREATE TABLE 'feriado' ('_id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'ano' INTEGER NOT NULL, 'mes' INTEGER NOT NULL, 'dia' INTEGER NOT NULL);");
        db.execSQL(" CREATE TABLE 'eventos' ('_id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'ano' INTEGER NOT NULL, 'mes' INTEGER NOT NULL, 'dia' INTEGER NOT NULL, " +
                "'titulo' STRING, 'descricao' STRING, 'horaInit' STRING, 'horaFim' STRING);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "feriado");
        onCreate(db);
    }
 /*   public void addEvento (int ano,int mes,int dia, String titulo, String descricao, String horaInit, String  horaFim){
        database.execSQL("INSERT INTO 'eventos' ('ano', 'mes', 'dia', 'titulo', 'descricao', 'horaInit', 'horaFim') VALUES " +
                "(ano,mes,dia,titulo,descricao,horaInit,horaFim)");
    }
 */
 public boolean insertData(int ano,int mes,int dia, String titulo, String descricao, String horaInit, String  horaFim) {
     SQLiteDatabase db = this.getWritableDatabase();
     ContentValues contentValues = new ContentValues();

     contentValues.put("ano", ano);
     contentValues.put("mes", mes);
     contentValues.put("dia", dia);
     contentValues.put("titulo", titulo);
     contentValues.put("descricao", descricao);
     contentValues.put("horaInit", horaInit);
     contentValues.put("horaFim", horaFim);


     long result = db.insert("eventos", null, contentValues);
     if (result == -1)
         return false;
     else {
         Log.d("teste", "true");
         return true;

     }
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


}



