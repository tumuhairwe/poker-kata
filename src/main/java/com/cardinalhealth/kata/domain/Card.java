package com.cardinalhealth.kata.domain;

import javax.swing.*;

public class Card implements Comparable<Card>{
    public Suit suit;
    public CardValue value;

    public Card(Suit suit, CardValue value){
        this.suit = suit;
        this.value = value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Card(Suit suit, String value){
        this.suit = suit;

        if(CardValue.validValues().keySet().contains(value)){
            this.value = CardValue.from(value);
        }
        else {
            throw new RuntimeException("Card value must be one of [ 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A]");
        }
    }

    public Suit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }
    public static Card from(String card){
        final Suit s = Suit.from(card.substring(card.length()-1));   // C, D, H, and S
        return new Card(s, Character.valueOf(card.charAt(0)).toString());
    }

    public String toString() {
        return value.toString() + suit;
    }

    @Override
    public int compareTo(Card card) {
        return card.getValue().getValue();
    }
}
