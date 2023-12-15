package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StraightFunction implements Function<List<Card>, Map.Entry<Ranks, Long>> {

    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        // b) determine if consecutive
        cards.sort(Comparator.comparing(Card::getValue));

        boolean isConsecutive = false;
        Card lowest = Collections.min(cards);
        Card highest = Collections.max(cards);
        if((highest.getValue().getValue() - lowest.getValue().getValue()) == Ranks.STRAIGHT_FLUSH.getSameValueCardCount()){
            isConsecutive = true;
        }
        //else  ordered but values not consecutive

        if((cards.size() == Ranks.STRAIGHT_FLUSH.getSameValueCardCount()) && isConsecutive){
            return Map.entry(Ranks.STRAIGHT_FLUSH, Long.valueOf(highest.getValue().getValue()));
        }
        return Map.entry(Ranks.UNKNOWN, 0L);
    }
}
