package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HasTwoPairsPredicateTest {

    @Test
    @DisplayName("Poker hand with 2 pairs is a TWO_PAIR")
    public void testApply(){
        final Card c1 = new Card(Suit.CLUB, "2");
        final Card c2 = new Card(Suit.CLUB, "2");
        final Card c3 = new Card(Suit.CLUB, "3");
        final Card c4 = new Card(Suit.CLUB, "4");
        final Card c5 = new Card(Suit.HEARTS, "4");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final boolean actual = new HasTwoPairsPredicate().test(cards);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Poker hand with 1 pair != TWO_PAIR")
    public void testApplyNotTwoPairs(){
        final Card c1 = new Card(Suit.CLUB, "2");
        final Card c2 = new Card(Suit.CLUB, "2");
        final Card c3 = new Card(Suit.CLUB, "3");
        final Card c4 = new Card(Suit.CLUB, "4");
        final Card c5 = new Card(Suit.HEARTS, "K");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final boolean actual = new HasTwoPairsPredicate().test(cards);

        assertFalse(actual);
    }
}
