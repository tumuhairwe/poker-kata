package com.cardinalhealth.kata;

import com.cardinalhealth.kata.domain.Card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHand {
    public static int MAX_SIZE = 5;
    private List<Card> cards;

    public PokerHand(final List<Card> hand){
        this.cards = hand;
    }
    //playerOne: 2H 3D 5S 9C KD, playerTwo: 2C 3H 4S 8C AH
    //playerOne: 2H 4S 4C 2D 4H, playerTwo: 2S 8S AS QS 3S
    //playerOne: 2H 3D 5S 9C KD, playerTwo: 2C 3H 4S 8C KH
    //playerOne: 2H 3D 5S 9C KD, playerTwo: 2D 3H 5C 9S KH
    public static PokerHand from(String hand1){
        List<Card> cards = Arrays
                .stream(hand1.split("\\s+"))
                .map(card -> Card.from(card))
                .collect(Collectors.toList());

        return new PokerHand(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}
