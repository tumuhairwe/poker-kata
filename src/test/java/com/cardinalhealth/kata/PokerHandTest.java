package com.cardinalhealth.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PokerHandTest {

    @Test
    @DisplayName("valid set of hands should be read correctly")
    public void testFrom(){
        PokerHand p = PokerHand.from("2H 3D 5S 9C KD");
        assertEquals(p.getCards().size(), 5);
    }

    @Test
    @DisplayName("invalid set of hands should fail")
    public void testFromInvalid(){
        assertThrows(RuntimeException.class, () ->{
            PokerHand p = PokerHand.from("1H 3D 5S 9C KD");
        });
    }
}
