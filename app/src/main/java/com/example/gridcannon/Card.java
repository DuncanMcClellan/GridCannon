package com.example.gridcannon;

public class Card {

    boolean royal;
    boolean wild;
    boolean red;
    boolean dead = false;
    int value;
    int armor = 0;
    char suit;

    /* Only Constructor that will be needed */
    public Card(boolean royal, boolean wild, boolean red, int value, char suit) {
        this.royal = royal;
        this.wild = wild;
        this.red = red;
        this.value = value;
        this.suit = suit;
    }
}
