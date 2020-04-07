package be.helmo.rwid.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "photo")
public class Photo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "path")
    private String path;

    @ColumnInfo(name = "commentary")
    private String commentary;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "eventId")
    private int eventId;

    public void setTime(String time) {
        this.time = time;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTime() {
        return time;
    }

    public int getEventId() {
        return eventId;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }


}
