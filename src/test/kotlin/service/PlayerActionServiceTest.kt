package service

import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class PlayerActionServiceTest {

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

    /**
     * test swapOneCard method
     */
    @Test
    fun swapOneCardTest(){
        val game = rootService.currentGame
        assertNotNull(game)

        val testRefreshable = TestRefreshable()
        rootService.addRefreshable(testRefreshable)

        game.players.sortBy { it.name }

        val open = game.players[game.currentPlayer].openCards[0]
        val desk = game.deskCards[0]

        testRefreshable.reset()
        rootService.playerActionService.swapOneCard(0,0)

        assertEquals(open, game.deskCards[0])
        assertEquals(desk, game.players[game.currentPlayer].openCards[0])
        assertTrue(testRefreshable.refreshAfterSwapCalled)

        rootService.playerActionService.swapOneCard(0,0)
        assertEquals(1, game.currentPlayer)

    }

    /**
     * test swapThree card method
     */
    @Test
    fun swapThreeCardsTest(){
        val game = rootService.currentGame
        assertNotNull(game)

        game.players.sortBy { it.name }

        val open = game.players[game.currentPlayer].openCards
        val desk = game.deskCards

        val testRefreshable = TestRefreshable()
        rootService.addRefreshable(testRefreshable)

        testRefreshable.reset()
        rootService.playerActionService.swapThreeCards()

        assertEquals(open, game.deskCards)
        assertEquals(desk, game.players[game.currentPlayer].openCards)
        assertTrue(testRefreshable.refreshAfterSwapCalled)

        rootService.playerActionService.swapThreeCards()
        assertEquals(1, game.currentPlayer)

    }

    /**
     * test moveRight method
     */
    @Test
    fun moveRightTest(){
        val game = rootService.currentGame
        assertNotNull(game)

        val testRefreshable = TestRefreshable()
        rootService.addRefreshable(testRefreshable)

        val first = game.deskCards[0]
        val last = game.drawStack.peek()

        testRefreshable.reset()
        rootService.playerActionService.move(false)

        assertEquals(first, game.discardStack.peek())
        assertEquals(last, game.deskCards[2])
        assertTrue(testRefreshable.refreshAfterMoveCalled)

        rootService.playerActionService.move(false)
        assertEquals(1, game.currentPlayer)

    }

    /**
     * test moveLeft method
     */
    @Test
    fun moveLeftTest(){
        val game = rootService.currentGame
        assertNotNull(game)

        val testRefreshable = TestRefreshable()
        rootService.addRefreshable(testRefreshable)

        val first = game.deskCards[2]
        val last = game.drawStack.peek()

        testRefreshable.reset()
        rootService.playerActionService.move(true)

        assertEquals(first, game.discardStack.peek())
        assertEquals(last, game.deskCards[0])
        assertTrue(testRefreshable.refreshAfterMoveCalled)

        rootService.playerActionService.move(true)
        assertEquals(1, game.currentPlayer)

    }

    /**
     * test skip swap and endturn called by skip swap
     */
    @Test
    fun skipSwapTest(){
        val game = rootService.currentGame
        assertNotNull(game)

        val testRefreshable = TestRefreshable()
        rootService.addRefreshable(testRefreshable)

        rootService.playerActionService.skipSwap()
        assertEquals(1, game.players[game.currentPlayer].actionCounter)

        rootService.playerActionService.skipSwap()
        assertEquals(1, game.currentPlayer)

    }
}