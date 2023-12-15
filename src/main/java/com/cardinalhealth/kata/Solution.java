package com.cardinalhealth.kata;

import com.cardinalhealth.kata.domain.Ranks;

import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        // read input
        String playerOne = "2H 3D 5S 9C KD", playerTwo= "2C 3H 4S 8C AH";
        //playerOne: 2H 4S 4C 2D 4H, playerTwo: 2S 8S AS QS 3S
        //playerOne: 2H 3D 5S 9C KD, playerTwo: 2C 3H 4S 8C KH
        //playerOne: 2H 3D 5S 9C KD, playerTwo: 2D 3H 5C 9S KH

        PokerHand p1 = PokerHand.from(playerOne);
        PokerHand p2 = PokerHand.from(playerTwo);

        // calculate rank
        RankCalculator calculator = new RankCalculator();
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
            else if(p1Rank.getKey() == Ranks.PAIR && p2Rank.getKey() == Ranks.PAIR){
                TieBreakService.breakSinglePairTie(p1, p2);
            }
            else if(p1Rank.getKey() == Ranks.TWO_PAIRS && p2Rank.getKey() == Ranks.TWO_PAIRS){
                System.out.println("There was a 2 pair tie. Time to choose the higher of the 2 (5th card)");
                TieBreakService.breakTwoPairTie(p1, p1Rank, p2, p2Rank);
            }
            else {
                // unlikely == if it wasn't a 2 pair tie or 1 pair tie --- we would've had a Flush or RoyalFlush
            }
        }while (!weHaveAWinner);

        //int rank = new HandComparator().compare(p1, p2);
    }

    private static void printResults1(Map.Entry<Ranks, Long> p1Rank, Map.Entry<Ranks, Long> p2Rank){
        System.out.println("P1 rank => " + p1Rank);
        System.out.println("P2 wins => " + p2Rank);
    }
}
