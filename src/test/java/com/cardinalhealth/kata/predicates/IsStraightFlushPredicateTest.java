package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IsStraightFlushPredicateTest {

    @Test
    @DisplayName("Poker hand with 5 cards of consecutive values = STRAIGH_FLUSH")
    public void testApply(){
        final Card c1 = new Card(Suit.CLUB, "5");
        final Card c2 = new Card(Suit.CLUB, "6");
        final Card c3 = new Card(Suit.CLUB, "7");
        final Card c4 = new Card(Suit.CLUB, "8");
        final Card c5 = new Card(Suit.CLUB, "9");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final boolean actual = new IsStraightFlushPredicate().test(cards);

        Assertions.assertTrue( actual);
    }

    @Test
    @DisplayName("Poker hand with 5 cards of consecutive values but different suits != STRAIGH_FLUSH")
    public void testApplyDifferentSuits(){
        final Card c1 = new Card(Suit.CLUB, "5");
        final Card c2 = new Card(Suit.CLUB, "6");
        final Card c3 = new Card(Suit.CLUB, "7");
        final Card c4 = new Card(Suit.DIAMOND, "8");
        final Card c5 = new Card(Suit.HEARTS, "9");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final boolean actual = new IsStraightFlushPredicate().test(cards);

        Assertions.assertFalse( actual);
    }

}
