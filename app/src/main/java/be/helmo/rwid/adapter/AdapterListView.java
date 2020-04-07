package be.helmo.rwid.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.List;
import be.helmo.rwid.R;
import be.helmo.rwid.model.EventData;
import be.helmo.rwid.repository.EventRepo;
import be.helmo.rwid.repository.PhotoRepo;


public class AdapterListView implements ListAdapter {
    private List<EventData> eventDataList;
    private Context context;
    private AdapterListViewPresenter presenter;

    public AdapterListView(Context context, List<EventData> eventDataList) {
        this.eventDataList =eventDataList;
        this.context=context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return eventDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final EventData eventData = eventDataList.get(position);
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.listview_item, null);
            convertView.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
            });
            TextView title=convertView.findViewById(R.id.title);
            ImageView imageView=convertView.findViewById(R.id.icon);
            TextView statut = convertView.findViewById(R.id.statut);
            TextView date = convertView.findViewById(R.id.date);
            LinearLayout linear = convertView.findViewById(R.id.linearLayoutItem);

            title.setText(eventData.getName());
            statut.setText(eventData.getStatut());
            date.setText(eventData.getDateToString());

            PhotoRepo repo = new PhotoRepo();
            List<String> photos = repo.getPathImage(eventData.getID());

            if(photos.size()>0){
                imageView.setImageURI(Uri.parse(photos.get(0)));
            }else{
                imageView.setImageResource(R.drawable.image_defaut);
            }

            presenter = new AdapterListViewPresenter(convertView.getContext());
            presenter.eventFinishOrNot(eventData,statut,linear, new EventRepo());

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