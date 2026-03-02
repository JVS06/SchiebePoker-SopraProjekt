package service

import entity.Card
import entity.CardSuit
import entity.CardValue
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull


class GameServiceTest {

    /**
     * This service is initialized in the [setUp] function hence it is a late-initialized property.
     */
    private lateinit var rootService: RootService

    /**
     * Initialize service to set up the test environment. This function is executed before every test.
     */
    @BeforeTest
    fun setUp() {
        rootService = RootService()
        rootService.gameService.startNewGame(mutableListOf("Alice","Bob"),2)
    }

    @Test
    fun `test startGame, dealCards, createDrawStack`(){
        val game = rootService.currentGame
        assertNotNull(game)

        game.players.sortBy { it.name }
        assertEquals(2, game.players.size)
        assertEquals("Alice",game.players[0].name)
        assertEquals("Bob",game.players[1].name)

        assertEquals(39, game.drawStack.size)

        assertEquals(3,game.deskCards.size)

        assertEquals(3,game.players[0].openCards.size)
        assertEquals(3, game.players[1].openCards.size)

        assertEquals(2,game.players[0].hiddenCards.size)
        assertEquals(2,game.players[1].hiddenCards.size)
    }

    @Test
    fun refillDrawStackTest(){
        val game = rootService.currentGame
        assertNotNull(game)

        game.discardStack.pushAll(game.drawStack.popAll(game.drawStack.size))
        assertTrue(game.drawStack.isEmpty())
        assertEquals(false, game.discardStack.isEmpty())

        rootService.gameService.refillDrawStack()

        assertTrue(game.discardStack.isEmpty())
        assertFalse(game.drawStack.isEmpty())
    }

    @Test
    fun testEndGame(){
        val game = rootService.currentGame
        assertNotNull(game)
        game.players.sortBy { it.name }

        game.roundsLeft--
        game.currentPlayer = 1

        rootService.gameService.endTurn()

        assertNull(rootService.currentGame)
    }

    @Test
    fun `testEndTurn to next player`(){
        val game = rootService.currentGame
        assertNotNull(game)
        game.players.sortBy { it.name }
        game.players[0].actionCounter++

        rootService.gameService.endTurn()

        assertEquals(1,game.currentPlayer)
        assertEquals(0, game.players[0].actionCounter)
    }

    @Test
    fun `testEndTurn to next round`(){
        val game = rootService.currentGame
        assertNotNull(game)
        game.players.sortBy { it.name }
        game.currentPlayer ++

        rootService.gameService.endTurn()

        assertEquals(1,game.roundsLeft)
        assertEquals(0, game.currentPlayer)
    }

    @Test
    fun selectWinnerTest(){
        val game = rootService.currentGame
        assertNotNull(game)
        game.players.sortBy { it.name }

        val tenClub = Card(CardSuit.CLUBS, CardValue.TEN)
        val jackClub = Card(CardSuit.CLUBS, CardValue.JACK)
        val queenClub = Card(CardSuit.CLUBS, CardValue.QUEEN)
        val kingClub = Card(CardSuit.CLUBS, CardValue.KING)
        val aceClub = Card(CardSuit.CLUBS, CardValue.ACE)

        game.players[0].openCards = mutableListOf(tenClub, jackClub, aceClub)
        game.players[0].hiddenCards = mutableListOf(kingClub, queenClub)

        val ranking = rootService.gameService.selectWinner()

        assertEquals("Alice", ranking.get(0).first.name)
        assertEquals(10, ranking.get(0).second)
    }

