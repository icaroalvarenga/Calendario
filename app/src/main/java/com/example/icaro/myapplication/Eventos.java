package com.example.icaro.myapplication;

import android.content.Context;
import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.HashSet;

public class Eventos extends MainActivity{


    public HashSet<CalendarDay> getEventosNaoAula(){
        final HashSet<CalendarDay> dates = new HashSet<>();
        dates.add(CalendarDay.from(2018, 02, 04));
        dates.add(CalendarDay.from(2018, 02, 11));
        dates.add(CalendarDay.from(2018, 02, 18));
        dates.add(CalendarDay.from(2018, 02, 25));
        dates.add(CalendarDay.from(2018, 02, 03));
        dates.add(CalendarDay.from(2018, 02, 10));
        dates.add(CalendarDay.from(2018, 02, 31));

        dates.add(CalendarDay.from(2018, 03, 01));
        dates.add(CalendarDay.from(2018, 03, 8));
        dates.add(CalendarDay.from(2018, 03, 15));
        dates.add(CalendarDay.from(2018, 03, 22));
        dates.add(CalendarDay.from(2018, 03, 29));
        dates.add(CalendarDay.from(2018, 03, 21));
        dates.add(CalendarDay.from(2018, 03, 28));

        dates.add(CalendarDay.from(2018, 04, 06));
        dates.add(CalendarDay.from(2018, 04, 13));
        dates.add(CalendarDay.from(2018, 04, 20));
        dates.add(CalendarDay.from(2018, 04, 27));
        dates.add(CalendarDay.from(2018, 04, 12));

        dates.add(CalendarDay.from(2018, 05, 03));
        dates.add(CalendarDay.from(2018, 05, 10));
        dates.add(CalendarDay.from(2018, 05, 17));
        dates.add(CalendarDay.from(2018, 05, 24));
        dates.add(CalendarDay.from(2018, 05, 02));
        dates.add(CalendarDay.from(2018, 05, 16));

        dates.add(CalendarDay.from(2018, 06, 01));
        dates.add(CalendarDay.from(2018, 06, 8));
        dates.add(CalendarDay.from(2018, 06, 15));
        dates.add(CalendarDay.from(2018, 06, 22));
        dates.add(CalendarDay.from(2018, 06, 14));
        dates.add(CalendarDay.from(2018, 06, 21));

        return dates;
    }
    public HashSet<CalendarDay> getEventosFeriados(){
        final HashSet<CalendarDay> dates = new HashSet<>();
        dates.add(CalendarDay.from(2018, 02, 30));
        dates.add(CalendarDay.from(2018, 03, 21));
        dates.add(CalendarDay.from(2018, 03, 30));
        dates.add(CalendarDay.from(2018, 04, 1));
        dates.add(CalendarDay.from(2018, 04, 31));
        dates.add(CalendarDay.from(2018, 05, 1));
        dates.add(CalendarDay.from(2018, 05, 13));


return dates;


    }


}
