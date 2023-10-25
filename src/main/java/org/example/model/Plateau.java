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

    /*private int hauteur;
    private int largeur;
    private Territoire[][] territoires;
    private boolean partieTerminer;
    private List<Territoire> territoiresGame;
    private List<Continent> continentsGame;
    private List<CarteTerritoire> cartesTerritoires;
    private List<Joueur> joueurs;
    private Tour tours;
    private List<Equipe> equipes;*/


    /*------------*/
    /* Constructeur */
    /*------------*/

    public Plateau(int hauteur, int largeur) {
        super(hauteur,largeur);
        /*this.hauteur = 10;
        this.largeur = 7;
        this.territoires = new Territoire[11][8];
        this.partieTerminer = false;
        this.territoiresGame = new ArrayList<>();
        this.continentsGame = new ArrayList<>();
        this.cartesTerritoires = new ArrayList<>();
        this.joueurs = new ArrayList<>();
        this.equipes = new ArrayList<>();
        this.tours = new Tour(1);*/
        this.readFiles();

        System.out.println("----------------------------------\n");
        this.getCartesTerritoires().forEach(carteTerritoire -> System.out.println(carteTerritoire.getTerritoire().getTerritoireName() +" " +carteTerritoire.getTypeCarte()));
        System.out.println("----------------------------------\n");
        this.getEquipes().forEach(equipe -> equipe.getJoueursEquipe().forEach(joueur -> System.out.println(equipe.getNomEquipe() + ": " + joueur.getNomJoueur())));
        System.out.println("----------------------------------\n");
        this.getContinentsGame().forEach(continent -> continent.getTerritoiresContinent().forEach(territoire -> System.out.println(continent.getContinentName() + ": " + territoire.getTerritoireName())));
        System.out.println("----------------------------------\n");
        this.getJoueurs().forEach(joueur -> System.out.println(joueur.getPrenomJoueur()));
        System.out.println("----------------------------------\n");


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

        this.getJoueurs().get(0).setActif(true);
    }

    /*------------*/
    /* Methodes */
    /*------------*/

    /**
     * The method creates 5 String[] from the files countries.txt, adjacencies.txt, continents.txt, joueurs.txt, equipes.txt.
     * Those String[] are given to a method to then create the Territory, Continent, Joueur, Equipe objects with the initPlateau
     */
    @Override
    public void readFiles() {
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

            // Reads players file
            reader = new BufferedReader(new FileReader("src/main/java/org/example/model/joueurs.txt"));
            stringBuilder = new StringBuilder();
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            input = stringBuilder.toString();
            // Splits the text in the file into an array
            String[] playersArray = input.split(";");

            // Reads teams file
            reader = new BufferedReader(new FileReader("src/main/java/org/example/model/equipes.txt"));
            stringBuilder = new StringBuilder();
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            input = stringBuilder.toString();
            // Splits the text in the file into an array
            String[] equipesArray = input.split(";");

            this.initPlateau(countriesArray, adjacenciesArray, continentsArray, playersArray, equipesArray);

        } catch (FileNotFoundException error) {
            System.out.println(error.getMessage());
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }

    }

    @Override
    public void initPlateau(String[] territoires, String[] territoiresVoisins, String[] continents, String[] joueurs, String[] equipes) {
        this.addTerritories(territoires);
        this.addTerritoryNextTerritories(territoiresVoisins);
        this.addContinent(continents);
        this.addEquipe(equipes);
        this.addJoueurs(joueurs);
    }
    @Override
    public void addTerritories(String[] territoires) {
        for (int i = 0; i < territoires.length; i++) {
            String[] countriesArray = territoires[i].split(",");
            this.getTerritoiresGame().add(new Territoire(countriesArray[0], countriesArray[4]));
            this.getCartesTerritoires().add(new CarteTerritoire(this.getTerritoiresGame().get(i), countriesArray[1]));

            switch (countriesArray[4]) {
                case "Amérique du Nord" -> {
                    this.getTerritoiresGame().get(i).setTypeTerritoire(TypeTerritoire.AMNORD);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.getTerritoiresGame().get(i));
                }
                case "Amérique du Sud" -> {
                    this.getTerritoiresGame().get(i).setTypeTerritoire(TypeTerritoire.AMSUD);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.getTerritoiresGame().get(i));
                }
                case "Europe" -> {
                    this.getTerritoiresGame().get(i).setTypeTerritoire(TypeTerritoire.EU);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.getTerritoiresGame().get(i));
                }
                case "Afrique" -> {
                    this.getTerritoiresGame().get(i).setTypeTerritoire(TypeTerritoire.AFRIQUE);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.getTerritoiresGame().get(i));
                }
                case "Asie" -> {
                    this.getTerritoiresGame().get(i).setTypeTerritoire(TypeTerritoire.ASIE);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.getTerritoiresGame().get(i));
                }
                default -> {
                    this.getTerritoiresGame().get(i).setTypeTerritoire(TypeTerritoire.AUST);
                    this.setTerritoire(Integer.parseInt(countriesArray[2]), Integer.parseInt(countriesArray[3]), this.getTerritoiresGame().get(i));
                }

            }
        }
    }

    @Override
    public void addTerritoryNextTerritories(String[] territoiresVoisins) {
        for (int i = 0; i < territoiresVoisins.length; i++) {
            List<String> adjacenciesTable = new ArrayList<>();
            List<Territoire> adjacenciesList = new ArrayList<>();
            for (int j = 1; j < territoiresVoisins[i].split(",").length; j++) {
                adjacenciesTable.add(territoiresVoisins[i].split(",")[j]);
            }
            adjacenciesList = adjacenciesTable.stream().map((this::getTerritoireByName)).toList();
            this.getTerritoiresGame().get(i).setTerritoiresAdjacents(adjacenciesList);
        }
    }

    @Override
    public void addJoueurs(String[] joueurs) {
        String[] joueursArray;
        for (int i = 0; i < joueurs.length; i++) {
            joueursArray = joueurs[i].split(",");
            this.getJoueurs().add(new Joueur(joueursArray[1], joueursArray[2], this.getEquipeByName(joueursArray[3]),Integer.parseInt(joueursArray[0]) ));
        }
    }

    @Override
    public void addEquipe(String[] equipes) {
        this.setEquipes(Arrays.stream(equipes)
                .map(Equipe::new)
                .toList());
    }
    @Override
    public void addContinent(String[] continents) {
        for (int i = 0; i < continents.length; i++) {
            String[] continentsArray = continents[i].split(",");
            this.getContinentsGame().add(new Continent(continentsArray[0], Integer.parseInt(continentsArray[1]), Arrays.stream(continentsArray).toList().subList(2, continentsArray.length).stream().map(this::getTerritoireByName).toList()));
        }
    }

    @Override
    public Continent getContinentByName(String continentName) {
        return this.getContinentsGame().stream()
                .filter(continent -> continent.getContinentName().equals(continentName.replace(" ", "")))
                .toList().get(0);
    }

    @Override
    public Territoire getTerritoireByName(String territoryName) {
       return this.getTerritoiresGame().stream()
               .filter(territoire -> territoire.getTerritoireName().equals(territoryName.stripLeading()))
               .toList().get(0);
    }

    /**
     *
     * @param countryName
     * @return
     */
    @Override
    public CarteTerritoire getACarteTerritoireByTerritoireName(String countryName) {
        return this.getCartesTerritoires().stream()
                .filter(carteTerritoire -> carteTerritoire.getTerritoire().getTerritoireName().equals(countryName))
                .toList().get(0);
    }

    @Override
    public Joueur getAJoueurById(int joueurId) {
        return this.getJoueurs().stream()
                .filter(joueur -> joueur.getIdJoueur() == (joueurId))
                .toList().get(0);
    }

    @Override
    public Equipe getEquipeByName(String nomEquipe) {
        return this.getEquipes().stream()
                .filter(equipe -> equipe.getNomEquipe().equals(nomEquipe))
                .toList().get(0);
    }

    /**
     * Affecte / retourne le type d'un territoire donnee.
     */

    @Override
    public TypeTerritoire getTypeTerritoire(int x, int y) {
        return this.getTerritoires()[x][y].getTypeTerritoire();
    }


 }
