package org.example.model;
import java.util.ArrayList;

import static org.example.controller.Gestion_BDD.insertNombreTerritoire;

import java.util.HashMap;
import java.util.List;

public class Joueur {
    private int idJoueur;
    private Equipe equipeJoueur;
    private String nomJoueur;
    private String prenomJoueur;
    private List<Territoire> territoiresOccupes;
    private List<Continent> continentsConquis;
    private int soldatsADeployer;
    private ArrayList<CarteTerritoire> listeCarteTerritoire;
    private boolean actif;
    private int ptsConquerant;

    public Joueur(String nomJoueur, String prenomJoueur, Equipe equipeJoueur, int idJoueur, int soldatsADeployer) {
        this.idJoueur = idJoueur;
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.equipeJoueur = equipeJoueur;
        this.territoiresOccupes = new ArrayList<>();
        this.continentsConquis = new ArrayList<>();
        this.listeCarteTerritoire = new ArrayList<>();
        this.equipeJoueur.addJoueur(this);
        this.actif = false;
        this.soldatsADeployer = soldatsADeployer;
    }

    public boolean getAtif() {
        return this.actif;
    }

    public void setActif (boolean actif) {
        this.actif = actif;
    }

    public void addSoldatsAdeployer(int nouveauxSoldatsAdeployer){
        this.soldatsADeployer += nouveauxSoldatsAdeployer;
    }

    /**
     *
     * @param soldatsDeployés le nombre de soldats que le joueur souhaite déployer
     */
    public void removeSoldatsAdeployer(int soldatsDeployés) {
        this.soldatsADeployer -= soldatsDeployés;

        listeCarteTerritoire = new ArrayList<CarteTerritoire>();
    }

    public Equipe getEquipeJoueur() {
        return equipeJoueur;
    }

