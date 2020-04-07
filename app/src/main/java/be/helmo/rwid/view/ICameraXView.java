package be.helmo.rwid.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;

public interface ICameraXView {

    ContentResolver getGetContentResolver();

    void setImgUri(Uri insert);

    Uri getImageUri();

    int getIMAGE_CAPTURE_MODE();

    void SetStartActivityForResult(Intent cameraIntent, int image_capture_mode);

    int getPERMISSION_CODE();

    void SetRequestPermissions(String[] permission, int permission_code);
    String getPathImage();
    String getCommentary();
    void setToast(String message);

    void backMenu();
}
