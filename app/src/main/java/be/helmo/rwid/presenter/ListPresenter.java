package be.helmo.rwid.presenter;


import java.util.List;

import be.helmo.rwid.adapter.AdapterListView;
import be.helmo.rwid.database.Event;
import be.helmo.rwid.model.EventData;
import be.helmo.rwid.model.PartyStatut;
import be.helmo.rwid.repository.EventRepo;
import be.helmo.rwid.view.IListView;
import be.helmo.rwid.view.ListView;
import be.helmo.rwid.view.MainActivity;

public class ListPresenter implements IListPresenter{

    private IListView view;

    public ListPresenter(ListView view){
        this.view = view;
    }

    @Override
    public void onCreate() {
        List<EventData> eventsList = MainActivity.getMyAppDataBase().eventDao().getEvents();

//        eventsList.forEach(eventData ->{
//            if(eventData.isFinish()) {
//            eventData.setStatusFinish();
//            Event even = new Event();
//            even.setId(eventData.getID());
//            even.setDateEnd(eventData.getDateEnd());
//            even.setIdImage(eventData.getIdImage());
//            even.setName(even.getName());
//            even.setStatus(eventData.getStatut());
//            EventRepo events = new EventRepo();
//            events.updateStatus(even);
//        }});

        AdapterListView customAdapter = new AdapterListView(view.getContext(), eventsList);
        view.getList().setAdapter(customAdapter);
    }
}