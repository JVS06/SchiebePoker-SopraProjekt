package entity

import kotlin.test.Test
import kotlin.test.assertEquals

class CardTest {

    private val aceOfSpades = Card(CardSuit.SPADES, CardValue.ACE);

    /**
     * Test, if the Card is initialized right with one Value and one Suit
     */
    @Test
    fun testCardRight(){
        assertEquals(CardSuit.SPADES,aceOfSpades.suit);
        assertEquals(CardValue.ACE, aceOfSpades.value);
    }



}