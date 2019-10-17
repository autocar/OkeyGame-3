package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Controller {
    final static int PLAYER_COUNT = 4;
    final static int DIFF_TILES = 53;
    final static int COPY_TILE = 2;
    private int firstPlayerNo;
    private int joker;
    private int indicator;
    private ArrayList<Tile> allTiles = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    private Random rg;

    public void playGame(){
        createPlayers();
        createTile();
        defineJoker();
        shuffleTile();
        tileDistribute();

        double winnerScore = 0;
        int winner = 0;
        for (int i = 0; i < players.size(); i++) {
            players.get(i).findDoubleCount();
            players.get(i).sameNumberCluster();
            players.get(i).sameColorCluster();

            double currentPlayerScore = players.get(i).getPlayerScore();

            if(currentPlayerScore >= winnerScore){
                winner = i;
                winnerScore = currentPlayerScore;
            }
        }

        playerDetailedInfo(players.get(winner));
        System.out.println("\nFinished");
    }

    private void createPlayers(){
        for(int i = 0; i < PLAYER_COUNT; i++){
            players.add(new Player(i));
        }

        rg = new Random();
        firstPlayerNo = rg.nextInt(PLAYER_COUNT);
        players.get(firstPlayerNo).setFirstPlayer(true);
    }

    private void createTile(){
        // Create tile without fakeJoker which will be added at defineJoker
        for(int i = 0; i < COPY_TILE; i++){
            for(int k = 0; k < DIFF_TILES -2; k++){
                allTiles.add(new Tile(k));
            }
        }
    }

    private void defineJoker(){
        rg = new Random();
        indicator = rg.nextInt(DIFF_TILES -1);
        if(indicator == 12 || indicator == 25 || indicator == 38 || indicator == 51){
            joker = indicator - 12;
        }else{
            joker = indicator + 1;
        }

        allTiles.get(joker).setJoker(true);
        allTiles.get(joker+51).setJoker(true);

        allTiles.get(joker).setNumber(-1);
        allTiles.get(joker+51).setNumber(-1);


        allTiles.remove(indicator);     // Remove one indicator from game

        // Add 2 fakeJokers to tile
        for(int i = 0; i < COPY_TILE; i++){
            allTiles.add(new Tile(joker));
        }
    }

    private void shuffleTile(){
        if (!allTiles.isEmpty()){
            Collections.shuffle(allTiles);
        }else{
            System.out.println("Tile set is empty. Create tile set before shuffle!");
        }
    }

    private void tileDistribute(){
        for(int i = 0; i < PLAYER_COUNT; i++){
            if(i == firstPlayerNo){
                players.get(i).setFirstPlayer(true);
                for(int k=0; k<15; k++){
                    players.get(i).getTiles().add(allTiles.remove(0));
                }
            }else{
                for(int k=0; k<14; k++){
                    players.get(i).getTiles().add(allTiles.remove(0));
                }
            }
        }
        countJokers();
    }
    
    private void countJokers(){
        for (Player player: players) {
            player.tileSort();
            int count = 0;
            for (Tile tile: player.getTiles()) {
                if(tile.isJoker()){
                    count += 1;
                }
            }
            player.setJokerCount(count);
        }
    }


    public void playerDetailedInfo(Player p){       // Detailed information of all players
            System.out.println("Indicator: " + indicator + "\nJoker: "+joker);
            System.out.println("Winner: Player" + p.getPlayerNo());
            System.out.println("Tile size: " + p.getTiles().size());
            System.out.println("Joker count: " + p.getJokerCount());
            System.out.println("Double score: " + p.getDoubleCount()*2);
            System.out.println("All tiles in ascending order:\t" + p.getTiles());
            System.out.println("Clustered according to number: \t" + p.getNoClus());
            System.out.println("Total number clustering score: " + p.totalNoClustering());
            System.out.println("Clustered according to color: \t" + p.getColClus());
            System.out.println("Total color clustering score: " + p.totalColCluster());
            System.out.println("Final score of Player"+p.getPlayerNo()+": " + p.getPlayerScore());
    }

    @Override
    public String toString() {
        return "Controller{" +
                "allTiles=" + allTiles +
                ", players=" + players +
                '}';
    }
}
