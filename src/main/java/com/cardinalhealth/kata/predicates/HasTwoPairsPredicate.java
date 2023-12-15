package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HasTwoPairsPredicate implements Predicate<List<Card>> {
    @Override
    public boolean test(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        long numberOfPairs = cardsGroupedByValue.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == Ranks.PAIR.getSameValueCardCount())
                .count();
        return numberOfPairs == 2;
    }
}
