package be.helmo.rwid.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import be.helmo.rwid.R;
import be.helmo.rwid.presenter.CameraXPresenter;
import be.helmo.rwid.presenter.ICameraPresenter;
import be.helmo.rwid.repository.EventRepo;

public class CameraXView extends AppCompatActivity implements ICameraXView {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_MODE = 1001;
    private ImageView imageView;
    private Uri imageUri;
    private String pathImage;
    private EditText commentaire;
    private ICameraPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_x);

        presenter = new CameraXPresenter(this);

        imageView = findViewById(R.id.image_view);
        Button captureBtn = findViewById(R.id.capture_image_btn);
        commentaire = findViewById(R.id.commentaireTxt);
        Button addPhotoAndCommmentary = findViewById(R.id.addPhotoAndCommmentary);
        TextView titlePhoto = findViewById(R.id.titlePhoto);
        TextView statusPhoto = findViewById(R.id.StatusPhoto);


        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.FRENCH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        String time = dateFormat.format(new Date()).substring(11);

        EventRepo repo = new EventRepo();
        int id = getEventIdFromIntent(savedInstanceState);
        List<String> names = repo.getNames(id);
        List<String> status = repo.getStatus(id);
        titlePhoto.setText(String.format("%s - ", names.get(0)));
        statusPhoto.setText(status.get(0));

        captureBtn.setOnClickListener(v -> presenter.camera());
        addPhotoAndCommmentary.setOnClickListener(v -> presenter.addPhotoTimeLine(pathImage,commentaire.getText().toString(),time,id));
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.openCamera();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            imageView.setImageURI(imageUri);
            pathImage = imageUri.toString();
        }
    }

    @Override
    public ContentResolver getGetContentResolver(){
        return getContentResolver();
    }

    @Override
    public void setImgUri(Uri insert) {
        this.imageUri = insert;
    }

    @Override
    public Uri getImageUri() {
        return imageUri;
    }

    @Override
    public int getIMAGE_CAPTURE_MODE() {
        return IMAGE_CAPTURE_MODE;
    }

    @Override
    public void SetStartActivityForResult(Intent cameraIntent, int image_capture_mode) {
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_MODE);
    }

    @Override
    public int getPERMISSION_CODE() {
        return PERMISSION_CODE;
    }

    @Override
    public void SetRequestPermissions(String[] permission, int permission_code) {
        requestPermissions(permission,permission_code);
    }

    @Override
    public String getPathImage(){
        return pathImage;
    }

    @Override
    public String getCommentary(){
        return commentaire.getText().toString();
    }

    @Override
    public void setToast(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void backMenu() {
        onBackPressed();
    }

    public int getEventIdFromIntent(Bundle savedInstanceState){
        String newString ;
        String value = "KeyEvent";
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString(value);
                System.out.println(newString);
            }
        } else {
            newString= (String) savedInstanceState.getSerializable(value);
        }
        assert newString != null;
        return Integer.parseInt(newString);
    }
}
