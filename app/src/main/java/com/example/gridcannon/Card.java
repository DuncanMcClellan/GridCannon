package com.example.gridcannon;

import android.media.Image;
import android.widget.ImageView;

public class Card {

    private boolean royal;
    private boolean wild;
    private boolean red;
    private boolean dead = false;
    private int value;
    private int armor = 0;
    private char suit;
    public ImageView card;
    public int id;              // The id of the card

    /* Only Constructor that will be needed */
    public Card(boolean royal, boolean wild, boolean red, int value, char suit) {
        this.royal = royal;
        this.wild = wild;
        this.red = red;
        this.value = value;
        this.suit = suit;
    }


    public boolean isRoyal(){return this.royal;}

    public boolean isWild(){return this.wild;}

    public boolean isRed(){return this.red;}

    public boolean isDead(){return this.dead;}

    public void kill(){this.dead = true;}

    public int getValue(){return this.value;}

    public int getArmor(){return this.armor;}

    public void setArmor(int armor){this.armor = armor;}

    public char getSuit(){return this.suit;}
}
