package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PairFunction implements Function<List<Card>, Map.Entry<Ranks, Long>> {
    @Override
    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Optional<Map.Entry<CardValue, Long>> entryWithCountOf2 = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.PAIR.getSameValueCardCount())
                .findFirst();

        if(entryWithCountOf2.isPresent()){
            Long valueOfBothCards = Long.valueOf(entryWithCountOf2.get().getKey().getValue());
            return Map.entry(Ranks.PAIR, valueOfBothCards);
        }

        return Map.entry(Ranks.UNKNOWN, 0L);
    }
}
