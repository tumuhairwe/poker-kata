package com.cardinalhealth.kata.ranks;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Pair {

    private static final int SAME_VALUE_CARD_COUNT = 2;

    private List<Card> cards;
    public Pair(List<Card> hand){
        this.cards = hand;
    }
    public Long getRank(){
        Map<CardValue, Long> cardsGroupedByValue = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        Optional<Map.Entry<CardValue, Long>> x = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == SAME_VALUE_CARD_COUNT)
                .findFirst();   // if 2 pairs in a hand == ??

        if(x.isPresent()){
            return x.get().getValue();
        }
        
        return Long.valueOf(0);
    }
}
