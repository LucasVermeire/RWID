package be.helmo.rwid.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.List;
import be.helmo.rwid.R;
import be.helmo.rwid.database.Photo;
import be.helmo.rwid.model.PhotoData;
import be.helmo.rwid.repository.EventRepo;
import be.helmo.rwid.repository.PhotoRepo;
import be.helmo.rwid.view.AddEventView;
import be.helmo.rwid.view.CameraXView;

public class AdapterPhotoView implements ListAdapter {
    private List<PhotoData> photoDataList;
    private Context context;
    private AdapterPhotoPresenter presenter;


    public AdapterPhotoView(Context context, List<PhotoData> photoDataList){
        this.photoDataList = photoDataList;
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return photoDataList.size() ;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PhotoData photoData = photoDataList.get(position);

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.time_line_item, null);
            convertView.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
            });

            TextView hour = convertView.findViewById(R.id.hourTM);
            ImageView photo = convertView.findViewById(R.id.photoTL);
            TextView commentaire = convertView.findViewById(R.id.commentaireTxt);
            ImageButton deleteBtn = convertView.findViewById(R.id.deleteBTN);

            hour.setText(photoData.getTime());
            commentaire.setText(photoData.getCommentary());
            photo.setImageURI(Uri.parse(photoData.getPath()));

            presenter = new AdapterPhotoPresenter(context);
            presenter.deletePhoto(deleteBtn,photoData,new PhotoRepo(),new Photo());
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
