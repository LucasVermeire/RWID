package be.helmo.rwid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import java.util.Calendar;
import java.util.TimeZone;
import be.helmo.rwid.R;
import be.helmo.rwid.database.Event;
import be.helmo.rwid.model.EventData;
import be.helmo.rwid.model.PartyStatut;
import be.helmo.rwid.repository.EventRepo;
import be.helmo.rwid.view.CameraXView;
import be.helmo.rwid.view.ListView;
import be.helmo.rwid.view.MainActivity;
import be.helmo.rwid.view.TimeLineView;

public class AdapterListViewPresenter implements IAdapterListViewPresenter {

    private Context context;

    AdapterListViewPresenter(Context context){
        this.context= context;
    }

    @Override
    public void eventFinishOrNot (EventData eventData, TextView statut, LinearLayout linear, EventRepo eventRepo){
        if(eventData.getStatut().equals(PartyStatut.ON_WAY.toString())){
            statut.setTextColor(Color.parseColor("#4DFF00"));

            linear.setOnClickListener(v -> {
                Intent intent = new Intent(this.context, CameraXView.class);
                intent.putExtra("KeyEvent", String.valueOf(eventData.getID()));
                this.context.startActivity(intent);
            });

            linear.setOnLongClickListener(v -> {
                Event event = new Event();
                event.setId(eventData.getID());
                event.setName(eventData.getName());
                event.setDateEnd(Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris")));
                event.setStatus(PartyStatut.FINISH.toString());

                eventRepo.updateStatus(event);

                reloadFragment();

                return true;
            });
        }else{

            linear.setOnClickListener(v -> {
                TimeLineView timeLineView = new TimeLineView();

                MainActivity.getFtManager().beginTransaction().replace(R.id.fragment_container, timeLineView)
                        .addToBackStack(null).commit();

                Bundle bundle=new Bundle();
                String str =String.valueOf(eventData.getID());
                bundle.putString("KEY2",str);
                timeLineView.setArguments(bundle);
            });

            deleteItems(linear,eventData,eventData.getName(),new EventRepo(),new Event());
        }
    }

    private void deleteItems(LinearLayout linear, EventData eventData, String nameEvent, EventRepo eventRepo, Event eventDeleted ){

        linear.setOnLongClickListener(v->{
            eventDeleted.setId(eventData.getID());

            AlertDialog.Builder myPopup = new AlertDialog.Builder(context);
            myPopup.setTitle("Supprimer une soirée");
            myPopup.setMessage("Confirmez-vous la suppresion " + nameEvent + " ?");
            myPopup.setPositiveButton("OUI", (dialog, which) -> {
                eventRepo.deleteEvent(eventDeleted);
                Toast.makeText(this.context,"Soirée supprimée",Toast.LENGTH_LONG).show();
                reloadFragment();
            });
            myPopup.setNegativeButton("NON", (dialog, which) -> Toast.makeText(this.context,nameEvent + " n'a pas été supprimé !",Toast.LENGTH_SHORT).show());

            myPopup.show();

            return true;
        });
    }

    private void reloadFragment(){
        MainActivity.getFtManager().beginTransaction().replace(R.id.fragment_container, new ListView())
                .addToBackStack(null).commit();
    }
}
