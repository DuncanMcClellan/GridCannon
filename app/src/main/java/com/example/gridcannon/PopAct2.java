package com.example.gridcannon;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

public class PopAct2 extends Activity {
    TextView rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_act2);

        rules = findViewById(R.id.rules);

        String text = "Start:\n\n" +
                "•\tGo through deck, only put down number cards and jokers and Aces\n\n" +
                "•\tLeave center empty\n\n" +
                "•\tCollect royals that come up during pulling cards to create grid for immediate placement\n\n" +
                "\n" +
                "Placing royals\n\n" +
                "•\tplace royal next to card most like it (suit, color, and then value)(we need to develop rules to determine immediate card placement\n\n" +
                "o\tsuits first, then color, and then value, if all cards of same suit are covered, go by point value \n\n" +
                "\n" +
                "        Playing the Game:\n\n" +
                "\n" +
                "•\tafter initial royal cards are placed, pull top card from deck \n\n" +
                "•\tcard can only be placed on stack with less  or equal value than it\n\n" +
                "•\tif royal is picked up, place it on edge following the royal rules \n\n" +
                "•\tif you have a card you can’t place, you have 2 options:\n" +
                "o\tyou put it in the ‘shame pile’ and you have to ‘reset’ a pile, by putting all of the cards in that pile at the bottom of the deck, that section of the grid is now empty, it is able to have a new card placed on it.\n\n" +
                "o\tYou add it to a royal as hit points for the royal it is most like it(follow rules of value, if you have 2 royals of the same suit, give the armor to the one with the least hit points \n\n" +
                "•\tKing can only be attacked by points of its same suits\n\n" +
                "•\tQueens can only be attacked by cards of the same color\n\n" +
                "•\tWhen you put a card on an edge, it triggers the cards on either side to attack the royal and check if it can kill it or not\n\n" +
                "•\tA joker or wild means a reset for one of your stacks, cards in stack goes on bottom\n" +
                " \n" +
                "\n";

        rules.setText(text);
        rules.setMovementMethod(new ScrollingMovementMethod());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20 ;

        getWindow().setAttributes(params);




    }
}
