package com.cardinalhealth.kata;

import com.cardinalhealth.kata.domain.Ranks;

import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        // read input
        String playerOne = "2H 3D 5S 9C KD", playerTwo= "2C 3H 4S 8C AH";
        //String playerOne = "2H 4S 4C 2D 4H", playerTwo ="2S 8S AS QS 3S";
        //String playerOne = "2H 3D 5S 9C KD", playerTwo = "2C 3H 4S 8C KH";
        //String playerOne = "2H 3D 5S 9C KD", playerTwo = "2D 3H 5C 9S KH";

        PokerHand p1 = PokerHand.from(playerOne);
        PokerHand p2 = PokerHand.from(playerTwo);

        // calculate rank
        RankCalculator calculator = new RankCalculator();
        //RankCalculatorV2 calculatorv2 = new RankCalculatorV2();
        Map.Entry<Ranks, Long> p1Rank = calculator.apply(p1.getCards());
        Map.Entry<Ranks, Long> p2Rank = calculator.apply(p2.getCards());

        // outright winner
        boolean weHaveAWinner = false;
        do {
            if(p1Rank.getKey().getRank() > p2Rank.getKey().getRank()){
                System.out.println("P1 wins");
                printResults1(p1Rank, p2Rank);
                weHaveAWinner = true;
            }
            else if(p2Rank.getKey().getRank() > p1Rank.getKey().getRank()){
                System.out.println("P2 wins");
                printResults1(p1Rank, p2Rank);
                weHaveAWinner = true;
            }
            else if(p1Rank.getKey() == Ranks.THREE_OF_A_KIND && p2Rank.getKey() == Ranks.THREE_OF_A_KIND){
                if(p1Rank.getValue() > p2Rank.getValue()){
                    System.out.println("3 of a kind tie. P1 wins with " + p1Rank.getValue());
                }
                if(p2Rank.getValue() > p1Rank.getValue()) {
                    System.out.println("3 of a kind tie. P2 wins with " + p2Rank.getValue());
                }
            }
            else if(p1Rank.getKey() == Ranks.PAIR && p2Rank.getKey() == Ranks.PAIR){
                TieBreakService.breakSinglePairTie(p1, p2);
            }
            else if(p1Rank.getKey() == Ranks.TWO_PAIRS && p2Rank.getKey() == Ranks.TWO_PAIRS){
                System.out.println("There was a 2 pair tie. Time to choose the higher of the 2 (5th card)");
                TieBreakService.breakTwoPairTie(p1, p1Rank, p2, p2Rank);
            }
            else if(p1Rank.getKey() == Ranks.HIGH_CARD && p2Rank.getKey() == Ranks.HIGH_CARD){
                // we have 2 HIGH_CARDS (i.e. no outright winner)

                if(p1Rank.getValue() > p2Rank.getValue()){
                    System.out.println("Both players had a HIGH_CARD: P1 Wins!!");
                    System.out.println("p1 score =" + p1Rank.getValue());
                }
                if(p2Rank.getValue() > p1Rank.getValue()){
                    System.out.println("Both players had a HIGH_CARD: P2 Wins!!");
                    System.out.println("p1 score =" + p1Rank.getValue());
                }
                else {
                    System.out.println("Both players had a HIGH_CARD of equal value. Need to draw again");
                }
                weHaveAWinner = true;
            }
            else {
                // unlikely == if it wasn't a 2 pair tie or 1 pair tie --- we would've had a Flush or RoyalFlush
                System.out.println("DEBUG INFO: Player 1: input:" + playerOne + " card:" + p1.getCards()+ " rank: " + p1Rank);
                System.out.println("DEBUG INFO: Player 2: input:" + playerTwo + " card:" + p2.getCards()+ " rank: " + p2Rank);

                throw new RuntimeException("Something unxpected has happened with an entry that doesn't match any of the known rules. See log above^^");
            }
        }while (!weHaveAWinner);

        //int rank = new HandComparator().compare(p1, p2);
    }

    private static void printResults1(Map.Entry<Ranks, Long> p1Rank, Map.Entry<Ranks, Long> p2Rank){
        System.out.println("P1 rank => " + p1Rank);
        System.out.println("P2 wins => " + p2Rank);
    }
}
