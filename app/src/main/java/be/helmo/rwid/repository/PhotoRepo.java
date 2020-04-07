package be.helmo.rwid.repository;

import java.util.List;

import be.helmo.rwid.view.MainActivity;

public class PhotoRepo {

    public void addPhoto(be.helmo.rwid.database.Photo photo){
        MainActivity.getMyAppDataBase().photoDao().addPhoto(photo);
    }

    public void deletePhoto(be.helmo.rwid.database.Photo photo) {
        MainActivity.getMyAppDataBase().photoDao().deletePhoto(photo);
    }

    public List<String> getPathImage(int id){
        return MainActivity.getMyAppDataBase().photoDao().getPathImage(id);
    }
}
