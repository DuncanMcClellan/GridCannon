package com.example.gridcannon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CardStack[][] field;

    // The buttons that represent the stacks of cards on the table
    private Button[][] fieldBtns;
    private Button shameBtn;
    private Button newGameBtn;

    // The deck of cards
    private ArrayList<Card> deck = new ArrayList<Card>(54);

    // The stacks of cards
    private CardStack royalStack;
    private CardStack shameStack;

    TextView status;

    boolean playing;
    boolean reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldBtns = new Button[5][5];

        // Initialize Field and Deck
        setOnClick();
        newGame();
    }

    void shuffle(){
        ArrayList<Card> temp = new ArrayList<Card>(54);
        Random random = new Random();

        for(int i = 0; i < 54; i++) {
            int rand = random.nextInt(54 - i);
            temp.set(i, deck.get(rand));
            deck.remove(rand);
        }

        for(int i = 0; i < 54; i++) {
            int rand = random.nextInt(54 - i);
            deck.set(i, temp.get(rand));
            temp.remove(rand);
        }
    }

    void deal(){
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(!(i == 2 && j == 2)){
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        deck.remove(0);
                    }
                    field[i][j].push((Card) deck.get(0));
                    deck.remove(0);
                }

        placeRoyals();
    }

    void placeRoyals(){
        while(royalStack.peek() != null){
            int x = 0, y = 0;

            for(int i = 0; i < 4; i++)
                for(int j = 0; j < 4; j++)
                    if(!(i == 2 && i == 2))
                        if((field[i+1][j].peek() != null && !(field[i+1][j].peek().isRoyal() && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
                                (field[i-1][j].peek() != null && !(field[i-1][j].peek().isRoyal() && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
                                (field[i][j+1].peek() != null && !(field[i][j+1].peek().isRoyal() && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))) ||
                                (field[i][j-1].peek() != null && !(field[i][j-1].peek().isRoyal() && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))))
                            if(field[i][j].peek().getSuit() == royalStack.peek().getSuit())
                                if(x == 0 || field[i][j].peek().getValue() > field[x][y].peek().getValue()) {
                                    x = i;
                                    y = j;
                                }
            if(x == 0)
                for(int i = 0; i < 4; i++)
                    for(int j = 0; j < 4; j++)
                        if(!(i == 2 && j == 2))
                            //Check surrounding cards to see if there is an available royals slot
                            if((field[i+1][j].peek() != null && !(field[i+1][j].peek().isRoyal() && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
                                    (field[i-1][j].peek() != null && !(field[i-1][j].peek().isRoyal() && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
                                    (field[i][j+1].peek() != null && !(field[i][j+1].peek().isRoyal() && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))) ||
                                    (field[i][j-1].peek() != null && !(field[i][j-1].peek().isRoyal() && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))))
                                if(field[i][j].peek().isRed() == royalStack.peek().isRed())
                                    if(x == 0 || field[i][j].peek().getValue() > field[x][y].peek().getValue()) {
                                        x = i;
                                        y = j;
                                    }
            if(x == 0)
                for(int i = 0; i < 4; i++)
                    for(int j = 0; j < 4; j++)
                        if(!(i == 2 && j == 2))
                            //Check surrounding cards to see if there is an available royals slot
                            if((field[i+1][j].peek() != null && !(field[i+1][j].peek().isRoyal() && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
                                    (field[i-1][j].peek() != null && !(field[i-1][j].peek().isRoyal() && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
                                    (field[i][j+1].peek() != null && !(field[i][j+1].peek().isRoyal() && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))) ||
                                    (field[i][j-1].peek() != null && !(field[i][j-1].peek().isRoyal() && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))))
                                if(x == 0 || field[i][j].peek().getValue() > field[x][y].peek().getValue()) {
                                    x = i;
                                    y = j;
                                }

            selectRoyal(x, y);
        }
    }

    void selectRoyal(int x, int y){
        if(x == 1)
            if(y == 1)
                if(field[x-1][y].peek() != null)
                    y--;
                else if(field[x][y-1].peek() != null)
                    x--;
                else //both are null
                    x--; //user selection later
        if(y == 2)
            x--;
        if(y == 3)
            if(field[x-1][y].peek() != null)
                y++;
            else if(field[x][y+1].peek() != null)
                x--;
            else //both are null
                y++; //user selection later
        else if(x == 2)
            if(y == 1)
                y--;
        if(y == 3)
            y++;
        else if(x == 3)
            if(y == 1)
                if(field[x+1][y].peek() != null)
                    y--;
                else if(field[x][y-1].peek() != null)
                    x++;
                else //both are null
                    y--; //user selection later
        if(y == 2)
            x++;
        if(y == 3)
            if(field[x+1][y].peek() != null)
                y++;
            else if(field[x][y+1].peek() != null)
                x++;
            else //both are null
                x++; //user selection later
        else{}
            //error

        field[x][y].push(royalStack.pop());
    }

    void setOnClick(){
        fieldBtns[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 1);
                if(reset){
                    resetStack(1, 1);
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        placeRoyals();
                    }
                }
            }
        });

        fieldBtns[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 2);
                if(reset){
                    resetStack(1, 2);
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        placeRoyals();
                    }
                }
            }
        });

        fieldBtns[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 3);
                if(reset){
                    resetStack(1, 3);
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        placeRoyals();
                    }
                }
            }
        });

        fieldBtns[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 1);
                if(reset){
                    resetStack(1, 1);
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        placeRoyals();
                    }
                }
            }
        });

        fieldBtns[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 2);
                if(reset){
                    resetStack(1, 2);
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        placeRoyals();
                    }
                }
            }
        });

        fieldBtns[2][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 3);
                if(reset){
                    resetStack(1, 3);
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        placeRoyals();
                    }
                }
            }
        });

        fieldBtns[3][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 1);
                if(reset){
                    resetStack(1, 1);
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        placeRoyals();
                    }
                }
            }
        });

        fieldBtns[3][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 2);
                if(reset){
                    resetStack(1, 2);
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        placeRoyals();
                    }
                }
            }
        });

        fieldBtns[3][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 3);
                if(reset){
                    resetStack(1, 3);
                    while(((Card) deck.get(0)).isRoyal()){
                        royalStack.push((Card) deck.get(0));
                        placeRoyals();
                    }
                }
            }
        });

        shameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(9, 9);
            }
        });

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });
    }

    void newGame(){
        init();
        deal();
        status.setText("Playing");
        playing = true;
        reset = false;
        royalStack = new CardStack(12);
        shameStack = new CardStack(33);
        //shameBtn
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

        shuffle();
    }


}
