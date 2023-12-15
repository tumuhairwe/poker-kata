package com.cardinalhealth.kata.domain;

import java.util.Arrays;

public enum Suit {  // unordered
    CLUB('C'),
    DIAMOND('D'),
    HEARTS('H'),
    SPADES ('S');

    private final Character value;
    Suit(Character rank){
        this.value = rank;
    }

    public Character getValue() {
        return value;
    }

    public static Suit from(String s){

        return Arrays.stream(values())
                .filter(e -> e.value.charValue() == s.toUpperCase().charAt(0))
                .findFirst()
                .orElse(null);
    }
}
