package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IsFourOfAKindPredicate implements Predicate<List<Card>>, Function<List<Card>, Map.Entry<Boolean, Integer>> {

    @Override
    public boolean test(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Optional<Map.Entry<CardValue, Long>> entryWithCountOf4 = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.FOUR_OF_A_KIND.getSameValueCardCount())
                .findFirst();

        if(entryWithCountOf4.isPresent()){
            return true;
        }
        return false;
    }

    // key: isMatch | boolean, value = rank | Long
    public Map.Entry<Boolean, Integer> apply(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Optional<Map.Entry<CardValue, Long>> entryWithCountOf4 = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.FOUR_OF_A_KIND.getSameValueCardCount())
                .findFirst();

        if(entryWithCountOf4.isPresent()){
            return Map.entry(Boolean.TRUE, Ranks.FOUR_OF_A_KIND.getRank());
        }

        return Map.entry(Boolean.FALSE, 0);
    }
}
