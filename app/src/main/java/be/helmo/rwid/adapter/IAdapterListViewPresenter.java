package be.helmo.rwid.adapter;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.helmo.rwid.model.EventData;
import be.helmo.rwid.repository.EventRepo;

public interface IAdapterListViewPresenter {
    void eventFinishOrNot (EventData eventData, TextView statut, LinearLayout linear, EventRepo eventRepo);
}
