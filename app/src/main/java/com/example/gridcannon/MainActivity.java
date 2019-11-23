package com.example.gridcannon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CardStack[][] field;

    // The buttons that represent the stacks of cards on the table
    private Button[][] fieldBtns;
    private Button shameBtn;

    // The deck of cards
    private ArrayList deck = new ArrayList<Card>(54);

    // The stacks of cards
    private ArrayList royalStack = new ArrayList(12);
    private ArrayList shameStack = new ArrayList(33);

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldBtns = new Button[5][5];

        // Initialize Field and Deck
        init();
    }

    public void init() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if((i == 0 && j == 0) || (i == 0 && j == 4) || (i == 4 && j == 0) || (i == 4 && j == 4))
                    continue;

                else
                    field[i][j] = new CardStack();

            }
        }

        for(int i = 0; i < 54; i++) {

            // Jokers
            if (i < 2)
                deck.add(new Card(false, true, false, 0, 'J'));

            // Ace of Spades
            else if (i == 2)
                deck.add(new Card(false, true, false, 1, 'S'));

            // Spades Numbers
            else if (i < 12)//+10
                deck.add(new Card(false, false, false, i - 1, 'S'));

            // Spades Royals
            else if (i < 15)//+3
                deck.add(new Card(true, false, false, i - 1, 'S'));

            // Ace of Clubs
            else if (i == 15)
                deck.add(new Card(false, true, false, 1, 'C'));

            // Clubs Number
            else if (i < 25)//+10
                deck.add(new Card(false, false, false, i - 14, 'C'));

            // CLubs Royals
            else if (i < 28)//+3
                deck.add(new Card(true, false, false, i - 14, 'C'));

            // Ace of Hearts
            else if (i == 28)
                deck.add(new Card(false, true, false, 1, 'H'));

            // Hearts Numbers
            else if (i < 38)//+10
                deck.add(new Card(false, false, false, i - 28, 'H'));

            // Hearts Royals
            else if (i < 41)//+3
                deck.add(new Card(true, false, false, i - 28, 'H'));

            // Ace of Diamonds
            else if (i == 41)
                deck.add(new Card(false, true, false, 1, 'D'));

            // Diamonds Numbers
            else if (i < 51)//+10
                deck.add(new Card(false, false, false, i - 41, 'D'));

            // Diamonds Royals
            else if (i < 54)//+3
                deck.add(new Card(true, false, false, i - 41, 'D'));

            // Throw exception?
        }

    }


}
