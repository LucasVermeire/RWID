package be.helmo.rwid.presenter;

import java.util.Calendar;

public interface IAddEventPresenter extends IBasePresenter {
    void addEvent(String name, String statut, Calendar dateEnd);
}
