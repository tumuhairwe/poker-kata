package com.cardinalhealth.kata;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.functions.*;
import com.cardinalhealth.kata.predicates.*;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class RankCalculatorV2  implements Function<List<Card>, Map.Entry<Ranks, Long> > {

    //@Autowire
    private Map<Predicate<List<Card>>, Function<List<Card>, Map.Entry<Ranks, Long>>> predicateToFunctiobrRegistry = new HashMap<>();

    // Use Spring @Bean to create bean manually
    public RankCalculatorV2(){
        // Spring could inject but we need to match order of evaluation based on defined rule
        predicateToFunctiobrRegistry.put(new IsStraightFlushPredicate(), new StraightFlushFunction());
        predicateToFunctiobrRegistry.put(new IsFourOfAKindPredicate(), new FourOfAKindFunction());
        predicateToFunctiobrRegistry.put(new IsFullHousePredicate(), new FullHouseFunction());
        predicateToFunctiobrRegistry.put(new IsFlushPredicate(), new FlushFunction());
        predicateToFunctiobrRegistry.put(new IsStraightPredicate(), new StraightFunction());
        predicateToFunctiobrRegistry.put(new IsThreeOfAKindPredicate(), new ThreeOfAKindFunction());
        predicateToFunctiobrRegistry.put(new HasTwoPairsPredicate(), new TwoPairFunction());
        predicateToFunctiobrRegistry.put(new IsPairPredicate(), new PairFunction());
    }

    @Override
    public Map.Entry<Ranks, Long> apply(List<Card> cards) {
        for (Map.Entry<Predicate<List<Card>>, Function<List<Card>, Map.Entry<Ranks, Long>>> entry : predicateToFunctiobrRegistry.entrySet()){
            if(entry.getKey().test(cards)){
                return entry.getValue().apply(cards);
            }
        }

        Collections.sort(cards, Collections.reverseOrder());
        CardValue c = cards.iterator().next().getValue();
        return Map.entry(Ranks.HIGH_CARD, Long.valueOf(c.getValue()));
    }
}
