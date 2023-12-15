package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlushFunctionTest {

    @Test
    @DisplayName("5 cards of same suit should be FLUSH")
    public void testApply(){
        final Card c1 = new Card(Suit.CLUB, "2");
        final Card c2 = new Card(Suit.CLUB, "2");
        final Card c3 = new Card(Suit.CLUB, "2");
        final Card c4 = new Card(Suit.CLUB, "2");
        final Card c5 = new Card(Suit.CLUB, "2");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final Map.Entry<Ranks, Long> actual= new FlushFunction().apply(cards);

        Assertions.assertEquals(Ranks.FLUSH, actual.getKey());
        Assertions.assertEquals(2, actual.getValue());
    }
    @Test
    @DisplayName("PokerHand without 5 cards of same suit should NOT be FLUSH")
    public void testNoteApply(){
        final Card c1 = new Card(Suit.CLUB, "2");
        final Card c2 = new Card(Suit.CLUB, "2");
        final Card c3 = new Card(Suit.CLUB, "2");
        final Card c4 = new Card(Suit.CLUB, "3");
        //final Card c5 = new Card(Suit.CLUB, "2");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        //cards.add(c5);
        final Map.Entry<Ranks, Long> actual= new FlushFunction().apply(cards);

        Assertions.assertEquals(Ranks.UNKNOWN, actual.getKey());
        Assertions.assertEquals(0, actual.getValue());
    }
    @Test
    @DisplayName("PokerHand with 5 cards of different suits should NOT be FLUSH")
    public void testNoteApplUDifferentSuit(){
        final Card c1 = new Card(Suit.HEARTS, "2");
        final Card c2 = new Card(Suit.CLUB, "2");
        final Card c3 = new Card(Suit.DIAMOND, "2");
        final Card c4 = new Card(Suit.CLUB, "3");
        final Card c5 = new Card(Suit.CLUB, "2");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final Map.Entry<Ranks, Long> actual= new FlushFunction().apply(cards);

        Assertions.assertEquals(Ranks.UNKNOWN, actual.getKey());
        Assertions.assertEquals(0, actual.getValue());
    }
}
