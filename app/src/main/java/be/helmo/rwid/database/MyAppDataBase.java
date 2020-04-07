package be.helmo.rwid.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Event.class,Photo.class},version = 1,exportSchema = false)
@TypeConverters({DateTypeConverters.class})
public abstract class MyAppDataBase extends RoomDatabase {
    public abstract EventDao eventDao();
    public abstract PhotoDao photoDao();

}