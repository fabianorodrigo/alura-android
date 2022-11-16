package br.com.aluras.agenda.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {
    @TypeConverter
    public Long toLong(Calendar calendar) {
        if (calendar != null) {
            return calendar.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long timeInMillis) {
        Calendar c = Calendar.getInstance();
        if (timeInMillis != null) {
            c.setTimeInMillis(timeInMillis);
        }
        return c;
    }
}
