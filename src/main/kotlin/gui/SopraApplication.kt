package gui

import entity.Player
import service.Refreshable
import tools.aqua.bgw.core.BoardGameApplication
import service.RootService

/**
 * Represents the main application for the SoPra board game.
 * The application initializes the [RootService] and displays the scenes.
 */
class SopraApplication : BoardGameApplication("Schiebe Poker Game"), Refreshable {

    /**
     * The root service instance. This is used to call service methods and access the entity layer.
     */
    val rootService: RootService = RootService()

    /**
     * The main game scene displayed in the application.
     */
    private val mainMenuScene = MainMenuScene(rootService).apply {
        endGameButton.onMouseClicked = {
            exit()
        }
    }
    private val gameScene = GameScene(rootService)

    val hotSeatScene = HotSeatScene(rootService).apply {
        button.onMouseClicked = {
            hideMenuScene(0)
        }
    }

    val resultMenuScene = ResultMenuScene(rootService).apply {
        button.onMouseClicked = {
            hideMenuScene(1)
            showMenuScene(mainMenuScene)
            gameScene.listView.items.clear()
        }
        button1.onMouseClicked = {
            exit()
        }
    }


    /**
     * Initializes the application by displaying the [HelloScene].
     */
    init {

        rootService.addRefreshables(
            this,
            gameScene,
            mainMenuScene
        )

        this.showGameScene(gameScene)

        this.showMenuScene(mainMenuScene,0)

    }


    override fun refreshAfterGameStart() {
        val game = rootService.currentGame
        checkNotNull(game) { "Game not started" }

        gameScene.roundsLeft.text = "Remaining Rounds: ${game.roundsLeft}"

        this.hideMenuScene(0)
        hotSeatScene.label.text = game.players[game.currentPlayer].name + "'s Turn"
        this.showMenuScene(hotSeatScene)
    }

    override fun refreshAfterEndTurn() {
        val game = rootService.currentGame
        checkNotNull(game) { "Game not started" }

        hotSeatScene.label.text = game.players[game.currentPlayer].name + "'s Turn"
        gameScene.roundsLeft.text = "Remaining Rounds: ${game.roundsLeft}"
        this.showMenuScene(hotSeatScene,100)

    }

    override fun refreshAfterEndGame(ranking: MutableList<Pair<Player, Int>>) {
        this.showMenuScene(resultMenuScene)
    }
}

