package com.cardinalhealth.kata.predicates;


import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IsFullHousePredicate implements Predicate<List<Card>> {

    @Override
    public boolean test(List<Card> cards) {
        // 3 cards of same suit
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Optional<CardValue> entryWithCountOf3 = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.FULL_HOUSE.getSameValueCardCount())
                .map(e -> e.getKey())
                .findFirst();

        if(!entryWithCountOf3.isPresent()){
            return false;
        }
        // find remaining 2 -> must be pair
        List<Card> remaining2Cards = cards.stream()
                .filter(card -> !card.getValue().equals(entryWithCountOf3.get()))
                .collect(Collectors.toList());

        return new IsPairPredicate().test(remaining2Cards);
    }
}
