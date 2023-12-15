package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IsStraightPredicateTest {

    @Test
    @DisplayName("Poker hand with 5 consecutive values should return STRAIGHT")
    public void testApply(){
        final Card c1 = new Card(Suit.CLUB, "4");
        final Card c2 = new Card(Suit.CLUB, "5");
        final Card c3 = new Card(Suit.CLUB, "6");
        final Card c4 = new Card(Suit.CLUB, "7");
        final Card c5 = new Card(Suit.CLUB, "8");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final boolean actual = new IsStraightPredicate().test(cards);

        Assertions.assertTrue( actual);
    }

    @Test
    @DisplayName("Poker hand with 2,3,3,9, J is NOT STRAIGHT")
    public void testDoesNotApply(){
        final Card c1 = new Card(Suit.CLUB, "J");
        final Card c2 = new Card(Suit.CLUB, "2");
        final Card c3 = new Card(Suit.CLUB, "3");
        final Card c4 = new Card(Suit.CLUB, "3");
        final Card c5 = new Card(Suit.HEARTS, "9");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final boolean actual = new IsStraightPredicate().test(cards);

        Assertions.assertFalse( actual);
    }
}
