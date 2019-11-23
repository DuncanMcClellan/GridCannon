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
//Pseudocode written and designed by Duncan McClellan
//Pseudocode for Gridcannon Android App

CardStack field[5][5] //2D array of Card stacks
Button fieldBtns[5][5]
Cards deck[54] //ArrayList
Cards royalStack[12]
Cards shameStack[33] //54-(5*5-4) = 54-21 = 33
Button shameBtn
boolean playing
TextView status

//Cards class
Cards{
	boolean royal
	boolean wild
	boolean red
	boolean dead = false
	int value
	int armor = 0
	char suit
}

onCreate{
	fieldBtns = new Button[5][5]
	fieldBtns[i] = button in view
	
	setOnClick
	newGame
}

//pops the stack at the selected pos onto the bottom of the deck until the stack is empty
resetStack(int x, int y){
	while(field[x][y].size > 0)
		deck.add(field[x][y].pop())
}

//places card at selected position if all rules are met
placeCard(int x, int y){
	if(x == 9 && y == 9)//artifical shamepile pos
		shameStack.push(deck[0])
		update shamepile button img
		reset = true
	else if(deck[0].wild)
		resetStack(x, y)
		field[x][y].push(deck[0])
		update button img
		trigger(x, y)
	else if(field[x][y].peek().value <= deck[0].value)
		field[x][y].push(deck[0])
		update button img
		trigger(x, y)
	else
		//nothing. Invalid selection

	if(!reset)
		while(deck[0].royal)
			royalStack.push(deck[0])
			placeRoyals

		checkGameOver		
}

//checks to see if a royal is to die and kills the royal
trigger(int x, int y){
	dmg[2] = dmg(x, y)
	
	if(dmg[0] > 0)
		if(x == 1)
			if(field[x+3][y].peek() != null && field[x+3][y].peek().value == 13) //King
				if(field[x+3][y].peek().value + field[x+3][y].peek().armor <= dmg[0] && field[x+3][y].peek().suit == field[x+2][y].peek().suit && field[x+3][y].peek().suit == field[x+1][y].peek().suit && field[x+3][y].peek().suit == field[x][y].peek().suit)
					field[x+3][y].peek().dead = true
			else if(field[x+3][y].peek() != null && field[x+3][y].peek().value == 12) //Queen
				if(field[x+3][y].peek().value + field[x+3][y].peek().armor <= dmg[0] && field[x+3][y].peek().red == field[x+2][y].peek().red && field[x+3][y].peek().red == field[x+1][y].peek().red && field[x+3][y].peek().red == field[x][y].peek().red)
					field[x+3][y].peek().dead = true
			else if(field[x+3][y].peek() != null && field[x+3][y].peek().value + field[x+3][y].peek().armor <= dmg[0])
				field[x+3][y].peek().dead = true
			else
				//nothing. royal slot is null
		else if(x == 3)
			if(field[x-3][y].peek() != null && field[x-3][y].peek().value == 13) //King
				if(field[x-3][y].peek().value + field[x-3][y].peek().armor <= dmg[0] && field[x-3][y].peek().suit == field[x-2][y].peek().suit && field[x-3][y].peek().suit == field[x-1][y].peek().suit && field[x-3][y].peek().suit == field[x][y].peek().suit)
					field[x-3][y].peek().dead = true
			else if(field[x-3][y].peek() != null && field[x+3][y].peek().value == 12) //Queen
				if(field[x-3][y].peek().value + field[x-3][y].peek().armor <= dmg[0] && field[x-3][y].peek().red == field[x-2][y].peek().red && field[x-3][y].peek().red == field[x-1][y].peek().red && field[x-3][y].peek().red == field[x][y].peek().red)
					field[x-3][y].peek().dead = true
			else if(field[x-3][y].peek() != null && field[x-3][y].peek().value + field[x-3][y].peek().armor <= dmg[0])
				field[x-3][y].peek().dead = true
			else
				//nothing. royal slot is null
		else
			//nothing

	if(dmg[1] > 0)
		if(y == 1)
			if(field[x][y+3].peek() != null && field[x][y+3].peek().value == 13) //King
				if(field[x][y+3].peek().value + field[x][y+3].peek().armor <= dmg[0] && field[x][y+3].peek().suit == field[x][y+2].peek().suit && field[x][y+3].peek().suit == field[x][y+1].peek().suit && field[x][y+3].peek().suit == field[x][y].peek().suit)
					field[x][y+3].peek().dead = true
			else if(field[x][y+3].peek() != null && field[x+3][y].peek().value == 12) //Queen
				if(field[x][y+3].peek().value + field[x][y+3].peek().armor <= dmg[0] && field[x][y+3].peek().red == field[x][y+2].peek().red && field[x][y+3].peek().red == field[x][y+1].peek().red && field[x][y+3].peek().red == field[x][y].peek().red)
					field[x][y+3].peek().dead = true
			else if(field[x][y+3].peek() != null && field[x][y+3].peek().value + field[x][y+3].peek().armor <= dmg[0])
				field[x][y+3].peek().dead = true
			else
				//nothing. royal slot is null
		else if(y == 3)
			if(field[x][y-3].peek() != null && field[x][y-3].peek().value == 13) //King
				if(field[x][y-3].peek().value + field[x][y-3].peek().armor <= dmg[0] && field[x][y-3].peek().suit == field[x][y-2].peek().suit && field[x][y-3].peek().suit == field[x][y-1].peek().suit && field[x][y-3].peek().suit == field[x][y].peek().suit)
					field[x][y-3].peek().dead = true
			else if(field[x][y-3].peek() != null && field[x-3][y].peek().value == 12) //Queen
				if(field[x][y-3].peek().value + field[x][y-3].peek().armor <= dmg[0] && field[x][y-3].peek().red == field[x][y-2].peek().red && field[x][y-3].peek().red == field[x][y-1].peek().red && field[x][y-3].peek().red == field[x][y].peek().red)
					field[x][y-3].peek().dead = true
			else if(field[x][y-3].peek() != null && field[x][y-3].peek().value + field[x][y-3].peek().armor <= dmg[0])
				field[x][y-3].peek().dead = true
			else
				//nothing. royal slot is null
		else
			//nothing
}

