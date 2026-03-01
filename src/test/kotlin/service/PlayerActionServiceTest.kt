package service

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

private lateinit var rootService: RootService

/**
 * Class for testing the [PlayerActionService.swapOneCard], [PlayerActionService.skipSwap], [PlayerActionService.move] and [PlayerActionService.swapThreeCards]
 * methods of the [PlayerActionService].
 */
class PlayerActionServiceTest {

    /**
     * Sets up a new game before each test
     */
    @BeforeTest
    fun setUp(){
        rootService = RootService()
        rootService.gameService.startNewGame(mutableListOf<String>("Alice"),2)

    }

    /**
     * Test swapping one card from open cards with one card from desk cards and checks if the action counter was incremented
     */
    @Test
    fun `test swapOneCard`(){
        val game = rootService.currentGame
        checkNotNull(game)

        val openCard = game.players.get(game.currentPlayer).openCards.get(0)
        val deskCard = game.deskCards.get(0)

        rootService.playerActionService.swapOneCard(0,0)

        assertEquals(openCard,  game.players.get(game.currentPlayer).openCards.get(0))
        assertEquals(deskCard, game.deskCards.get(0))
        assertEquals(1, game.players.get(game.currentPlayer).actionCounter)
    }

    /**
     * Test swapping all cards from open cards with all cards from desk cards and checks if the action counter was incremented
     */
    @Test
    fun `test swapThreeCards`(){
        val game = rootService.currentGame
        checkNotNull(game)

        val openCards = game.players.get(game.currentPlayer).openCards
        val deskCards = game.deskCards

        rootService.playerActionService.swapOneCard(0,0)

        assertEquals(openCards,  game.players.get(game.currentPlayer).openCards)
        assertEquals(deskCards, game.deskCards)
        assertEquals(1, game.players.get(game.currentPlayer).actionCounter)
    }

    /**
     * Test checking if the action counter was incremented
     */
    @Test
    fun `test skipSwap` () {
        val game = rootService.currentGame
        checkNotNull(game)

        rootService.playerActionService.skipSwap()

        assertEquals(1, game.players.get(game.currentPlayer).actionCounter)
    }

    /**
     * Tests the move function from playerActionService and checks if the action counter was incremented
     */
    @Test
    fun `test move` (){
        val game = rootService.currentGame
        checkNotNull(game)

        var newCardForDiscardStack = game.deskCards.get(0)
        var newCardForDeskCards = game.drawStack.peek()

        rootService.playerActionService.move(false)

        assertEquals(newCardForDiscardStack, game.discardStack.peek())
        assertEquals(newCardForDeskCards, game.deskCards.get(2))

        assertEquals(1, game.players.get(game.currentPlayer).actionCounter)

        game.players.get(game.currentPlayer).actionCounter = 0

        newCardForDiscardStack = game.deskCards.get(2)
        newCardForDeskCards = game.drawStack.peek()

        assertEquals(newCardForDiscardStack, game.discardStack.peek())
        assertEquals(newCardForDeskCards, game.deskCards.get(0))

        assertEquals(1, game.players.get(game.currentPlayer).actionCounter)
    }

}