package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {

    private int playerNo;
    private boolean firstPlayer = false;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Tile> clusteredTiles = new ArrayList<>();
    private ArrayList<Tile> noClus = new ArrayList<>();
    private ArrayList<Tile> colClus = new ArrayList<>();
    private int jokerCount = 0;
    private int doubleCount = 0;
    private int tileLeft = 0;
    private int no2Cluster = 0;
    private int no3Cluster = 0;
    private int no4Cluster = 0;
    private int color2Cluster = 0;
    private int color3Cluster = 0;
    private int color4Cluster = 0;
    private int color5Cluster = 0;
    private int colorClustered = 0;

    public Player(int playerNo) {
        this.playerNo = playerNo;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public int getDoubleCount() {
        return doubleCount;
    }

    public void setDoubleCount(int doubleCount) {
        this.doubleCount = doubleCount;
    }

    public int getTileLeft() {
        return tileLeft;
    }

    public void setTileLeft(int tileLeft) {
        this.tileLeft = tileLeft;
    }

    public ArrayList<Tile> getClusteredTiles() {
        return clusteredTiles;
    }

    public void setClusteredTiles(ArrayList<Tile> clusteredTiles) {
        this.clusteredTiles = clusteredTiles;
    }

    public ArrayList<Tile> getNoClus() {
        return noClus;
    }

    public void setNoClus(ArrayList<Tile> noClus) {
        this.noClus = noClus;
    }

    public ArrayList<Tile> getColClus() {
        return colClus;
    }

    public void setColClus(ArrayList<Tile> colClus) {
        this.colClus = colClus;
    }

    public int getNo2Cluster() {
        return no2Cluster;
    }

    public void setNo2Cluster(int no2Cluster) {
        this.no2Cluster = no2Cluster;
    }

    public int getNo3Cluster() {
        return no3Cluster;
    }

    public void setNo3Cluster(int no3Cluster) {
        this.no3Cluster = no3Cluster;
    }

    public int getNo4Cluster() {
        return no4Cluster;
    }

    public void setNo4Cluster(int no4Cluster) {
        this.no4Cluster = no4Cluster;
    }

    public int getColorClustered() {
        return colorClustered;
    }

    public void setColorClustered(int colorClustered) {
        this.colorClustered = colorClustered;
    }

    public int getColor2Cluster() {
        return color2Cluster;
    }

    public void setColor2Cluster(int color2Cluster) {
        this.color2Cluster = color2Cluster;
    }

    public int getColor3Cluster() {
        return color3Cluster;
    }

    public void setColor3Cluster(int color3Cluster) {
        this.color3Cluster = color3Cluster;
    }

    public int getColor4Cluster() {
        return color4Cluster;
    }

    public void setColor4Cluster(int color4Cluster) {
        this.color4Cluster = color4Cluster;
    }

    public int getColor5Cluster() {
        return color5Cluster;
    }

    public void setColor5Cluster(int color5Cluster) {
        this.color5Cluster = color5Cluster;
    }

    public int getJokerCount() {
        return jokerCount;
    }

    public void setJokerCount(int jokerCount) {
        this.jokerCount = jokerCount;
    }

    public int totalNoClustering(){
        return no2Cluster + 3*no3Cluster + 4*no4Cluster;
    }

    public int totalColCluster(){
        return color2Cluster + 3*color3Cluster + 4*color4Cluster + 5*color5Cluster;
    }

    public void tileSort(){
        Collections.sort(tiles,new Comparator<Tile>() {
            @Override
            public int compare(Tile s1, Tile s2) {
                return s1.getNumber() - s2.getNumber();
            }
        });
    }

    public void findDoubleCount(){
        for(int i=0; i<tiles.size()-1; i++){
            if(tiles.get(i).getNumber()==tiles.get(i+1).getNumber()){
                doubleCount += 1; // Increase double count
                i += 1; // Skip next
            }
        }
        if(jokerCount > 0){
            doubleCount += 1;
        }
    }

    public void sameNumberCluster(){
        ArrayList<Tile> copyList = new ArrayList<>(tiles);
        int jCount = jokerCount;
        while(copyList.size() > 1){
            for(int i = 0; i<copyList.size()-1; i++){
                int count = 0;
                int no = copyList.get(i).getNumber();
                if((i != copyList.size()-1) && copyList.get(i+1).getNumber() == no){
                    noClus.add(copyList.remove(i+1));
                    i += 1;
                }else{
                    noClus.add(copyList.remove(i));
                }
                for(int k = i; k < copyList.size(); k++){
                    if((k != (copyList.size()-1))&&(copyList.get(k).getNumber() == copyList.get(k+1).getNumber())){
                        int otherNo = copyList.get(k+1).getNumber() % 13;
                        if(no == otherNo){
                            noClus.add(copyList.remove(k+1));
                            count += 1;
                        }
                    }else{
                        int otherNo = copyList.get(k).getNumber() % 13;
                        if(no == otherNo){
                            noClus.add(copyList.remove(k));
                            count += 1;
                            k -= 1;
                        }
                    }
                }
                if(count == 1){
                    if(jCount > 0){
                        no3Cluster += 1;
                        jCount -= 1;

                    }else{
                        no2Cluster += 1;
                    }
                }else if(count == 2){
                    no3Cluster += 1;
                }else if(count == 3){
                    no4Cluster += 1;
                }else{
                    tileLeft += 1;
                }
            }
        }
        while(!copyList.isEmpty()){
            noClus.add(copyList.remove(0));
            tileLeft += 1;
        }
        if(jCount > 0){
            no2Cluster += jCount;
        }
    }

    public void sameColorCluster(){
        int jCount = jokerCount;
        ArrayList<Tile> copyList = new ArrayList<>(tiles);
        int prevStack = 0;
        int lastNo = -10;
        for(int i = jokerCount + 1; i < copyList.size()-1; i++){
            if(((copyList.get(i-1).getNumber())%13 + 1 == (copyList.get(i).getNumber())%13)&&
                    ((copyList.get(i).getNumber())%13 + 1 == (copyList.get(i+1).getNumber())%13)){
                lastNo = copyList.get(i+1).getNumber()%13;
                colClus.add(copyList.remove(i-1));
                colClus.add(copyList.remove(i-1));
                colClus.add(copyList.remove(i-1));
                i-= 1;
                color3Cluster += 1;
                prevStack = 3;
            }else if(((copyList.get(i).getNumber())%13 == (copyList.get(i-1).getNumber())%13 + 1)
                    && ((copyList.get(i-1).getNumber())%13 == lastNo%13 + 1)){
                lastNo = copyList.get(i).getNumber()%13;
                colClus.add(copyList.remove(i-1));
                colClus.add(copyList.remove(i-1));
                i -= 1;
                if(prevStack == 3){
                    color3Cluster -= 1;
                    color5Cluster += 1;
                    prevStack = 0;
                }else{
                    color2Cluster -= 1;
                    color3Cluster += 1;
                    prevStack = 3;
                }
            }else if(lastNo%13 + 1 == copyList.get(i-1).getNumber()%13){
                lastNo = copyList.get(i-1).getNumber()%13;
                colClus.add(copyList.remove(i-1));
                i -= 1;
                if(prevStack == 3){
                    color3Cluster -= 1;
                    color4Cluster += 1;
                }else{
                    color2Cluster += 1;
                }

            }else if((copyList.get(i).getNumber()%13 == copyList.get(i-1).getNumber()%13 + 1)
                    || ((copyList.get(i).getNumber()%13 == copyList.get(i-1).getNumber()%13 + 2))){
                lastNo = copyList.get(i).getNumber()%13;
                colClus.add(copyList.remove(i-1));
                colClus.add(copyList.remove(i-1));
                i -= 1;
                color2Cluster += 1;
            }else{
                lastNo = copyList.get(i-1).getNumber()%13;
                colClus.add(copyList.remove(i-1));
                i -= 1;
                prevStack = 0;
            }

        }
        while(!copyList.isEmpty()){
            colClus.add(copyList.remove(0));
        }

        for(int k = 0; k < colClus.size(); k++){
            if(colClus.get(k).getNumber()%13 == 12 && colClus.get(k-1).getNumber()%13 == 11 ){
                for(int m = 0; m < colClus.size(); m++){
                    if(colClus.get(m).getNumber()%13 == 1 && colClus.get(m).getNumber() + 12 == colClus.get(k).getNumber()){
                        if(colClus.get(m).getNumber() + 1 == colClus.get(m+1).getNumber()){
                            if(colClus.get(m).getNumber() + 2 != colClus.get(m+2).getNumber()){
                                colClus.add(k+1, colClus.remove(m));
                                color2Cluster -= 1;
                                color3Cluster += 1;
                            }
                            if((colClus.get(m).getNumber() + 2 == colClus.get(m+2).getNumber() &&
                                    colClus.get(m).getNumber() + 3 == colClus.get(m+3).getNumber())){
                                color3Cluster += 1;
                                color4Cluster -= 1;
                            }
                        }else{
                            colClus.add(k+1, colClus.remove(m));
                            color3Cluster += 1;
                        }
                    }
                }
            }
        }
        if(jCount > 0){
            while(jCount > 0){
                if(color2Cluster > 0){
                    color2Cluster -= 1;
                    color3Cluster += 1;
                    jCount -= 1;
                }else{
                    color2Cluster += 1;
                    jCount -= 1;
                }
            }
        }
    }

    public double getPlayerScore(){
        double colorScore = totalColCluster();
        double numberScore = totalNoClustering();
        double doubleScore = getDoubleCount() * 2 ;
        double normalScore = 0;
        double totalScore = 0;

        if(numberScore > colorScore){
            normalScore = numberScore + (colorScore/2);
        }else{
            normalScore = colorScore + (numberScore/2);
        }

        if(doubleScore > normalScore){
            totalScore = doubleScore;
        }else{
            totalScore = normalScore;
        }
        return totalScore;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerNo=" + playerNo +
                ", firstPlayer=" + firstPlayer +
                ", tiles=" + tiles +
                ", doubleCount=" + doubleCount +
                ", tileLeft=" + tileLeft +
                '}';
    }
}
