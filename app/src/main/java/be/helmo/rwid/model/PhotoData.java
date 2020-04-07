package be.helmo.rwid.model;

public class PhotoData {

    private int id;
    private String path;
    private String commentary;
    private String time;
    private int eventId;

    public PhotoData(int id, String path, String commentary,String time, int eventId){
        this.id = id;
        this.path = path;
        this.commentary = commentary;
        this.time = time;
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getCommentary() {
        return commentary;
    }

    public String getTime() {
        return time;
    }
}
