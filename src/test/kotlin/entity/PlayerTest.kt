package entity

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerTest {

    private val aceOfSpades = Card(CardSuit.SPADES, CardValue.ACE)

    val player = Player("player")

    @BeforeTest
    fun setUp(){
        player.openCards = mutableListOf(aceOfSpades)
        player.hiddenCards = mutableListOf(aceOfSpades)
    }



    /**
     * Test, if the players Name is Right
     */
    @Test
    fun testPlayerName(){
        assertEquals("player",player.name)
    }

    /**
     * Test, if the open cards are correct
     */
    @Test
    fun testOpenCards(){
        assertEquals(player.openCards, mutableListOf(aceOfSpades))
    }

    /**
     * Test, if the hidden cards are correct
     */
    @Test
    fun testHiddenCards(){
        assertEquals(player.hiddenCards,mutableListOf(aceOfSpades))
    }
}