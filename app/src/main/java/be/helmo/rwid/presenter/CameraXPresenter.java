package be.helmo.rwid.presenter;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;

import be.helmo.rwid.database.Photo;
import be.helmo.rwid.repository.PhotoRepo;
import be.helmo.rwid.view.CameraXView;
import be.helmo.rwid.view.ICameraXView;

public class CameraXPresenter implements ICameraPresenter {

    private ICameraXView view;

    public CameraXPresenter(CameraXView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        view.setImgUri(view.getGetContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values));

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, view.getImageUri());
        view.SetStartActivityForResult(cameraIntent, view.getIMAGE_CAPTURE_MODE());
    }

    @Override
    public void camera() {
        if (((AppCompatActivity) view).checkSelfPermission(Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_DENIED ||
                ((AppCompatActivity) view).checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
            String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            view.SetRequestPermissions(permission, view.getPERMISSION_CODE());
        } else {
            openCamera();
        }
    }

    @Override
    public void addPhotoTimeLine(String pathImage, String commentaire, String time, int eventId){
        Photo photo = new Photo();
        photo.setTime(time);
        photo.setCommentary(commentaire);
        photo.setPath(pathImage);
        photo.setEventId(eventId);

        PhotoRepo photoQuery = new PhotoRepo();
        photoQuery.addPhoto(photo);

        view.setToast("Photo ajouté avec succès !");

        view.backMenu();
    }
}
