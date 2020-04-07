package be.helmo.rwid.repository;

import java.util.List;

import be.helmo.rwid.R;
import be.helmo.rwid.database.Event;
import be.helmo.rwid.model.EventData;
import be.helmo.rwid.view.ListView;
import be.helmo.rwid.view.MainActivity;

public class EventRepo{

    public EventRepo(){
    }

    public void addEvent(Event event) {
        MainActivity.getMyAppDataBase().eventDao().addEvent(event);

        MainActivity.getFtManager().beginTransaction().replace(R.id.fragment_container, new ListView())
                .addToBackStack(null).commit();
    }

    public void deleteEvent(Event event){
        MainActivity.getMyAppDataBase().eventDao().deleteEvent(event);
    }

    public void updateStatus(Event event){
        MainActivity.getMyAppDataBase().eventDao().updateStatus(event);
    }

    public List<String> getNames(int id){
        return MainActivity.getMyAppDataBase().eventDao().getNames(id);
    }

    public List<String> getStatus(int id){
        return MainActivity.getMyAppDataBase().eventDao().getStatus(id);
    }
}
