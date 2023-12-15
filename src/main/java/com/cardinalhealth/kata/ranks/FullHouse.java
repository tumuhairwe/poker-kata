package com.cardinalhealth.kata.ranks;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.predicates.IsFullHousePredicate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FullHouse {

    private List<Card> cards;
    public FullHouse(List<Card> cards){
        this.cards = cards;
    }
    public boolean isValid(){
        return new IsFullHousePredicate().test(this.cards);
    }
    public Long getRank(){
        Map<CardValue, Long> cardsGroupedByValue = cards.stream().collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        Optional<Map.Entry<CardValue, Long>> entryWithCountOf3 = cardsGroupedByValue
                .entrySet().stream()
                .filter(e -> e.getValue() == Ranks.FULL_HOUSE.getSameValueCardCount())
                .findFirst();

        if(entryWithCountOf3.isPresent()){
            return entryWithCountOf3.get().getValue();
        }

        return Long.valueOf(0);
    }
}
