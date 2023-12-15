package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.PokerHand;
import com.cardinalhealth.kata.RankCalculator;
import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.domain.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullHouseFunctionTest {

    @Test
    @DisplayName("3 cards of same value + 2 card of pair = FULL_HOUSE")
    public void testApplyFullHouse(){
        final Card c1 = new Card(Suit.CLUB, "3");
        final Card c2 = new Card(Suit.CLUB, "3");
        final Card c3 = new Card(Suit.CLUB, "3");
        final Card c4 = new Card(Suit.HEARTS, "2");
        final Card c5 = new Card(Suit.HEARTS, "2");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final Map.Entry<Ranks, Long> actual = new FullHouseFunction().apply(cards);

        assertEquals(Ranks.FULL_HOUSE, actual.getKey());
        assertEquals(3, actual.getValue());
    }
    @Test
    @DisplayName("2 cards of same value + 2 card of pair + 1 extra != FULL_HOUSE")
    public void testApplyNotFullFullHouse(){
        final Card c1 = new Card(Suit.CLUB, "3");
        final Card c2 = new Card(Suit.CLUB, "3");
        final Card c3 = new Card(Suit.CLUB, "4");
        final Card c4 = new Card(Suit.HEARTS, "4");
        final Card c5 = new Card(Suit.HEARTS, "2");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final Map.Entry<Ranks, Long> actual = new FullHouseFunction().apply(cards);

        assertEquals(Ranks.UNKNOWN, actual.getKey());
        assertEquals(0, actual.getValue());
    }
}
