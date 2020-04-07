package be.helmo.rwid.presenter;

import android.content.ContentValues;

public interface ICameraPresenter extends IBasePresenter {
    void openCamera();
    void camera();
    void addPhotoTimeLine(String pathImage, String commentaire, String time, int eventId);
}
