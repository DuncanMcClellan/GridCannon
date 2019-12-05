package com.example.gridcannon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CardStack[][] field = new CardStack[5][5];

    // The buttons that represent the stacks of cards on the table
    private ImageButton[][] fieldBtns;
    private ImageButton deckBtn;
    private ImageButton shameBtn;
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

        fieldBtns = new ImageButton[5][5];

        // todo Duncan please review this to make sure im not messing it up

        // Place-able Field Buttons
        fieldBtns[1][1] = findViewById(R.id.field0);
        fieldBtns[1][2] = findViewById(R.id.field1);
        fieldBtns[1][3] = findViewById(R.id.field2);

        fieldBtns[2][1] = findViewById(R.id.field3);
        fieldBtns[2][2] = findViewById(R.id.field4);
        fieldBtns[2][3] = findViewById(R.id.field5);

        fieldBtns[3][1] = findViewById(R.id.field6);
        fieldBtns[3][2] = findViewById(R.id.field7);
        fieldBtns[3][3] = findViewById(R.id.field8);

        // Royal Field Buttons
        fieldBtns[0][1] = findViewById(R.id.royal0);
        fieldBtns[0][2] = findViewById(R.id.royal1);
        fieldBtns[0][3] = findViewById(R.id.royal2);

        fieldBtns[1][0] = findViewById(R.id.royal3);
        fieldBtns[1][4] = findViewById(R.id.royal4);

        fieldBtns[2][0] = findViewById(R.id.royal5);
        fieldBtns[2][4] = findViewById(R.id.royal6);

        fieldBtns[3][0] = findViewById(R.id.royal7);
        fieldBtns[3][4] = findViewById(R.id.royal8);

        fieldBtns[4][1] = findViewById(R.id.royal9);
        fieldBtns[4][2] = findViewById(R.id.royal10);
        fieldBtns[4][3] = findViewById(R.id.royal11);

        // Deck, Shame, and new Game Buttons
        deckBtn = findViewById(R.id.deckBtn);
        shameBtn = findViewById(R.id.shameBtn);
        newGameBtn = findViewById(R.id.newGameBtn);


        // Initialize Field and Deck
        setOnClick();
        newGame();
    }

    //pops the stack at the selected pos onto the bottom of the deck until the stack is empty
    private void resetStack(int x, int y) {

        CardStack temp = new CardStack();
        while (field[x][y].size > 0) {
            temp.push(field[x][y].pop());
        }

        while (temp.size > 0) {
            deck.add(temp.pop());
        }
    }

    private void trigger(int x, int y) {

        int[] dmg;
        dmg = dmg(x, y);

        if(dmg[0] > 0) {

            if (x == 1) {

                // King
                if(field[x+3][y].peek() != null && field[x+3][y].peek().getValue() == 13) {
                    if (field[x + 3][y].peek().getValue() + field[x + 3][y].peek().getArmor() <= dmg[0] && field[x + 3][y].peek().getSuit() == field[x + 2][y].peek().getSuit() && field[x + 3][y].peek().getSuit() == field[x + 1][y].peek().getSuit() && field[x + 3][y].peek().getSuit() == field[x][y].peek().getSuit()) {
                        field[x + 3][y].peek().kill();
                    }
                }

                // Queen
                else if(field[x+3][y].peek() != null && field[x+3][y].peek().getValue() == 12) {
                    if (field[x + 3][y].peek().getValue() + field[x + 3][y].peek().getArmor() <= dmg[0] && field[x + 3][y].peek().isRed() == field[x + 2][y].peek().isRed() && field[x + 3][y].peek().isRed() == field[x + 1][y].peek().isRed() && field[x + 3][y].peek().isRed() == field[x][y].peek().isRed()) {
                        field[x + 3][y].peek().kill();
                    }
                }

                else if (field[x + 3][y].peek() != null && field[x + 3][y].peek().getValue() + field[x + 3][y].peek().getArmor() <= dmg[0]) {
                    field[x + 3][y].peek().kill();
                }

                else {

                }
            }

            else if (x == 3) {

                // King
                if (field[x - 3][y].peek() != null && field[x - 3][y].peek().getValue() == 13) {
                    if (field[x - 3][y].peek().getValue() + field[x - 3][y].peek().getArmor() <= dmg[0] && field[x - 3][y].peek().getSuit() == field[x - 2][y].peek().getSuit() && field[x - 3][y].peek().getSuit() == field[x - 1][y].peek().getSuit() && field[x - 3][y].peek().getSuit() == field[x][y].peek().getSuit()) {
                        field[x - 3][y].peek().kill();
                    }
                }

                // Queen
                else if (field[x - 3][y].peek() != null && field[x + 3][y].peek().getValue() == 12) {
                    if (field[x - 3][y].peek().getValue() + field[x - 3][y].peek().getArmor() <= dmg[0] && field[x - 3][y].peek().isRed() == field[x - 2][y].peek().isRed() && field[x - 3][y].peek().isRed() == field[x - 1][y].peek().isRed() && field[x - 3][y].peek().isRed() == field[x][y].peek().isRed()) {
                        field[x - 3][y].peek().kill();
                    }
                }

                else if (field[x - 3][y].peek() != null && field[x - 3][y].peek().getValue() + field[x - 3][y].peek().getArmor() <= dmg[0]) {
                    field[x - 3][y].peek().kill();
                }

                else {
                    // nothing
                }
            }

            else {
                // nothing
            }

        }

        if (dmg[1] > 0) {

            if (y == 1) {

                // King
                if (field[x][y + 3].peek() != null && field[x][y + 3].peek().getValue() == 13) {
                    if (field[x][y + 3].peek().getValue() + field[x][y + 3].peek().getArmor() <= dmg[0] && field[x][y + 3].peek().getSuit() == field[x][y + 2].peek().getSuit() && field[x][y + 3].peek().getSuit() == field[x][y + 1].peek().getSuit() && field[x][y + 3].peek().getSuit() == field[x][y].peek().getSuit()) {
                        field[x][y + 3].peek().kill();
                    }
                }
                // Queen
                else if (field[x][y + 3].peek() != null && field[x + 3][y].peek().getValue() == 12) {
                    if (field[x][y + 3].peek().getValue() + field[x][y + 3].peek().getArmor() <= dmg[0] && field[x][y + 3].peek().isRed() == field[x][y + 2].peek().isRed() && field[x][y + 3].peek().isRed() == field[x][y + 1].peek().isRed() && field[x][y + 3].peek().isRed() == field[x][y].peek().isRed()) {
                        field[x][y + 3].peek().kill();
                    }
                }

                else if (field[x][y + 3].peek() != null && field[x][y + 3].peek().getValue() + field[x][y + 3].peek().getArmor() <= dmg[0]) {
                    field[x][y + 3].peek().kill();
                }

                else {
                    //nothing. royal slot is null
                }

            }

            else if (y == 3) {

                // King
                if (field[x][y - 3].peek() != null && field[x][y - 3].peek().getValue() == 13) {
                    if (field[x][y - 3].peek().getValue() + field[x][y - 3].peek().getArmor() <= dmg[0] && field[x][y - 3].peek().getSuit() == field[x][y - 2].peek().getSuit() && field[x][y - 3].peek().getSuit() == field[x][y - 1].peek().getSuit() && field[x][y - 3].peek().getSuit() == field[x][y].peek().getSuit()) {
                        field[x][y - 3].peek().kill();
                    }
                }

                // Queen
                else if (field[x][y - 3].peek() != null && field[x - 3][y].peek().getValue() == 12) {
                    if (field[x][y - 3].peek().getValue() + field[x][y - 3].peek().getArmor() <= dmg[0] && field[x][y - 3].peek().isRed() == field[x][y - 2].peek().isRed() && field[x][y - 3].peek().isRed() == field[x][y - 1].peek().isRed() && field[x][y - 3].peek().isRed() == field[x][y].peek().isRed()) {
                        field[x][y - 3].peek().kill();
                    }
                }

                else if (field[x][y - 3].peek() != null && field[x][y - 3].peek().getValue() + field[x][y - 3].peek().getArmor() <= dmg[0]) {
                    field[x][y - 3].peek().kill();
                }

                else {
                    //nothing. royal slot is null
                }
            }

            else {
                //nothing
            }

        }

    }

    //places card at selected position if all rules are met
    private void placeCard(int x, int y) {
        if (x == 9 && y == 9) {
            shameStack.push(deck.get(0));

            reset = true;
        }
        else if (deck.get(0).isWild()) {
            resetStack(x, y);
            field[x][y].push(deck.get(0));

            // TODO: Update Button Image
            fieldBtns[x][y].setImageResource(deck.get(0).id);

            trigger(x, y);
        }
        else if (field[x][y].peek().getValue() <= deck.get(0).getValue()) {
            field[x][y].push(deck.get(0));
            deck.remove(0);
            // TODO: Update Button Image
            trigger(x, y);
        }
        else {
            // nothing, invalid section.
        }

        if (!reset) {
            while(deck.get(0).isRoyal()) {
                royalStack.push(deck.get(0));
            }
            deck.remove(0);
            placeRoyals();

            checkGameOver();
        }

    }

    private int[] dmg(int x, int y) {
        int[] dmg = new int[2];     // opposing x, opposing y

        if(x == 1 && y == 1) {
            dmg[0] = field[x + 1][y].peek().getValue() + field[x + 2][y].peek().getValue();     //right
            dmg[1] = field[x][y + 1].peek().getValue() + field[x][y + 2].peek().getValue();     //down
        }

        else if(x == 1 && y == 3) {
                dmg[0] = field[x + 1][y].peek().getValue() + field[x + 2][y].peek().getValue();     //right
                dmg[1] = field[x][y - 1].peek().getValue() + field[x][y - 2].peek().getValue();     //up
            }

        else if(x == 1) {
                dmg[0] = field[x + 1][y].peek().getValue() + field[x + 2][y].peek().getValue();     //right
                dmg[1] = 0;
            }

        else if(x == 2 && y == 1) {
                dmg[0] = 0;
                dmg[1] = field[x][y + 1].peek().getValue() + field[x][y + 2].peek().getValue();     //down
            }

        else if(x == 2 && y == 3) {
                dmg[0] = 0;
                dmg[1] = field[x][y - 1].peek().getValue() + field[x][y - 2].peek().getValue();     //up
            }

        else if(x == 3 && y == 1) {
                dmg[0] = field[x = 1][y].peek().getValue() + field[x - 2][y].peek().getValue();     //left
                dmg[1] = field[x][y + 1].peek().getValue() + field[x][y + 2].peek().getValue();     //down
            }

        else if(x == 3 && y == 3) {
                dmg[0] = field[x - 1][y].peek().getValue() + field[x - 2][y].peek().getValue();     //left
                dmg[1] = field[x][y - 1].peek().getValue() + field[x][y - 2].peek().getValue();     //up
            }

        else if(x == 3) {
                dmg[0] = field[x - 1][y].peek().getValue() + field[x - 2][y].peek().getValue();     //left
                dmg[1] = 0;
            }

        return dmg;
    }

    private void lockGame(boolean win){
        playing = false;

        if(win)
            status.setText("Win");
        else
            status.setText("Loss");
    }

    private void checkGameOver(){
        int counter = 0;

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (field[i][j].peek() != null && field[i][j].peek().isDead())
                    counter++;

        if(counter == 12)
            lockGame(true);

        if(deck.size() == 0)
            lockGame(false);
    }

    private void shuffle(){
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

    private void deal(){
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(!(i == 2 && j == 2)){
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
                        deck.remove(0);
                    }
                    field[i][j].push(deck.get(0));
                    deck.remove(0);
                }

        placeRoyals();
    }

    private void placeRoyals(){
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

    private void selectRoyal(int x, int y){
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

    private void setOnClick(){
        fieldBtns[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing)
                    placeCard(1, 1);
                if(reset){
                    resetStack(1, 1);
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
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
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
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
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
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
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
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
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
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
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
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
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
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
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
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
                    while(deck.get(0).isRoyal()){
                        royalStack.push(deck.get(0));
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

    private void newGame(){
        init();
        deal();
        status.setText("Playing");
        playing = true;
        reset = false;
        royalStack = new CardStack(12);
        shameStack = new CardStack(33);
        //shameBtn
    }

    private void init() {
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
            if (i < 2)  // +2
                deck.add(new Card(false, true, false, 0, 'J'));

                // Ace of Spades
            else if (i == 2)  // + 1
                deck.add(new Card(false, true, false, 1, 'S'));


                // Spades Numbers
            else if (i < 12)//+10
                deck.add(new Card(false, false, false, i - 1, 'S'));

            // Spades Royals
            else if (i < 15)//+3
                deck.add(new Card(true, false, false, i - 1, 'S'));

                // Ace of Clubs
            else if (i == 15) // + 1
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
        }

        shuffle();
    }


}


