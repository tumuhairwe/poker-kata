package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.domain.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoPairFunctionTest {
    @Test
    @DisplayName("2 cards of same value + 2 card of pair = TWO_PAIR")
    public void testApplyTwoPair(){
        final Card c1 = new Card(Suit.CLUB, "3");
        final Card c2 = new Card(Suit.CLUB, "3");
        final Card c3 = new Card(Suit.HEARTS, "4");
        final Card c4 = new Card(Suit.HEARTS, "4");
        final Card c5 = new Card(Suit.DIAMOND, "6");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final Map.Entry<Ranks, Long> actual = new TwoPairFunction().apply(cards);

        assertEquals(Ranks.TWO_PAIRS, actual.getKey());
        assertEquals(4, actual.getValue());
    }
    @Test
    @DisplayName("3 different cards + 1 card of pair != TWO_PAIR")
    public void testApplyTwoPairIncompletePair(){
        final Card c1 = new Card(Suit.CLUB, "3");
        final Card c2 = new Card(Suit.CLUB, "3");
        final Card c3 = new Card(Suit.HEARTS, "4");
        final Card c4 = new Card(Suit.HEARTS, "7");
        final Card c5 = new Card(Suit.DIAMOND, "6");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final Map.Entry<Ranks, Long> actual = new TwoPairFunction().apply(cards);

        assertEquals(Ranks.UNKNOWN, actual.getKey());
        assertEquals(0, actual.getValue());
    }
}
