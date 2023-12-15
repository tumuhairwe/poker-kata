package com.cardinalhealth.kata;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.predicates.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class PokerHandMatcher implements Function<List<Card>, List<PokerHandResult>> {

    private final Map<Predicate<List<Card>>, PokerHandResult> knownCases = new HashMap<>();

    public PokerHandMatcher(){
        initialize();
    }
    // @PostConstruct
    public void initialize(){
        // 2
        Predicate<List<Card>> hasTwoPairs = new IsPairPredicate();
        Predicate<List<Card>> isPair = new HasTwoPairsPredicate();

        // 3
        Predicate<List<Card>> is3OfAKind = new IsThreeOfAKindPredicate();
        Predicate<List<Card>> isFullHouse = new IsFullHousePredicate();

        // 4
        Predicate<List<Card>> isFourOfAKind = new IsFourOfAKindPredicate();

        // 5
        Predicate<List<Card>> isFlush = new IsFlushPredicate();
        Predicate<List<Card>> isStraight = new IsStraightPredicate();
        Predicate<List<Card>> isStraightFlush = new IsStraightFlushPredicate();

        knownCases.put(null, PokerHandResult.HIGH_CARD);
        knownCases.put(isPair, PokerHandResult.PAIR);
        knownCases.put(hasTwoPairs, PokerHandResult.TWO_PAIRS);
        knownCases.put(is3OfAKind, PokerHandResult.THREE_OF_A_KIND);
        knownCases.put(isStraight, PokerHandResult.STRAIGHT);

        knownCases.put(isFlush, PokerHandResult.FLUSH);
        knownCases.put(isFullHouse, PokerHandResult.FULL_HOUSE);
        knownCases.put(isFourOfAKind, PokerHandResult.FOUR_OF_A_KIND);
        knownCases.put(isStraightFlush, PokerHandResult.STRAIGHT_FLUSH);
    }
    @Override
    public List<PokerHandResult> apply(List<Card> cards) {
        // 5
        Predicate<List<Card>> isFlush = new IsFlushPredicate();
        Predicate<List<Card>> isStraight = new IsStraightPredicate();
        Predicate<List<Card>> isStraightFlush = new IsStraightFlushPredicate();
        if(isFlush.test(cards) || isStraight.test(cards) || isStraightFlush.test(cards)){

            return new PokerHandMatcher().apply(cards); // max-card-count = 5 => we're done
        }
        return null;
    }
}
