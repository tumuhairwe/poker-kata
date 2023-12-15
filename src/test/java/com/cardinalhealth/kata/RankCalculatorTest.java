package com.cardinalhealth.kata;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Ranks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankCalculatorTest {

    @Test
    @DisplayName("STRAIGHT_FLUSH: 5 cards with consecutive values")
    public void testCalculateStraightFlush(){
        PokerHand p = PokerHand.from("2C 3C 4C 5C 6C");

        RankCalculator calculator = new RankCalculator();
        final Map.Entry<Ranks, Long> score = calculator.apply(p.getCards());
        assertEquals(Ranks.STRAIGHT_FLUSH, score.getKey());
        assertEquals(6, score.getValue());
    }
    @Test
    @DisplayName("FOUR_OF_A_KIND : Four cards of same value")
    public void testCalculateFourOfAKind(){
        PokerHand p = PokerHand.from("2C 2C 2C 2C 3C");

        RankCalculator calculator = new RankCalculator();
        final Map.Entry<Ranks, Long> score = calculator.apply(p.getCards());
        assertEquals(Ranks.FOUR_OF_A_KIND, score.getKey());
        assertEquals(2, score.getValue());
    }
    @Test
    @DisplayName("FULL_HOUSE : 3 cards of same value + 2 card of pair")
    public void testCalculateFullHouse(){
        PokerHand p = PokerHand.from("3C 3C 3C 2C 2C");

        RankCalculator calculator = new RankCalculator();
        final Map.Entry<Ranks, Long> score = calculator.apply(p.getCards());
        assertEquals(Ranks.FULL_HOUSE, score.getKey());
        assertEquals(3, score.getValue());
    }
    @Test
    @DisplayName("FLUSH: 5 of the same suit should be a FLUSH ")
    public void testCalculateFlush(){
        PokerHand p = PokerHand.from("3C KC QC AC 2C");

        RankCalculator calculator = new RankCalculator();
        final Map.Entry<Ranks, Long> score = calculator.apply(p.getCards());
        assertEquals(Ranks.FLUSH, score.getKey());
        assertEquals(3, score.getValue());
    }
    @Test
    @DisplayName("PAIR : 2 cards of same value + 3 different values = PAIR")
    public void testCalculatePair(){
        PokerHand p = PokerHand.from("3C 3C QC AD 2H");

        RankCalculator calculator = new RankCalculator();
        final Map.Entry<Ranks, Long> score = calculator.apply(p.getCards());
        assertEquals(Ranks.PAIR, score.getKey());
        assertEquals(3, score.getValue());
    }
    @Test
    @DisplayName("HIGH_CARD: 3 cards of same value + 2 card of pair = HIGH_CARD")
    public void testCalculateHighCard(){
        PokerHand p = PokerHand.from("3C 9D QC AD 2H");

        RankCalculator calculator = new RankCalculator();
        final Map.Entry<Ranks, Long> score = calculator.apply(p.getCards());
        assertEquals(Ranks.HIGH_CARD, score.getKey());
        assertEquals(3, score.getValue());
    }

    @Test
    @DisplayName("TWO_PAIRS: 2 cards of same value x 2 is a 2 pair")
    public void testCalculate() {
        PokerHand p = PokerHand.from("3H 3H 4C 4C KD");

        RankCalculator calculator = new RankCalculator();
        final Map.Entry<Ranks, Long> score = calculator.apply(p.getCards());

        assertEquals(Ranks.TWO_PAIRS, score.getKey());
        assertEquals(4, score.getValue());
    }
}
