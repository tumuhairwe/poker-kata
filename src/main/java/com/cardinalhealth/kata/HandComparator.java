package com.cardinalhealth.kata;

import java.util.Comparator;

public class HandComparator implements Comparator<PokerHand> {

    @Override
    public int compare(PokerHand o1, PokerHand o2) {
        if(o1.getCards().size() != 5 || o2.getCards().size() != 5){
            throw new UnsupportedOperationException("Can't compare 2 hands that are  not exactly 5 cards each");
        }

        return 0;
    }
}
