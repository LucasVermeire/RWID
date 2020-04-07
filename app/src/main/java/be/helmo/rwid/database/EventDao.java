package be.helmo.rwid.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import be.helmo.rwid.model.EventData;
import be.helmo.rwid.model.PhotoData;

@Dao
public interface EventDao {

    @Insert
    void addEvent(Event event);

    @Query("SELECT * FROM Event")
    List<EventData> getEvents();

    @Delete
    void deleteEvent(Event event);

    @Update
    void updateStatus(Event event);

    @Query("SELECT name FROM Event WHERE id=:id")
    List<String> getNames(int id);

    @Query("SELECT status FROM Event WHERE id=:id")
    List<String> getStatus(int id);
}
