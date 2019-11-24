package com.example.gridcannon;

public class CardStack {

    private int MAX;

    Card[] stack = new Card[MAX];   // Implemented using an array
    public int size;                // Keeping track of stack size

    /* Default No-Arg Constructor - Creates an empty stack */
    public CardStack(int max) {
        this.MAX = max;
        size = 0;
    }

    public CardStack(){
        size = 0;
        this.MAX = 54;
    }

    public void push(Card card) {
        stack[size++] = card;
    }

    public Card pop() {
        /* Checking to see if the stack is empty */
        if (size < 1) return null;

        Card card = stack[--size];

        return card;
    }

    public Card peek(){
        return stack[size-1];
    }
}
