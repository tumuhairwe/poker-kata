package com.cardinalhealth.kata;

import com.cardinalhealth.kata.domain.Card;
import com.cardinalhealth.kata.domain.CardValue;
import com.cardinalhealth.kata.domain.Ranks;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TieBreakService {

    public static boolean breakSinglePairTie(PokerHand p1, PokerHand p2){
        boolean tieIsBroken = false;
        // a) remove cards NOT forming the pair (in both)
        Optional<Map.Entry<CardValue, Long>> p1Card_with_countOfOne = p1.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst();

        Optional<Map.Entry<CardValue, Long>> p2Card_with_countOfOne = p2.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst();

        // b) rank based on those 3
        if (p1Card_with_countOfOne.isPresent() && p2Card_with_countOfOne.isPresent()){
            if(p1Card_with_countOfOne.get().getKey().getValue() > p2Card_with_countOfOne.get().getKey().getValue()){
                System.out.println("P1 wins");
                System.out.println(p1Card_with_countOfOne.get().getKey());
                tieIsBroken = true;
            }
            else if(p2Card_with_countOfOne.get().getKey().getValue() > p1Card_with_countOfOne.get().getKey().getValue()){
                System.out.println("P2 wins");
                System.out.println(p2Card_with_countOfOne.get().getKey());
                tieIsBroken = true;
            }
        }
        else {
            System.out.println("We have an EXACT 2 pair tie (no fifth card)... Either we have invalid (input cards.size*() is not 5)" +
                    " or we are in the iteration after removing the fifith card and we are stucj with 4 cards that are exact ties");
        }

        return tieIsBroken;
    }
    public static boolean breakTwoPairTie(PokerHand p1, Map.Entry<Ranks, Long> p1Rank, PokerHand p2, Map.Entry<Ranks, Long> p2Rank) {
        boolean tieIsBroken = false;
        // determine which pair is higher -> use that value of the remaining card
        Map<CardValue, Long> p1GroupedByValue = p1.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        Map<CardValue, Long> p2GroupedByValue = p2.getCards()
                .stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        // find entry whose value is NOT one of the 2 pairs
        Optional<Map.Entry<CardValue, Long>> p1RemainingCard = p1GroupedByValue.entrySet()
                .stream()
                .filter(e -> e.getKey().getValue() != p1Rank.getValue())
                .findFirst();
        Optional<Map.Entry<CardValue, Long>> p2RemainingCard = p2GroupedByValue.entrySet()
                .stream()
                .filter(e -> e.getKey().getValue() != p2Rank.getValue())
                .findFirst();
        if (p1RemainingCard.isPresent() && p2RemainingCard.isPresent()) {
            if (p1RemainingCard.get().getKey().getValue() > p2RemainingCard.get().getKey().getValue()) {
                System.out.println("P1 wins!");
                printResults2(p1RemainingCard.get(), p2RemainingCard.get());
                tieIsBroken = true;
            } else if (p2RemainingCard.get().getKey().getValue() < p1RemainingCard.get().getKey().getValue()) {
                System.out.println("P2 wins!");
                printResults2(p1RemainingCard.get(), p2RemainingCard.get());
                tieIsBroken = true;
            }
            else {
                // log.error("Failed to break tie when we should have")
                throw new RuntimeException("Failed to break tie");
            }
        }

        return tieIsBroken;
    }

    private static void printResults2(Map.Entry<CardValue, Long> p1Rank, Map.Entry<CardValue, Long> p2Rank){
        System.out.println("P1 rank => " + p1Rank);
        System.out.println("P2 wins => " + p2Rank);
    }
}
