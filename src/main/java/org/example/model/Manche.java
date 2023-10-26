package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Manche {
    private int numManche;
    private HashMap<Manche,Tour> listeTour;
    public Manche(int numManche) {
        this.numManche = numManche;
        this.listeTour = new HashMap<Manche,Tour>();
    }

    public void addMancheTour(Manche manche , Tour tour){
        listeTour.put(manche,tour);
    }

    public void changementManche (){
        if (listeTour.containsValue(4) == true){
            for (Manche entry: listeTour.keySet()
                 ) {
                entry.setterNumManche(entry.getnumManche() + 1);
                listeTour.get(entry).setNumTour(1);
            }
        }
    }

    public void afficherManche(){

    }

    public void setterNumManche(int numManche) {
        this.numManche = numManche;
    }

    public int getnumManche() {
        return numManche;
    }
}
