package com.cardinalhealth.kata.predicates;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.domain.Suit;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IsFlushPredicate implements Predicate<List<Card>>{

    @Override
    public boolean test(List<Card> cards) {
        Map<Suit, Long> cardsGroupedBySuit = cards.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));
        Optional<Map.Entry<Suit, Long>> entryWithCountOf5 = cardsGroupedBySuit
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.FLUSH.getSameValueCardCount())
                .findFirst();

        if(entryWithCountOf5.isPresent()){
            return true;
        }
        return false;
    }
}
