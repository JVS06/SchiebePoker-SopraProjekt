package entity

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SchiebePokerGameTest {
    private val aceOfSpades = Card(CardSuit.SPADES, CardValue.ACE);

    val player = Player("player");

    val game = SchiebePokerGame(mutableListOf(player),3);

    @BeforeTest
    fun setUp(){
        game.currentPlayer = 1;
        game.deskCards.add(aceOfSpades);
        game.drawStack.push(aceOfSpades);
        game.discardStack.push(aceOfSpades);
    }

    @Test
    fun testPlayers(){
        assertEquals(mutableListOf<Player>(player), game.players);
    }

    @Test
    fun testCurrentRound(){
        assertEquals(3,game.currentRound);
    }

    @Test
    fun testCurrentPlayer(){
        assertEquals(1,game.currentPlayer);
    }

    @Test
    fun testDeskCards(){
        assertEquals(aceOfSpades, game.deskCards.first());
    }

    @Test
    fun testDrawStack(){
        assertEquals(aceOfSpades, game.drawStack.peek())
    }

    @Test
    fun testDiscardStack(){
        assertEquals(aceOfSpades, game.discardStack.peek())
    }



}