package entity

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SchiebePokerGameTest {
    private val aceOfSpades = Card(CardSuit.SPADES, CardValue.ACE)

    val player = Player("player")

    val game = SchiebePokerGame(mutableListOf(player),3)

    @BeforeTest
    fun setUp(){
        game.currentPlayer = 1
        game.deskCards.add(aceOfSpades)
        game.drawStack.push(aceOfSpades)
        game.discardStack.push(aceOfSpades)
    }

    /**
     * Test, if the list of players is correct
     */
    @Test
    fun testPlayers(){
        assertEquals(mutableListOf(player), game.players)
    }

    /**
     * Test, if the current Round is correct
     */
    @Test
    fun testCurrentRound(){
        assertEquals(3,game.roundsLeft)
    }

    /**
     * Test, if the current player is correct
     */
    @Test
    fun testCurrentPlayer(){
        assertEquals(1,game.currentPlayer)
    }

    /**
     * Test, if the desk cards are correct
     */
    @Test
    fun testDeskCards(){
        assertEquals(aceOfSpades, game.deskCards.first())
    }

    /**
     * Test, if the draw stack is correct
     */
    @Test
    fun testDrawStack(){
        assertEquals(aceOfSpades, game.drawStack.peek())
    }

    /**
     * Test, if the discard stack is correct
     */
    @Test
    fun testDiscardStack(){
        assertEquals(aceOfSpades, game.discardStack.peek())
    }
}