    @Test
    fun assertCardCombinationTest(){
        var tenClub = Card(CardSuit.CLUBS, CardValue.TEN)
        var jackClub = Card(CardSuit.CLUBS, CardValue.JACK)
        var queenClub = Card(CardSuit.CLUBS, CardValue.QUEEN)
        var kingClub = Card(CardSuit.CLUBS, CardValue.KING)
        var aceClub = Card(CardSuit.CLUBS, CardValue.ACE)

        var open = mutableListOf(tenClub, jackClub, queenClub)
        var hidden = mutableListOf(kingClub, aceClub)

        var points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(10,points)

        tenClub = Card(CardSuit.CLUBS, CardValue.TEN)
        jackClub = Card(CardSuit.CLUBS, CardValue.JACK)
        queenClub = Card(CardSuit.CLUBS, CardValue.QUEEN)
        kingClub = Card(CardSuit.CLUBS, CardValue.KING)
        aceClub = Card(CardSuit.CLUBS, CardValue.NINE)

        open = mutableListOf(tenClub, jackClub, queenClub)
        hidden = mutableListOf(kingClub, aceClub)

        points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(9, points)

        tenClub = Card(CardSuit.CLUBS, CardValue.EIGHT)
        jackClub = Card(CardSuit.CLUBS, CardValue.JACK)
        queenClub = Card(CardSuit.CLUBS, CardValue.QUEEN)
        kingClub = Card(CardSuit.CLUBS, CardValue.KING)
        aceClub = Card(CardSuit.CLUBS, CardValue.NINE)

        open = mutableListOf(tenClub, jackClub, queenClub)
        hidden = mutableListOf(kingClub, aceClub)

        points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(6, points)

        tenClub = Card(CardSuit.CLUBS, CardValue.ACE)
        jackClub = Card(CardSuit.DIAMONDS, CardValue.ACE)
        queenClub = Card(CardSuit.HEARTS, CardValue.ACE)
        kingClub = Card(CardSuit.SPADES, CardValue.ACE)
        aceClub = Card(CardSuit.CLUBS, CardValue.NINE)

        open = mutableListOf(tenClub, jackClub, queenClub)
        hidden = mutableListOf(kingClub, aceClub)

        points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(8, points)

        tenClub = Card(CardSuit.CLUBS, CardValue.ACE)
        jackClub = Card(CardSuit.DIAMONDS, CardValue.ACE)
        queenClub = Card(CardSuit.HEARTS, CardValue.NINE)
        kingClub = Card(CardSuit.SPADES, CardValue.NINE)
        aceClub = Card(CardSuit.CLUBS, CardValue.ACE)

        open = mutableListOf(tenClub, jackClub, queenClub)
        hidden = mutableListOf(kingClub, aceClub)

        points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(7, points)

        tenClub = Card(CardSuit.CLUBS, CardValue.ACE)
        jackClub = Card(CardSuit.DIAMONDS, CardValue.ACE)
        queenClub = Card(CardSuit.HEARTS, CardValue.NINE)
        kingClub = Card(CardSuit.SPADES, CardValue.EIGHT)
        aceClub = Card(CardSuit.CLUBS, CardValue.ACE)

        open = mutableListOf(tenClub, jackClub, queenClub)
        hidden = mutableListOf(kingClub, aceClub)

        points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(4, points)

        tenClub = Card(CardSuit.CLUBS, CardValue.TWO)
        jackClub = Card(CardSuit.DIAMONDS, CardValue.THREE)
        queenClub = Card(CardSuit.HEARTS, CardValue.FOUR)
        kingClub = Card(CardSuit.SPADES, CardValue.FIVE)
        aceClub = Card(CardSuit.CLUBS, CardValue.SIX)

        open = mutableListOf(tenClub, jackClub, queenClub)
        hidden = mutableListOf(kingClub, aceClub)

        points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(5, points)

        tenClub = Card(CardSuit.CLUBS, CardValue.ACE)
        jackClub = Card(CardSuit.DIAMONDS, CardValue.EIGHT)
        queenClub = Card(CardSuit.HEARTS, CardValue.NINE)
        kingClub = Card(CardSuit.SPADES, CardValue.NINE)
        aceClub = Card(CardSuit.CLUBS, CardValue.ACE)

        open = mutableListOf(tenClub, jackClub, queenClub)
        hidden = mutableListOf(kingClub, aceClub)

        points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(3, points)

        tenClub = Card(CardSuit.CLUBS, CardValue.ACE)
        jackClub = Card(CardSuit.DIAMONDS, CardValue.EIGHT)
        queenClub = Card(CardSuit.HEARTS, CardValue.NINE)
        kingClub = Card(CardSuit.SPADES, CardValue.TEN)
        aceClub = Card(CardSuit.CLUBS, CardValue.ACE)

        open = mutableListOf(tenClub, jackClub, queenClub)
        hidden = mutableListOf(kingClub, aceClub)

        points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(2, points)

        tenClub = Card(CardSuit.CLUBS, CardValue.TWO)
        jackClub = Card(CardSuit.DIAMONDS, CardValue.TEN)
        queenClub = Card(CardSuit.HEARTS, CardValue.EIGHT)
        kingClub = Card(CardSuit.SPADES, CardValue.NINE)
        aceClub = Card(CardSuit.CLUBS, CardValue.ACE)

        open = mutableListOf(tenClub, jackClub, queenClub)
        hidden = mutableListOf(kingClub, aceClub)

        points = rootService.gameService.assertCardCombination(open, hidden)

        assertEquals(1, points)
    }

}