package com.cardinalhealth.kata.domain;

import java.util.*;

public enum CardValue implements Comparator<CardValue> {
    TWO(2), THREE(3),
    FOUR(4), FIVE(5),
    SIX(6), SEVEN(7),
    EIGHT(8), NINE(9),
    TEN(10),

    J(11),  // jack
    Q(12),  // queen
    K(13),  // king
    A(14);  //ace

    private int value;
    CardValue(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CardValue from(String s){
        return validValues().get(s);
    }
    public static Map<String,CardValue> validValues(){
        final Map<String,CardValue> values = new HashMap<>();
        values.put("2", TWO);
        values.put("3", THREE);
        values.put("4", FOUR);
        values.put("5", FIVE);
        values.put("6", SIX);
        values.put("7", SEVEN);
        values.put("8", EIGHT);
        values.put("9", NINE);
        values.put("10", TEN);
        values.put("J", J);
        values.put("Q", Q);
        values.put("K", K);
        values.put("A", A);
        return values;
    }
    @Override
    public int compare(CardValue o1, CardValue o2) {
        return o1.getValue() - o2.getValue();
    }
}
