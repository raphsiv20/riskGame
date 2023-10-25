package org.example.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plateau extends AbstractModel {

    /*------------*/
    /* Proprietes */
    /*------------*/

    private int hauteur;
    private int largeur;
    private Territoire[][] territoires;
    private boolean partieTerminer;
    private List<Territoire> territoiresGame;
    private List<Continent> continentsGame;
    private List<CarteTerritoire> cartesTerritoires;
    private List<Joueur> joueurs;


    /*------------*/
    /* Constructeur */
    /*------------*/

    public Plateau() {
        this.hauteur = 10;
        this.largeur = 7;
        this.territoires = new Territoire[11][8];
        this.partieTerminer = false;
        this.territoiresGame = new ArrayList<>();
        this.continentsGame = new ArrayList<>();
        this.cartesTerritoires = new ArrayList<>();
        this.joueurs = new ArrayList<>();


        this.readFiles();

        this.continentsGame.forEach(continent -> System.out.println(continent.getContinentName()));


        this.setTerritoire(3, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(4, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(5, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(6, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(7, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(9, 0, new Territoire(TypeTerritoire.VIDE));

        this.setTerritoire(5, 1, new Territoire(TypeTerritoire.VIDE));

        this.setTerritoire(2, 2, new Territoire(TypeTerritoire.VIDE));

        this.setTerritoire(1, 3, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(2, 3, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(3, 3, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(9, 3, new Territoire(TypeTerritoire.VIDE));

        this.setTerritoire(2, 4, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(3, 4, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(6, 4, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(7, 4, new Territoire(TypeTerritoire.VIDE));

        this.setTerritoire(2, 5, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(3, 5, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(6, 5, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(7, 5, new Territoire(TypeTerritoire.VIDE));

        this.setTerritoire(0, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(1, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(2, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(3, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(6, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(7, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(8, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(9, 6, new Territoire(TypeTerritoire.VIDE));




    }

    /*------------*/
    /* Methodes */
    /*------------*/

    /**
     * The method creates 3 String[] from the files countries.txt, adjacencies.txt and continents.txt.
     * Those String[] are given to a method to then create the TerritorY and Continent objects with the initPlateau
     */
    public void readFiles() {
        List<String[]> listsCountryContAdj = new ArrayList<>();
        BufferedReader reader;
        StringBuilder stringBuilder;
        String line;
        try {
            // Reads countries file
            reader = new BufferedReader(new FileReader("src/main/java/org/example/model/countries.txt"));
            stringBuilder = new StringBuilder();
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String input = stringBuilder.toString();
            // Splits the text in the file into an array
            String[] countriesArray = input.split(";");

            // Reads adjacencies file
            reader = new BufferedReader(new FileReader("src/main/java/org/example/model/adjacencies.txt"));
            stringBuilder = new StringBuilder();

            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            input = stringBuilder.toString();

            // Creates an array of each line from the file
            String[] adjacenciesArray = input.split(";");

            // Reads continents file
            reader = new BufferedReader(new FileReader("src/main/java/org/example/model/continents.txt"));
            stringBuilder = new StringBuilder();

            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            input = stringBuilder.toString();
            String[] continentsArray = input.split(";");
            this.initPlateau(countriesArray, adjacenciesArray, continentsArray);

        } catch (FileNotFoundException error) {
            System.out.println(error.getMessage());
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }

    }

    public void initPlateau(String[] territoires, String[] territoiresVoisins, String[] continents) {
        this.addTerritories(territoires);
        this.addTerritoryNextTerritories(territoiresVoisins);
        this.addContinent(continents);
    }

    public void addTerritories(String[] territoires) {
        for (int i = 0; i < territoires.length; i++) {
            String[] countriesArray = territoires[i].split(",");
            this.territoiresGame.add(new Territoire(countriesArray[0], countriesArray[4]));
            this.cartesTerritoires.add(new CarteTerritoire(this.territoiresGame.get(i), countriesArray[1]));

            switch (countriesArray[4]) {
                case "Amérique du Nord" -> {
                    this.territoiresGame.get(i).setTypeTerritoire(TypeTerritoire.AMNORD);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.territoiresGame.get(i));
                }
                case "Amérique du Sud" -> {
                    this.territoiresGame.get(i).setTypeTerritoire(TypeTerritoire.AMSUD);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.territoiresGame.get(i));
                }
                case "Europe" -> {
                    this.territoiresGame.get(i).setTypeTerritoire(TypeTerritoire.EU);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.territoiresGame.get(i));
                }
                case "Afrique" -> {
                    this.territoiresGame.get(i).setTypeTerritoire(TypeTerritoire.AFRIQUE);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.territoiresGame.get(i));
                }
                case "Asie" -> {
                    this.territoiresGame.get(i).setTypeTerritoire(TypeTerritoire.ASIE);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.territoiresGame.get(i));
                }
                default -> {
                    this.territoiresGame.get(i).setTypeTerritoire(TypeTerritoire.AUST);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.territoiresGame.get(i));
                }

            }
        }
    }

    public void addTerritoryNextTerritories(String[] territoiresVoisins) {
        for (int i = 0; i < territoiresVoisins.length; i++) {
            List<String> adjacenciesTable = new ArrayList<>();
            List<Territoire> adjacenciesList = new ArrayList<>();
            for (int j = 1; j < territoiresVoisins[i].split(",").length; j++) {
                adjacenciesTable.add(territoiresVoisins[i].split(",")[j]);
            }
            adjacenciesList = adjacenciesTable.stream().map((this::getTerritoireByName)).toList();
            this.territoiresGame.get(i).setTerritoiresAdjacents(adjacenciesList);
        }
    }

    public void addContinent(String[] continents) {
        for (int i = 0; i < continents.length; i++) {
            String[] continentsArray = continents[i].split(",");
            this.continentsGame.add(new Continent(continentsArray[0], Integer.parseInt(continentsArray[1]), Arrays.stream(continentsArray).toList().subList(2, continentsArray.length).stream().map(this::getTerritoireByName).toList()));
        }
    }

    public Continent getContinentByName(String continentName) {
        return this.continentsGame.stream()
                .filter(continent -> continent.getContinentName().equals(continentName.replace(" ", "")))
                .toList().get(0);
    }

    public Territoire getTerritoireByName(String territoryName) {
       return this.territoiresGame.stream()
               .filter(territoire -> territoire.getTerritoireName().equals(territoryName.stripLeading()))
               .toList().get(0);
    }
    public CarteTerritoire getACarteTerritoireByTerritoireName(String countryName) {
        return this.cartesTerritoires.stream()
                .filter(carteTerritoire -> carteTerritoire.getTerritoire().getTerritoireName().equals(countryName))
                .toList().get(0);
    }

    /**
     * Affecte / retourne le type d'un territoire donnee.
     */

    @Override
    public TypeTerritoire getTypeTerritoire(int x, int y) {
        return this.territoires[x][y].getTypeTerritoire();
    }

    @Override
    public void setTypeTerritoire(int x, int y, TypeTerritoire tt) {
        this.territoires[x][y].setTypeTerritoire(tt);
    }

    /**
     * Retourne la hauteur et la largeur du labyrinthe.
     */

    @Override
    public int getLargeur() {
        return this.largeur;
    }

    @Override
    public int getHauteur() {
        return this.hauteur;
    }



    /**
     * Affecte / retourne le statu de la partie.
     */

    @Override
    public boolean partieTerminer() {
        return this.partieTerminer;
    }

    public void setPartieTerminer(boolean partieTerminer) {
        this.partieTerminer = partieTerminer;
    }

    /**
     * Affecte / retourne le territoire en position i,j.
     */

    public void setTerritoire (int i, int j, Territoire c) {
        this.territoires[i][j] = c;
    }

    public Territoire getTerritoire (int i, int j) {
        return this.territoires[i][j];
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public Territoire[][] getTerritoires() {
        return territoires;
    }

    public void setTerritoires(Territoire[][] territoires) {
        this.territoires = territoires;
    }

    public boolean isPartieTerminer() {
        return partieTerminer;
    }

    public List<Territoire> getTerritoiresGame() {
        return territoiresGame;
    }

    public void setTerritoiresGame(List<Territoire> territoiresGame) {
        this.territoiresGame = territoiresGame;
    }

    public List<Continent> getContinentsGame() {
        return continentsGame;
    }

    public void setContinentsGame(List<Continent> continentsGame) {
        this.continentsGame = continentsGame;
    }

    public List<CarteTerritoire> getCartesTerritoires() {
        return cartesTerritoires;
    }

    public void setCartesTerritoires(List<CarteTerritoire> cartesTerritoires) {
        this.cartesTerritoires = cartesTerritoires;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
}
