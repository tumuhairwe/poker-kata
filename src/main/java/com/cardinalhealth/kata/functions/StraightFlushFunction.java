package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StraightFlushFunction implements Function<List<Card>, Map.Entry<Ranks, Long>> {

    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        // a) determine if is same suite
        boolean isSameSuite = cards.stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet())
                .size() == 1;
        boolean isDistinctNumbers = true;
        for(int i=1; i<cards.size(); i++){
            boolean isNotDuplicate = cards.get(i-1).getValue().getValue() != cards.get(i).getValue().getValue();
            isDistinctNumbers = isDistinctNumbers && isNotDuplicate;
        }

        // b) determine if consecutive
        cards.sort(Comparator.comparing(Card::getValue));

        boolean isConsecutive = false;
        Card lowest = Collections.min(cards);
        Card highest = Collections.max(cards);
        if(isDistinctNumbers && (highest.getValue().getValue() - lowest.getValue().getValue() + 1) == Ranks.STRAIGHT_FLUSH.getSameValueCardCount()){
            isConsecutive = true;
        }
        //else  ordered but values not consecutive
        // @TODO judes | Reuse StraightFunction
        if((cards.size() == Ranks.STRAIGHT_FLUSH.getSameValueCardCount()) && isSameSuite && isConsecutive){
            return Map.entry(Ranks.STRAIGHT_FLUSH, Long.valueOf(highest.getValue().getValue()));
        }
        return Map.entry(Ranks.UNKNOWN, 0L);
    }
}
