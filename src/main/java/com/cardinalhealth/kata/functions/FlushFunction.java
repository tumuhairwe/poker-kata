package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.domain.Suit;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FlushFunction implements Function<List<Card>, Map.Entry<Ranks, Long>> {
    @Override
    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        boolean isSameSuite = cards.stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet())
                .size() == 1;

        if(!isSameSuite || cards.size() != Ranks.FLUSH.getSameValueCardCount()){
            return Map.entry(Ranks.UNKNOWN, Long.valueOf(0));
        }
        // high card rules == card with highest value
        //Collections.sort(cards, Collections.reverseOrder());
        cards.sort(Comparator.naturalOrder());

        int val = cards.iterator().next().getValue().getValue();
        Long highestValue = Long.valueOf(val);
        return Map.entry(Ranks.FLUSH, highestValue);
    }
}
