package service

import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


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
    fun `game should start`(){
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

}