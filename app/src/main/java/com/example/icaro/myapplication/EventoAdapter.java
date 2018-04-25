package com.example.icaro.myapplication;

import android.content.Context;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EventoAdapter extends ArrayAdapter<Evento> {

    private Context mContext;
    private List<Evento> eventoList = new ArrayList<>();

    public EventoAdapter( Context context,  ArrayList<Evento> list) {
        super(context, 0 , list);
        mContext = context;
        eventoList = list;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, @NonNull  ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.lista_eventos,parent,false);

        Evento evento = eventoList.get(position);


        TextView name = (TextView) listItem.findViewById(R.id.diaView);
        String dataString = evento.getDia()+"/"+evento.getMes()+"/"+evento.getAno();
        name.setText(dataString);



        return listItem;
    }

}

