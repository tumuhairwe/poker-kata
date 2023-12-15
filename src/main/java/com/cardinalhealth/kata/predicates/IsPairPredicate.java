package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IsPairPredicate implements Predicate<List<Card>> {

    @Override
    public boolean test(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        Optional<Map.Entry<CardValue, Long>> entryWithCountOF2 = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.PAIR.getSameValueCardCount())
                .findFirst();   // if 2 pairs in a hand == ??

        if(entryWithCountOF2.isPresent()){
            return true;
        }
        return false;
    }
}
