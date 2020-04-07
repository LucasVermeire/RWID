package be.helmo.rwid.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Calendar;

@Entity(tableName = "Event")
public class Event {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "idImage")
    private String idImage;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "dateEnd")
    private Calendar dateEnd;

    //--------------------------------

    public void setId (int id){
        this.id = id;
    }

    public void setIdImage (String idImage){
        this.idImage = idImage;
    }

    public void setName (String name){
        this.name = name;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setDateEnd(Calendar date){
        this.dateEnd = date;
    }

    //-----------------------------------

    public int getId() {
        return this.id;
    }

    String getIdImage() {
        return this.idImage;
    }

    public String getName() {
        return this.name;
    }

    String getStatus(){return this.status;}

    Calendar getDateEnd(){return this.dateEnd;}

}