//calculates damage to be dealt to royal
int[] dmg(int x, int y){
	int dmg[2] //opposing x, opposing y
	
	if(x == 1 && y == 1)
		dmg[0] = field[x+1][y].peek().value + field[x+2][y].peek().value //right
		dmg[1] = field[x][y+1].peek().value + field[x][y+2].peek().value //down
	else if(x == 1 && y == 3)
		dmg[0] = field[x+1][y].peek().value + field[x+2][y].peek().value //right
		dmg[1] = field[x][y-1].peek().value + field[x][y-2].peek().value //up
	else if(x == 1)
		dmg[0] = field[x+1][y].peek().value + field[x+2][y].peek().value //right
		dmg[1] = 0
	else if(x == 2 && y == 1)
		dmg[0] = 0
		dmg[1] = field[x][y+1].peek().value + field[x][y+2].peek().value //down
	else if(x == 2 && y == 3)
		dmg[0] = 0
		dmg[1] = field[x][y-1].peek().value + field[x][y-2].peek().value //up
	else if(x == 3 && y == 1)
		dmg[0] = field[x=1][y].peek().value + field[x-2][y].peek().value //left
		dmg[1] = field[x][y+1].peek().value + field[x][y+2].peek().value //down
	else if(x == 3 && y == 3)
		dmg[0] = field[x-1][y].peek().value + field[x-2][y].peek().value //left
		dmg[1] = field[x][y-1].peek().value + field[x][y-2].peek().value //up
	else if(x == 3)
		dmg[0] = field[x-1][y].peek().value + field[x-2][y].peek().value //left
		dmg[1] = 0
	
	return dmg[]
}

//Ends the game and displays if the player won or lose
lockGame(boolean win){
	playing = false
	
	if(win)
		status.setText("Win")
	else
		status.setText("Loss")
}

//checks game over conditions to see if the game should end or continue. If game ends, calls lockGame
checkGameOver{
	counter = 0
	
	for(i = 0 < 5)
		for(j = 0 < 5)
			if(field[i][j].peek() != null && field[i][j].peek().dead)
				counter++
				
	if(counter == 12)
		lockGame(true)
		
	if(deck.size == 0)
		lockGame(false)
}

//shuffles the deck at beginning of the game
shuffle{
	shuffle deck
}

//deals cards to the grid, skipping the center of the grid
deal{
	for(i = 1 < 4)
		for(j = 1 < 4)
			if(!(i == 2 && j == 2))
				while(true)
					if(deck.top != royal)
						field[i][j].push(deck.pop())
						break
					else
						royalStack.push(deck.pop())
	
	placeRoyals
}

//places all royals on the royalStack
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
	//can't for loop this because of lamda function scopes
	fieldBtns[i][j].setOnClickListener((v) -> {
		if(playing && )
			placeCard(i, j)
		if(reset)
			resetStack(i, j)
			while(deck[0].royal)
				royalStack.push(deck[0])
				placeRoyals
	})
	
	shameBtn.setOnClickListener((v) -> {
		if(playing && )
			placeCard(9, 9)
	})
	
	newGameBtn.setOnClickListener((v)) -> {
		newGame
	})
}

//initializes all variables that have a different value between game start and end
newGame{
	init
	deal
	status.setText("Playing")
	playing = true
	shameBtn = button in view
}

//initializes the deck and the field
init{
	for(i = 0 < 5)
		for(j = 0 < 5)
			if((i == 0 && j == 0) || (i == 0 && j == 4) || (i == 4 && j == 0) || (i == 4 && j == 4))
				//nothing
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
    */


}
