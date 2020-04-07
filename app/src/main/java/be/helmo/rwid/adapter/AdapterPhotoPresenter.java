package be.helmo.rwid.adapter;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import be.helmo.rwid.R;
import be.helmo.rwid.database.Photo;
import be.helmo.rwid.model.PhotoData;
import be.helmo.rwid.repository.PhotoRepo;
import be.helmo.rwid.view.ListView;
import be.helmo.rwid.view.MainActivity;

public class AdapterPhotoPresenter implements IAdapterPhotoPresenter {

    private Context context;

    public AdapterPhotoPresenter(Context context){
        this.context = context;
    }

    public void deletePhoto(ImageButton button, PhotoData photoData, PhotoRepo photoRepo, Photo photoDeleted){
        button.setOnClickListener(v -> {
            photoDeleted.setId(photoData.getId());

            AlertDialog.Builder myPopup = new AlertDialog.Builder(context);
            myPopup.setTitle("Supprimer une photo");
            myPopup.setMessage("Confirmez-vous la suppresion ?");
            myPopup.setPositiveButton("OUI", (dialog, which) -> {
                photoRepo.deletePhoto(photoDeleted);
                Toast.makeText(this.context,"Photo supprimée",Toast.LENGTH_LONG).show();
                reloadFragment();
            });
            myPopup.setNegativeButton("NON", (dialog, which) -> Toast.makeText(this.context, "La photo n'a pas été supprimé !",Toast.LENGTH_SHORT).show());

            myPopup.show();
        });
    }

    private void reloadFragment(){
        MainActivity.getFtManager().beginTransaction().replace(R.id.fragment_container, new ListView())
                .addToBackStack(null).commit();
    }
}
