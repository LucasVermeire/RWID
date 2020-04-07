package be.helmo.rwid.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import be.helmo.rwid.model.PhotoData;

@Dao
public interface PhotoDao {

    @Query("SELECT * FROM photo WHERE eventId=:id")
    List<PhotoData> getPhotoList(int id);

    @Insert
    void addPhoto(Photo photo);

    @Delete
    void deletePhoto (Photo photo);

    @Query("SELECT path FROM PHOTO WHERE eventId=:id")
    List<String> getPathImage(int id);

}
