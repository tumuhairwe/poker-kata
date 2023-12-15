package com.cardinalhealth.kata;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;
import com.cardinalhealth.kata.functions.*;
import com.cardinalhealth.kata.predicates.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RankCalculator implements Function<List<Card>, Map.Entry<Ranks, Long> > {

    @Override
    public Map.Entry<Ranks, Long>  apply(List<Card> cards) {
        // in order of weight
        // 5 = straight flus
        if(new IsStraightFlushPredicate().test(cards)){
            return new StraightFlushFunction().apply(cards);
        }
        // 4 of a kind
        else if(new IsFourOfAKindPredicate().test(cards)){
            return new FourOfAKindFunction().apply(cards);
        }
        // full house
        else if(new IsFullHousePredicate().test(cards)){
            return new FullHouseFunction().apply(cards);
        }

        // flush
        else if(new IsFlushPredicate().test(cards)){
            return new FlushFunction().apply(cards);
        }

        // straight
        else if(new IsStraightPredicate().test(cards)){
             return new StraightFunction().apply(cards);
        }

        // three of akind
        else if(new IsThreeOfAKindPredicate().test(cards)){
            return new ThreeOfAKindFunction().apply(cards);
        }

        // 2 pairs
        else if(new HasTwoPairsPredicate().test(cards)){
            return new TwoPairFunction().apply(cards);  // check if caller also has matching 2 pairs
        }

        // pair
        else if(new IsPairPredicate().test(cards)){
            return new PairFunction().apply(cards);
        }

        // high card
        else{
            //cards.sort(Comparator.comparing(Card::getValue));
            Collections.sort(cards, Collections.reverseOrder());
            CardValue c = cards.iterator().next().getValue();
            return Map.entry(Ranks.HIGH_CARD, Long.valueOf(c.getValue()));
        }
    }
}
