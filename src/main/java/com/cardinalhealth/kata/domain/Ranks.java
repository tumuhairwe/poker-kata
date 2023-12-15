package com.cardinalhealth.kata.domain;

public enum Ranks {

    UNKNOWN(0),
    HIGH_CARD(1, 1),
    PAIR(2, 2),
    TWO_PAIRS(3),   // count = 4 i.e. 2 Pair
    THREE_OF_A_KIND(4, 3),
    STRAIGHT(5, 5),
    FLUSH(6, 5),
    FULL_HOUSE(7, 3),
    FOUR_OF_A_KIND(8, 4),
    ROYAL_FLUSH(8),
    STRAIGHT_FLUSH(9, 5);

    private int rank;
    private int sameValueCardCount;
    Ranks(int r){
        this.rank = r;
    }
    Ranks(int r, int sameValueCardCount){
        this.rank = r;
        this.sameValueCardCount = sameValueCardCount;
    }

    public int getSameValueCardCount() {
        return sameValueCardCount;
    }

    public int getRank() {
        return rank;
    }
}
