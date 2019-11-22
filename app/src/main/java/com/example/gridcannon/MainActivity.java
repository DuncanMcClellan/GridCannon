package com.example.gridcannon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 2D array of Card stacks
    // TODO: Should this be an ArrayList?
    private int[][] field = new int[5][5];

    // The buttons that represent the stacks of cards on the table
    private Button[][] fieldBtns = new Button[5][5];

    // The deck of cards
    private ArrayList deck = new ArrayList(54);

    // The stacks of cards
    private ArrayList royalStack = new ArrayList(12);
    private ArrayList shameStack = new ArrayList(33);

    private Button shameBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*

cards{
	boolean royal
	boolean wild
	boolean red
	boolean dead = false
	int value
	char suit
}

onCreate{
	fieldBtns = new Button[5][5]
	fieldBtns[i] = button in view
	shameBtn = button in view
	
	setOnClick
	init
	deal
}

placeCard(int x, int y){
	if(x == 9 && y == 9)//artifical shamepile pos
		shameStack.push(deck[0])
	else if(deck[0].wild)
		while(field[x][y].size > 0)
			deck.add(field[x][y].pop())
		field[x][y].push(deck[0])
	else if(field[x][y].peek().value < deck[0].value)
		field[x][y].push(deck[0])
	else
		
		
	if(deck[0].royal)
		while(deck[0].royal)
			royalStack.push(deck[0])
			placeRoyals
}

int[] trigger(int x, int y){
	int dmg[2]
	
	if(x == 1 && y == 1)
		dmg[0] = field[x][y+1].peek().value + field[x][y+2].peek().value
		dmg[1] = field[x+1][y].peek().value + field[x+2][y].peek().value
	else if(x == 1)
		dmg[0] = field[x][y+1].peek().value + field[x][y+2].peek().value
		dmg[1] = 0
	else if(y == 1)
		dmg[0] = 0
		dmg[1] = field[x+1][y].peek().value + field[x+2][y].peek().value
	
	return dmg[]
}

shuffle{
	shuffle deck
}

deal{
	for(i = 1 < 4)
		for(j = 1 < 4)
			if(!(i == 2 && j == 2))
				if(deck.top != royal)
					field[i][j].push(deck.pop())
				else
					royalStack.push(deck.pop())
	
	placeRoyals
}

placeRoyals{
	while(royalStack.peek() != null)
		int x = 0, y = 0;
		
		for(i = 1 < 4)
			for(j = 1 < 4)
				if(!(i == 2 && j == 2))
						//Check surrounding cards to see if there is an available royals slot
					if((field[i+1][j].peek() != null && !(field[i+1][j].peek().royal && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
						(field[i-1][j].peek() != null && !(field[i-1][j].peek().royal && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
						(field[i][j+1].peek() != null && !(field[i][j+1].peek().royal && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))) ||
						(field[i][j-1].peek() != null && !(field[i][j-1].peek().royal && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))))
						if(field[i][j].peek().suit == royalStack.peek().suit)
							if(x == 0 || field[i][j].peek().value > field[x][y].peek().value)
								x = i
								y = j
		if(x == 0)
			for(i = 1 < 4)
				for(j = 1 < 4)
					if(!(i == 2 && j == 2))
						//Check surrounding cards to see if there is an available royals slot
						if((field[i+1][j].peek() != null && !(field[i+1][j].peek().royal && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
							(field[i-1][j].peek() != null && !(field[i-1][j].peek().royal && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
							(field[i][j+1].peek() != null && !(field[i][j+1].peek().royal && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))) ||
							(field[i][j-1].peek() != null && !(field[i][j-1].peek().royal && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))))
							if(field[i][j].peek().red == royalStack.peek().red)
								if(x == 0 || field[i][j].peek().value > field[x][y].peek().value)
									x = i
									y = j
		if(x == 0)
			for(i = 1 < 4)
				for(j = 1 < 4)
					if(!(i == 2 && j == 2))
						//Check surrounding cards to see if there is an available royals slot
						if((field[i+1][j].peek() != null && !(field[i+1][j].peek().royal && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
							(field[i-1][j].peek() != null && !(field[i-1][j].peek().royal && (field[i][j+1].peek() != null || field[i][j-1].peek() != null))) ||
							(field[i][j+1].peek() != null && !(field[i][j+1].peek().royal && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))) ||
							(field[i][j-1].peek() != null && !(field[i][j-1].peek().royal && (field[i+1][j].peek() != null || field[i-1][j].peek() != null))))
							if(x == 0 || field[i][j].peek().value > field[x][y].peek().value)
								x = i
								y = j
	
		if(x == 1)
			if(y == 1)
				user selection
			if(y == 2)
				x--;
			if(y == 3)
				user selection
		else if(x == 2)
			if(y == 1)
				y--;
			if(y == 3)
				y++;
		else if(x == 3)
			if(y == 1)
				user selection
			if(y == 2)
				x++;
			if(y == 3)
				user selection		
		else
			error
	
		field[x][y].push(royalStack.push())
}

setOnClick{
	fieldBtns[i].setOnClickListener((v) -> {
		if()
			placeCard
	})
}

init{
	for(i = 0 < 5)
		for(j = 0 < 5)
			if((i == 0 && j == 0) || (i == 0 && j == 4) || (i == 4 && j == 0) || (i == 4 && j == 4))
			else if(i == 0 || j == 0 || i == 4 || j == 4)
				field[i][j] = new cardStack(1)
			else
				field[i][j] = new cardStack()

	for(i = 0 < 54)
		if(i < 2)
			deck[i] = cards(false, true, false, 0, 'J')
		else if(i == 2)
			deck[i] = cards(false, true, false, 1, 'S')
		else if(i < 12)//+10
			deck[i] = cards(false, false, false, i-1, 'S')
		else if(i < 15)//+3
			deck[i] = cards(true, false, false, i-1, 'S')
		else if(i == 15)
			deck[i] = cards(false, true, false, 1, 'C')
		else if(i < 25)//+10
			deck[i] = cards(false, false, false, i-14, 'C')
		else if(i < 28)//+3
			deck[i] = cards(true, false, false, i-14, 'C')
		else if(i == 28)
			deck[i] = cards(false, true, false, 1, 'H')
		else if(i < 38)//+10
			deck[i] = cards(false, false, false, i-28, 'H')
		else if(i < 41)//+3
			deck[i] = cards(true, false, false, i-28, 'H')
		else if(i == 41)
			deck[i] = cards(false, true, false, 1, 'D')
		else if(i < 51)//+10
			deck[i] = cards(false, false, false, i-41, 'D')
		else if(i < 54)//+3
			deck[i] = cards(true, false, false, i-41, 'D')
		else
			error

	shuffle
    }
}

    */


}
