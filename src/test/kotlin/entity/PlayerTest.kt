package entity

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerTest {

    private val aceOfSpades = Card(CardSuit.SPADES, CardValue.ACE);

    val player = Player("player");
    @BeforeTest
    fun setUp(){
        player.openCards = mutableListOf<Card>(aceOfSpades);
        player.hiddenCards = mutableListOf<Card>(aceOfSpades);
    }



    /**
     * Test, if the Card is initialized right with one Value and one Suit
     */
    @Test
    fun testPlayerName(){
        assertEquals("player",player.name);
    }

    @Test
    fun testOpenCards(){
        assertEquals(player.openCards, mutableListOf(aceOfSpades));
    }

    @Test
    fun testHiddenCards(){
        assertEquals(player.hiddenCards,mutableListOf(aceOfSpades));
    }
}