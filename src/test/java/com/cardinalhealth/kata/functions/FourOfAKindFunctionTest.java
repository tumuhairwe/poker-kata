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

public class FourOfAKindFunctionTest {

    @Test
    @DisplayName("Hand with 4/5 cards of the same value should be FOUR_OF_KIND")
    public void testApply(){
        final Card c1 = new Card(Suit.CLUB, "2");
        final Card c2 = new Card(Suit.CLUB, "2");
        final Card c3 = new Card(Suit.CLUB, "2");
        final Card c4 = new Card(Suit.CLUB, "2");
        final Card c5 = new Card(Suit.HEARTS, "2");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final Map.Entry<Ranks, Long> actual = new FourOfAKindFunction().apply(cards);

        Assertions.assertEquals(2, actual.getValue());
        Assertions.assertEquals(Ranks.FOUR_OF_A_KIND, actual.getKey());
    }
    @Test
    @DisplayName("Hand with 3/5 cards of the same value should be FOUR_OF_KIND")
    public void testApplyIncompleteHandShouldBeUnknown(){
        final Card c1 = new Card(Suit.CLUB, "2");
        final Card c2 = new Card(Suit.CLUB, "2");
        final Card c3 = new Card(Suit.CLUB, "2");
        final Card c4 = new Card(Suit.CLUB, "5");
        final Card c5 = new Card(Suit.HEARTS, "6");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final Map.Entry<Ranks, Long> actual = new FourOfAKindFunction().apply(cards);

        Assertions.assertEquals(0, actual.getValue());
        Assertions.assertEquals(Ranks.UNKNOWN, actual.getKey());
    }

    @Test
    @DisplayName("Hand with 4/5 cards of the same value + 1 has differnt suite should be FOUR_OF_KIND")
    public void testApplyDoesNotCount4EntriesWithSameValueButDifferentSuits(){
        final Card c1 = new Card(Suit.CLUB, "2");
        final Card c2 = new Card(Suit.CLUB, "2");
        final Card c3 = new Card(Suit.CLUB, "2");
        final Card c4 = new Card(Suit.CLUB, "4");
        final Card c5 = new Card(Suit.HEARTS, "2");

        final List<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        final Map.Entry<Ranks, Long> actual = new FourOfAKindFunction().apply(cards);

        Assertions.assertEquals(2, actual.getValue());
        Assertions.assertEquals(Ranks.FOUR_OF_A_KIND, actual.getKey());
    }
}
