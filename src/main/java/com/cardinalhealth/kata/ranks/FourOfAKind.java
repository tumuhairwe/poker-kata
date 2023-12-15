package com.cardinalhealth.kata.ranks;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.predicates.IsFourOfAKindPredicate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FourOfAKind {

    private final List<Card> cards;

    public FourOfAKind(List<Card> hand){
        this.cards = hand;
    }

    public boolean isValid(){
        return new IsFourOfAKindPredicate().test(this.cards);
    }
    public Long getRank(){
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        Optional<Map.Entry<CardValue, Long>> entryWithFourOfAKind = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.FOUR_OF_A_KIND.getSameValueCardCount())
                .findFirst();

        if(entryWithFourOfAKind.isPresent()){
            return entryWithFourOfAKind.get().getValue();
        }

        return Long.valueOf(0);
    }
}
