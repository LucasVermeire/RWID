package be.helmo.rwid.database;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.TimeZone;

public class DateTypeConverters {
    @TypeConverter
    public static Calendar fromTimestamp(Long value) {
        return value == null ? null :Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
    }

    @TypeConverter
    public static Long dateToTimestamp(Calendar date) {
        return date == null ? null : date.getTime().getTime();
    }
}