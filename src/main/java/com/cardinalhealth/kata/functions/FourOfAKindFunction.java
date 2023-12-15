package com.cardinalhealth.kata.functions;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FourOfAKindFunction implements Function<List<Card>, Map.Entry<Ranks, Long>> {

    // key: isMatch | boolean, value = rank | Long
    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Optional<Map.Entry<CardValue, Long>> entryWithCountOf4 = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() >= Ranks.FOUR_OF_A_KIND.getSameValueCardCount())
                .findFirst();

        if(entryWithCountOf4.isPresent()){
            Long valueOfAll4Cards = Long.valueOf(entryWithCountOf4.get().getKey().getValue());
            return Map.entry(Ranks.FOUR_OF_A_KIND, valueOfAll4Cards);
        }

        return Map.entry(Ranks.UNKNOWN, 0l);
    }
}
