package be.helmo.rwid.presenter;


import java.util.Calendar;
import be.helmo.rwid.database.Event;
import be.helmo.rwid.repository.EventRepo;
import be.helmo.rwid.view.AddEventView;
import be.helmo.rwid.view.IAddEventView;

public class AddEventPresenter implements IAddEventPresenter {

    private IAddEventView view;

    public AddEventPresenter(AddEventView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void addEvent(String name, String statut, Calendar dateEnd) {

        Event event = new Event();
        event.setName(name);
        event.setStatus(statut);
        event.setDateEnd(dateEnd);

        EventRepo events = new EventRepo();
        events.addEvent(event);
        view.setToast("Soirée ajouté avec succès !");
    }
}
