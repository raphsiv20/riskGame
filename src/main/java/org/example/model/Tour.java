package org.example.model;

public class Tour {
    private Manche manche;

    private int numTour;

    public Tour(Manche manche) {
        this.manche = manche;
        this.numTour = 1;
    }

    public Manche getManche() {
        return manche;
    }

    public void setManche(Manche manche) {
        this.manche = manche;
    }

    public int getNumTour() {
        return numTour;
    }

    public void setNumTour(int numTour) {
        this.numTour = numTour;
    }
}
