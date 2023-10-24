package org.example.model;

public class Soldat {
    private int idSoldat;
    private String typeSoldat;

    public Soldat(int idSoldat, String typeSoldat){
        this.idSoldat = idSoldat;
        this.typeSoldat = typeSoldat;
    }

    public int getIdSoldat(){
        return this.idSoldat;
    }

    public String getTypeSoldat(){
        return this.typeSoldat;
    }

    public void setIdSoldat(int idSoldat){
        this.idSoldat = idSoldat;
    }

    public void setTypeSoldat(String typeSoldat){
        this.typeSoldat = typeSoldat;
    }
}
