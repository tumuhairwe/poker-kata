package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThreeOfAKindFunction implements Function<List<Card>, Map.Entry<Ranks, Long>> {
    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Optional<Map.Entry<CardValue, Long>> entryWithCountOf3 = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.THREE_OF_A_KIND.getSameValueCardCount())
                .findFirst();

        if(entryWithCountOf3.isPresent()){
            return Map.entry(Ranks.THREE_OF_A_KIND, entryWithCountOf3.get().getValue());
        }
        return Map.entry(Ranks.UNKNOWN, Long.valueOf(0));
    }
}
