package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IsStraightFlushPredicate implements Predicate<List<Card>> {

    //public static final int SAME_VALUE_CARD_COUNT = 5;
    @Override
    public boolean test(List<Card> cards) {
        // a) determine if is same suite
        boolean isSameSuite = cards.stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet())
                .size() == 1;
        if(!isSameSuite){
            return false;    // not a flush
        }
        // b) determine if consecutive

        boolean isConsecutive = false;
        List<CardValue> cardValues = cards.stream().map(v -> v.getValue()).collect(Collectors.toList());
        // cards.sort(Comparator.comparing(Card::getValue));

        // given e.g. 2, 3, 4, 5, 6 => min=2, max=6 -> diff = (6-2) = 1
        CardValue lowest = Collections.min(cardValues);
        CardValue highest = Collections.max(cardValues);
        int diffBetweenEdges = (highest.getValue() - lowest.getValue())+1;

        if(diffBetweenEdges == Ranks.STRAIGHT_FLUSH.getSameValueCardCount()){
            isConsecutive = true;
        }
        //else  ordered but values not consecutive

        return (cards.size() == Ranks.STRAIGHT_FLUSH.getSameValueCardCount()) && isSameSuite && isConsecutive;
    }
}
