package com.kale.adminninebala.ActivityManager;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.kale.adminninebala.BaseApp;
import com.kale.adminninebala.R;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarContent extends BaseApp {
    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_content);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        //Set an event for Teachers' Professional Day 2016 which is 21st of October
        Event ev1 = new Event(Color.RED, 1547053200000L, "Teachers' Professional Day");
        Event ev2 = new Event(Color.RED, 1547226000000L, "Teachers' Professional Day");
        compactCalendar.addEvent(ev1);
        compactCalendar.addEvent(ev2);
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                if (dateClicked.toString().compareTo("") == 1) {
                    Toast.makeText(context, "hari" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, ""+dateClicked , Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }
}
