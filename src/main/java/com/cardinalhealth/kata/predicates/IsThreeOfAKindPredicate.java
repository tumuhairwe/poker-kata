package com.cardinalhealth.kata.predicates;


import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IsThreeOfAKindPredicate implements Predicate<List<Card>> {

    public IsThreeOfAKindPredicate(){
    }
    @Override
    public boolean test(List<Card> cards) {
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Optional<Map.Entry<CardValue, Long>> entryWithCountOf3 = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.THREE_OF_A_KIND.getSameValueCardCount())
                .findFirst();

        if(entryWithCountOf3.isPresent()){
            return true;
        }
        return false;
    }
}
