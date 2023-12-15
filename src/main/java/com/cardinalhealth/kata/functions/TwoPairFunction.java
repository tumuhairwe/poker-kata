package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TwoPairFunction implements Function<List<Card>, Map.Entry<Ranks, Long>> {
    @Override
    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        if(cardsGroupedByValue.size() >=  Ranks.PAIR.getSameValueCardCount()){
            // pair 1
            Map.Entry<CardValue, Long> pair1, pair2;
            List<Map.Entry<CardValue, Long>> entriesWithCountOf2 = cardsGroupedByValue
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue() == Ranks.PAIR.getSameValueCardCount())
                    .collect(Collectors.toList());

            if(entriesWithCountOf2.size() == 2){
                pair1 = entriesWithCountOf2.get(0);
                pair2 = entriesWithCountOf2.get(1);
                if(pair1.getKey().getValue() != pair2.getKey().getValue()){
                    int higherOfThe2 = Math.max(pair1.getKey().getValue(), pair2.getKey().getValue());
                    return Map.entry(Ranks.TWO_PAIRS, Long.valueOf(higherOfThe2));
                }
                else {  // both pairs are the same value (e.g. pair-1 = [2,2]  pair-2 =[2,2])   -> should've been caugh by 4-of-a-kind rule

                }
            }
            // pair 2
        }
        List<CardValue> cardValues = cards.stream().map(v -> v.getValue()).collect(Collectors.toList());
        // cards.sort(Comparator.comparing(Card::getValue));

        // given e.g. 2, 3, 4, 5, 6 => min=2, max=6 -> diff = (6-2) = 1
        CardValue lowest = Collections.min(cardValues);
        CardValue highest = Collections.max(cardValues);

        return Map.entry(Ranks.UNKNOWN, 0L);

    }
}
