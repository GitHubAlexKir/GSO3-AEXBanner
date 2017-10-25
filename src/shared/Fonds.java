package shared;


import shared.IFonds;

import java.io.Serializable;

public class Fonds implements IFonds, Serializable {
    private String Naam;
    private double Koers;
    public Fonds(String Naam, double Koers){
        this.Naam = Naam;
        this.Koers = Koers;
    }
    public void setKoers(double koers) {
        Koers = koers;
    }

    @Override
    public String toString() {
        return this.Naam + ":" + this.Koers + "  ";
    }
}
