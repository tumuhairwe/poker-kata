package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IsFlushPredicateTest {

    @Test
    @DisplayName("Poker hand with  3-of-a-kind should be FLUS")
    public void testApply(){
        final Card c1 = new Card(Suit.CLUB, "2");
        final Card c2 = new Card(Suit.CLUB, "A");
        final Card c3 = new Card(Suit.CLUB, "5");
        final Card c4 = new Card(Suit.CLUB, "K");
        final Card c5 = new Card(Suit.CLUB, "Q");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final boolean actual = new IsFlushPredicate().test(cards);

        Assertions.assertTrue( actual);
    }

    @Test
    @DisplayName("Poker hand with 4 clubs and 1 heart is NOT a flush")
    public void testDoesNotApply(){
        final Card c1 = new Card(Suit.CLUB, "3");
        final Card c2 = new Card(Suit.CLUB, "4");
        final Card c3 = new Card(Suit.CLUB, "5");
        final Card c4 = new Card(Suit.CLUB, "J");
        final Card c5 = new Card(Suit.HEARTS, "Q");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final boolean actual = new IsFlushPredicate().test(cards);

        Assertions.assertFalse( actual);
    }
}
