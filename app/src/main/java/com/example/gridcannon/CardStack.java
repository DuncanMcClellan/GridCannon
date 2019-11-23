package com.example.gridcannon;

public class CardStack {

    static final int MAX = 54;

    Card[] stack = new Card[MAX];   // Implemented using an array
    public int size;                // Keeping track of stack size

    /* Default No-Arg Constructor - Creates an empty stack */
    public CardStack() {
        size = 0;
    }

    public void push(Card card) {

        stack[size - 1] = card;
        size++;
    }

    public Card pop() {

        /* Checking to see if the stack is empty */
        if (size < 1) return null;

        Card card = stack[size - 1];
        size--;

        return card;
    }
}
