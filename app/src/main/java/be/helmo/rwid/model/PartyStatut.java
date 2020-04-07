package be.helmo.rwid.model;

public enum PartyStatut {

    FINISH("Terminé"), ON_WAY("En cours");

    private String name;


    PartyStatut(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
