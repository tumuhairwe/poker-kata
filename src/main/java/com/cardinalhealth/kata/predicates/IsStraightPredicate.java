package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IsStraightPredicate implements Predicate<List<Card>>, Function<List<Card>, Map.Entry<Ranks, Long>> {

    @Override
    public boolean test(List<Card> cards) {
        boolean isConsecutive = false;

        //cards.sort(Comparator.comparing(Card::getValue));

        List<CardValue> cardValues = cards.stream().map(v -> v.getValue()).collect(Collectors.toList());
        CardValue lowest = Collections.min(cardValues);
        CardValue highest = Collections.max(cardValues);
        // consecutive count is broken (4, 5, 6, 7, 8)
        if((highest.getValue() - lowest.getValue() + 1) == Ranks.STRAIGHT.getSameValueCardCount()){
            isConsecutive = true;
        }
        //else  ordered but values not consecutive

        return isConsecutive;
    }

    @Override
    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        boolean isConsecutive = false;

        List<CardValue> cardValues = cards.stream().map(v -> v.getValue()).collect(Collectors.toList());
        CardValue lowest = Collections.min(cardValues);
        CardValue highest = Collections.max(cardValues);
        if((highest.getValue() - lowest.getValue()) == Ranks.STRAIGHT.getSameValueCardCount()){
            isConsecutive = true;
        }
        //else  ordered but values not consecutive

        if(isConsecutive){
            return Map.entry(Ranks.STRAIGHT, Long.valueOf(highest.getValue()));
        }
        return Map.entry(Ranks.UNKNOWN, 0l);
    }
}
