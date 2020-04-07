package be.helmo.rwid.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class EventData implements Comparable<EventData> {

    private int id;
    private String name;
    private String status;
    private Calendar dateEnd;
    private String idImage;

    public EventData(int id ,String name,String idImage, String status, Calendar dateEnd){
        this.id = id;
        this.idImage = idImage;
        this.name = name;
        this.status = status;
        this.dateEnd = dateEnd;
        this.dateEnd.set(Calendar.MONTH, dateEnd.get(Calendar.MONTH) - 1);
    }

    public String getName () {
        return name;
    }

    public String getStatut () {return status;}

    public void setStatusFinish(){status = PartyStatut.FINISH.toString();}

    public int getID(){return id;}

    @Override
    public int compareTo(EventData o) {
        return Integer.compare(id, o.getID());
    }

    @Override
    public boolean equals(Object o){ return o instanceof EventData && id == ((EventData) o).getID();}

    @Override
    public int hashCode(){
        return id;
    }

    public String getDateToString(){
        return dateToString();
    }

    private String dateToString(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        return format.format(dateEnd.getTime());
    }

    public boolean isFinish(){
        return Calendar.getInstance().after(dateEnd);
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public String getIdImage() {
        return idImage;
    }
}
