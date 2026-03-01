package service

import entity.Player
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

private lateinit var rootService: RootService

/**
 * Class for testing the [GameService.startNewGame], [GameService.endTurn] and [GameService.refillDrawStack]
 * methods of the [GameService].
 */
class GameServiceTest {

    /**
     * Sets up a new game before each test
     */
    @BeforeTest
    fun setUp(){
        rootService = RootService()
        rootService.gameService.startNewGame(mutableListOf<String>("Alice"),2)

    }

    /**
     * Test starting a new Game
     */
    @Test
    fun `test startNewGame, createDrawStack and dealCards`(){
        val game = rootService.currentGame
        checkNotNull(game)
        assertEquals(3, game.roundsLeft)
        assertEquals(mutableListOf<Player>(Player("Alice")), game.players)
        assertEquals(3,game.deskCards.size)
        assertEquals(3, game.players.get(0).openCards.size)
        assertEquals(2, game.players.get(0).hiddenCards.size)
        assertEquals(46, game.drawStack.size)
        assertEquals(0,game.discardStack.size)
        assertEquals(0, game.currentPlayer)
    }

    /**
     * Test ending a turn and ending the game
     */
    @Test
    fun `test endTurn and endgame`(){
        val game = rootService.currentGame
        checkNotNull(game)
        game.roundsLeft = 1
        rootService.gameService.endTurn()

        assertEquals(null, rootService.currentGame)
        assertEquals(0,game.roundsLeft)
        assertEquals(0,game.players.get(game.currentPlayer).actionCounter)
    }

    /**
     * Test refilling the draw stack
     */
    @Test
    fun `test refillDrawStack`(){
        val game = rootService.currentGame
        checkNotNull(game)

        game.discardStack.pushAll(game.drawStack.popAll(game.drawStack.size))

        assertEquals(0,game.drawStack.size)

        rootService.gameService.refillDrawStack()

        assertEquals(0, game.discardStack.size)
    }
}