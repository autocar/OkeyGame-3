package com.company;

public class Tile {
    private int number;
    private boolean joker = false;

    public Tile(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isJoker() {
        return joker;
    }

    public void setJoker(boolean joker) {
        this.joker = joker;
    }

    @Override
    public String toString() {
        return ""+number;
    }
}