    public void setEquipeJoueur(Equipe equipeJoueur) {
        this.equipeJoueur = equipeJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public String getPrenomJoueur() {
        return prenomJoueur;
    }

    public void setPrenomJoueur(String prenomJoueur) {
        this.prenomJoueur = prenomJoueur;
    }



    public List<Territoire> getTerritoiresOccupes() {
        return territoiresOccupes;
    }

    public void setTerritoiresOccupes(List<Territoire> territoiresOccupes) {
        this.territoiresOccupes = territoiresOccupes;
    }

    public List<Continent> getContinentsConquis() {
        return continentsConquis;
    }

    public void setContinentsConquis(List<Continent> continentsConquis) {
        this.continentsConquis = continentsConquis;
    }

    public int getSoldatsADeployer() {
        return soldatsADeployer;
    }

    public void setSoldatsADeployer(int soldatsADeployer) {
        this.soldatsADeployer = soldatsADeployer;
    }

    public ArrayList<CarteTerritoire> getListeCarteTerritoire() {
        return listeCarteTerritoire;
    }

    public void setListeCarteTerritoire(ArrayList<CarteTerritoire> listeCarteTerritoire) {
        this.listeCarteTerritoire = listeCarteTerritoire;
    }

    public boolean isActif() {
        return actif;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void addTerritoire(Territoire territoireConquis) {
        this.territoiresOccupes.add(territoireConquis);
        territoireConquis.setJoueurOccupant(this);
    }

    public void removeTerritoire(Territoire territoirePerdu) {
        this.territoiresOccupes.remove(territoirePerdu);
    }
    public void addContinent(Continent continentConquis) {
        this.continentsConquis.add(continentConquis);
        continentConquis.setJoueurOccupant(this);
    }

    public void removeContinent(Continent continentPerdu) {
        this.continentsConquis.remove(continentPerdu);
    }

    public void addCarteTerritoire(CarteTerritoire carteTerritoire) {
        this.listeCarteTerritoire.add(carteTerritoire);
        carteTerritoire.setJoueurDetenantLaCarte(this);
    }

    public void removeCarteTerritoire(CarteTerritoire carteTerritoire) {
        this.listeCarteTerritoire.remove(carteTerritoire);
        carteTerritoire.setJoueurDetenantLaCarte(null);
    }

    public void gainSoldats() {
        if (this.territoiresOccupes.size()%3 < 3) {
            this.addSoldatsAdeployer(3);
        }
        else {
            int newSoldats = 0;
            if (!continentsConquis.isEmpty()) {
                for (Continent continent : continentsConquis) {
                    newSoldats += continent.getBonusContinent();
                }
            }
            this.addSoldatsAdeployer(this.territoiresOccupes.size() % 3 + newSoldats);
        }
    }

    //une methode boolean avec echange possible et ensuite

    public boolean echangeCartePossible() {
        int nbFantassins = 0;
        int nbCavaliers = 0;
        int nbCanons = 0;
        for (CarteTerritoire carteTerritoire: listeCarteTerritoire) {
            if (carteTerritoire.getTypeCarte().equals("fantassin")) {
                nbFantassins++;
            } else if (carteTerritoire.getTypeCarte().equals("cavalier")) {
                nbCavaliers++;
            } else {
                nbCanons++;
            }
        }
        return (nbCanons >= 1 && nbCavaliers >= 1 && nbFantassins >= 1) || (nbCanons >= 3 || nbCavaliers >= 3 || nbFantassins >= 3);
    }

    public List<CarteTerritoire> echangerCarteTerritoires() {
        List<CarteTerritoire> cartesEchangees = new ArrayList<>();
        HashMap<String, List<CarteTerritoire>> cartesTerritoireClasses = new HashMap<String, List<CarteTerritoire>>();
        cartesTerritoireClasses.put("fantassin", new ArrayList<CarteTerritoire>());
        cartesTerritoireClasses.put("canon", new ArrayList<CarteTerritoire>());
        cartesTerritoireClasses.put("cavalier", new ArrayList<CarteTerritoire>());
        for (CarteTerritoire carteTerritoire: listeCarteTerritoire) {
            if (carteTerritoire.getTypeCarte().equals("fantassin")) {
                cartesTerritoireClasses.get("fantassin").add(carteTerritoire);
            } else if (carteTerritoire.getTypeCarte().equals("cavalier")) {
                cartesTerritoireClasses.get("cavalier").add(carteTerritoire);
            } else {
                cartesTerritoireClasses.get("canon").add(carteTerritoire);
            }
        }
        if (!cartesTerritoireClasses.get("fantassin").isEmpty() && !cartesTerritoireClasses.get("cavalier").isEmpty() && !cartesTerritoireClasses.get("canon").isEmpty()) {
            this.addSoldatsAdeployer(10);
            cartesEchangees.add(cartesTerritoireClasses.get("fantassin").get(0));
            cartesEchangees.add(cartesTerritoireClasses.get("cavalier").get(0));
            cartesEchangees.add(cartesTerritoireClasses.get("canon").get(0));
            cartesEchangees.forEach(this::removeCarteTerritoire);
            return cartesEchangees;
        } else if (cartesTerritoireClasses.get("canon").size() >= 3) {
            this.addSoldatsAdeployer(8);
            cartesEchangees.add(cartesTerritoireClasses.get("canon").get(0));
            cartesEchangees.add(cartesTerritoireClasses.get("canon").get(1));
            cartesEchangees.add(cartesTerritoireClasses.get("canon").get(2));
            cartesEchangees.forEach(this::removeCarteTerritoire);
            return cartesEchangees;
        } else if (cartesTerritoireClasses.get("cavalier").size() >= 3) {
            this.addSoldatsAdeployer(5);
            cartesEchangees.add(cartesTerritoireClasses.get("cavalier").get(0));
            cartesEchangees.add(cartesTerritoireClasses.get("cavalier").get(1));
            cartesEchangees.add(cartesTerritoireClasses.get("cavalier").get(2));
            cartesEchangees.forEach(this::removeCarteTerritoire);
            return cartesEchangees;
        } else {
            this.addSoldatsAdeployer(3);
            cartesEchangees.add(cartesTerritoireClasses.get("fantassin").get(0));
            cartesEchangees.add(cartesTerritoireClasses.get("fantassin").get(1));
            cartesEchangees.add(cartesTerritoireClasses.get("fantassin").get(2));
            cartesEchangees.forEach(this::removeCarteTerritoire);
            return cartesEchangees;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Joueur joueur) {
            return this.getIdJoueur() == joueur.getIdJoueur();
        }
        return false;
    }
}