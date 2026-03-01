package service

import entity.Player
import entity.SchiebePokerGame
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

private lateinit var rootService: RootService

class GameServiceTest {

    @BeforeTest
    fun setUp(){
        rootService = RootService()
        rootService.gameService.startNewGame(mutableListOf<String>("Alice"),2)

    }

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



}