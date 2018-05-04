package com.example.icaro.myapplication;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class EventDecoratorFeriado implements DayViewDecorator {

    private final int color;
    private final HashSet<CalendarDay> dates;
    private final Context context;

    public EventDecoratorFeriado(Context i, int color, Collection<CalendarDay> dates) {
        this.color = color;
        this.dates = new HashSet<>(dates);
        this.context = i;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(color));
        //view.addSpan(new BackgroundColorSpan(Color.BLUE));
        view.setBackgroundDrawable(ContextCompat.getDrawable(context ,R.drawable.rectangle_orange));
    }
}

