package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.domain.CardValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FullHouseFunction implements Function<List<Card>, Map.Entry<Ranks, Long>> {

    @Override
    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Optional<CardValue> entryWithCountOf3 = cardsGroupedByValue
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == Ranks.FULL_HOUSE.getSameValueCardCount())
                .map(e -> e.getKey())
                .findFirst();

        if(entryWithCountOf3.isPresent()){
            return Map.entry(Ranks.FULL_HOUSE, Long.valueOf(entryWithCountOf3.get().getValue()));
        }

        return Map.entry(Ranks.UNKNOWN, 0L);
    }
}
