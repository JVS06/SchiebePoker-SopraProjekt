package gui

import entity.Card
import entity.CardSuit
import entity.CardValue
import service.Refreshable
import service.RootService
import tools.aqua.bgw.components.container.CardStack
import tools.aqua.bgw.components.container.LinearLayout
import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.ComboBox
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.ListView
import tools.aqua.bgw.components.uicomponents.Orientation
import tools.aqua.bgw.components.uicomponents.SelectionMode
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.util.BidirectionalMap
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
class GameScene (private val rootService: RootService) : BoardGameScene(1920, 1080), Refreshable {

    val listView = ListView<String>(
        posX = 30,
        posY = 750,
        width = 650,
        height = 300,
        items = mutableListOf<String>("LOG"),
        font = Font(
            size = 14,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        visual = ColorVisual(
            color = Color(0xD9D9D9)
        ),
        orientation = Orientation.VERTICAL,
        selectionMode = SelectionMode.NONE,
        selectionBackground = ColorVisual(
            Color(0xD9D9D9)
        )
    )

    private val cardMap: BidirectionalMap<Card, CardView> = BidirectionalMap()

    val drawStack = CardStack<CardView>(
        posX = 1170,
        posY = 463,
        width = 100,
        height = 154,
        alignment = Alignment.CENTER,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        )
    )

    val discardStack = CardStack<CardView>(
        posX = 650,
        posY = 463,
        width = 100,
        height = 154,
        alignment = Alignment.CENTER,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        )
    )

    val deskCardView = LinearLayout<CardView>(
        posX = 800,
        posY = 463,
        width = 320,
        height = 154,
        spacing = 10,
        visual = ColorVisual(
            color = Color(0xDC1717)
        ).apply {
            transparency = 0.5
        },
        orientation = Orientation.HORIZONTAL,
        alignment = Alignment.TOP_LEFT
    )

    val openCards1 = LinearLayout<CardView>(
        posX = 800,
        posY = 680,
        width = 320,
        height = 154,
        spacing = 10,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.5
        },
        orientation = Orientation.HORIZONTAL,
        alignment = Alignment.TOP_LEFT
    )

    val hiddenCards1 = LinearLayout<CardView>(
        posX = 855,
        posY = 850,
        width = 210,
        height = 154,
        spacing = 10,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.5
        },
        orientation = Orientation.HORIZONTAL,
        alignment = Alignment.TOP_LEFT
    )

    val openCards2 = LinearLayout<CardView>(
        posX = 1390,
        posY = 462,
        width = 320,
        height = 154,
        spacing = 10,
        visual = ColorVisual(
            color = Color(0xDC1717)
        ).apply {
            transparency = 0.5
        },
        orientation = Orientation.HORIZONTAL,
        alignment = Alignment.TOP_LEFT
    ).apply {
        rotation = -90.0
    }

    val hiddenCards2 = LinearLayout<CardView>(
        posX = 45,
        posY = 462,
        width = 210,
        height = 154,
        spacing = 10,
        visual = ColorVisual(
            color = Color(0xDC1717)
        ).apply {
            transparency = 0.5
        },
        orientation = Orientation.HORIZONTAL,
        alignment = Alignment.TOP_LEFT
    ).apply {
        rotation = -90.0
    }

    val openCards3 = LinearLayout<CardView>(
        posX = 800,
        posY = 246,
        width = 320,
        height = 154,
        spacing = 10,
        visual = ColorVisual(
            color = Color(0xDC1717)
        ).apply {
            transparency = 0.5
        },
        orientation = Orientation.HORIZONTAL,
        alignment = Alignment.TOP_LEFT
    ).apply {
        rotation = 180.0
    }

    val hiddenCards3 = LinearLayout<CardView>(
        posX = 855,
        posY = 76,
        width = 210,
        height = 154,
        spacing = 10,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.5
        },
        orientation = Orientation.HORIZONTAL,
        alignment = Alignment.TOP_LEFT
    ).apply {
        rotation = 180.0
    }

    val openCards4 = LinearLayout<CardView>(
        posX = 155,
        posY = 462,
        width = 320,
        height = 154,
        spacing = 10,
        visual = ColorVisual(
            color = Color(0xDC1717)
        ).apply {
            transparency = 0.5
        },
        orientation = Orientation.HORIZONTAL,
        alignment = Alignment.TOP_LEFT
    ).apply {
        rotation = 90.0
    }

    val hiddenCard4 = LinearLayout<CardView>(
        posX = 1610,
        posY = 462,
        width = 210,
        height = 154,
        spacing = 10,
        visual = ColorVisual(
            color = Color(0xDC1717)
        ).apply {
            transparency = 0.5
        },
        orientation = Orientation.HORIZONTAL,
        alignment = Alignment.TOP_LEFT
    ).apply {
        rotation = 90.0
    }

    val opponentHidden = listOf(hiddenCards2, hiddenCards3, hiddenCard4)

    val opponentOpen = listOf(openCards2,openCards3, openCards4)

    val roundsLeft = Label(
        posX = 1420,
        posY = 930,
        width = 400,
        height = 50,
        text = "Roundsleft: ",
        font = Font(
            size = 20,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xBEB1B1)
        )
    )

    val comboBox = ComboBox<String>(
        posX = 1420,
        posY = 875,
        width = 400,
        height = 50,
        font = Font(
            size = 20,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        items = listOf(
            "Skip An Action",
            "Swap One Card",
            "Swap All Cards",
            "Move Cards to the Right",
            "Move Cards to the Left"),
        disallowUnselect = true,
        visual = ColorVisual(
            color = Color(0xBEB1B1)
        )
    )

    val button = Button(
        posX = 1770,
        posY = 875,
        width = 30,
        height = 50,
        text = ">>",
        font = Font(
            size = 25,
            color = Color(0x000000),
            family = "Arial",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER_RIGHT,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xBEB1B1)
        )
    ).apply {
        onMouseClicked = {
            if (comboBox.selectedItem == "Swap All Cards"){
                rootService.playerActionService.swapThreeCards()
            }
            if (comboBox.selectedItem == "Swap One Card"){
                rootService.playerActionService.swapOneCard(0,0)
            }
            if (comboBox.selectedItem == "Skip An Action"){
                rootService.playerActionService.skipSwap()
            }
            if (comboBox.selectedItem == "Move Cards to the Right"){
                rootService.playerActionService.move(true)
            }
            if (comboBox.selectedItem == "Move Cards to the Left"){
                rootService.playerActionService.move(false)
            }
        }
    }

    init {
       this.background = ColorVisual(
           color = Color(0xDEB7BB)
       )

       addComponents(
           deskCardView,
           openCards1, openCards2, openCards3, openCards4,
           roundsLeft, listView, comboBox,
           hiddenCards1, hiddenCards2,  hiddenCards3,
           drawStack, discardStack, button
           )
   }


    override fun refreshAfterGameStart() {
        val game = rootService.currentGame
        checkNotNull(game) { "Game not started" }

        val cardImageLoader = CardImageLoader()

        hiddenCards1.clear()
        openCards1.clear()
        deskCardView.clear()
        opponentOpen.forEach { views ->
            views.clear()
        }

        opponentHidden.forEach { views ->
            views.clear()
        }

        cardMap.clear()
        CardValue.entries.forEach { value ->
            CardSuit.entries.forEach { suit ->
                cardMap[Card(suit, value)] = CardView(
                    width = 100,
                    height = 154,
                    front = cardImageLoader.frontImageFor(suit, value),
                    back = cardImageLoader.backImage
                )
            }
        }

        drawStack.clear()
        game.drawStack.peekAll().reversed().forEach { card ->
            val cardView = cardMap[card]
            drawStack.add(cardView)
        }

        game.players[game.currentPlayer].openCards.forEach { card ->
            openCards1.add(cardMap[card])
        }

        openCards1.forEach { card ->
            card.showFront()
        }

        game.players[game.currentPlayer].hiddenCards.forEach { card ->
            hiddenCards1.add(cardMap[card])
        }
        hiddenCards1.forEach { card ->
            card.showFront()
        }


        repeat(game.players.size - 1) { index ->
            // Get the player at the current opponent index
            val player = game.players[(game.currentPlayer + 1 + index) % game.players.size]

            // Add all cards of the opponent's hand to the opponentHand
            player.openCards.forEach { card ->
                opponentOpen[index].add(
                    (cardMap[card]).apply {
                        showFront()
                    }
                )
            }
        }

//        repeat(game.players.size - 1) { index ->
//            // Get the player at the current opponent index
//            val player = game.players[(game.currentPlayer + 1 + index) % game.players.size]
//
//            // Add all cards of the opponent's hand to the opponentHand
//            player.hiddenCards.forEach { card ->
//                opponentHidden[index].add(
//                    (cardMap[card])
//                )
//            }
//        }

        game.deskCards.forEach { card ->
            deskCardView.add(cardMap[card])
        }
        deskCardView.forEach { card ->
            card.showFront()
        }

        listView.items.add(game.log[game.log.size - 1])
    }

    override fun refreshAfterSwap() {
        val game = rootService.currentGame
        checkNotNull(game) { "Game not started" }

        openCards1.clear()
        deskCardView.clear()

        game.players[game.currentPlayer].openCards.forEachIndexed { index, card ->
            openCards1.add(cardMap[card], index)
        }

        openCards1.forEach { card ->
            card.showFront()
        }

        game.deskCards.forEach { card ->
            deskCardView.add(cardMap[card])
        }

        deskCardView.forEach { card ->
            card.showFront()
        }

        listView.items.add(game.log[game.log.size - 1])

    }

    override fun refreshAfterEndTurn() {
        val game = rootService.currentGame
        checkNotNull(game) { "Game not started" }

        openCards1.clear()
        deskCardView.clear()
        hiddenCards1.clear()

        opponentOpen.forEach { views ->
            views.clear()
        }

        game.players[game.currentPlayer].hiddenCards.forEach { card ->
            hiddenCards1.add(cardMap[card])
        }
        hiddenCards1.forEach { card ->
            card.showFront()
        }

        game.players[game.currentPlayer].openCards.forEach { card ->
            openCards1.add(cardMap[card])
        }

        openCards1.forEach { card ->
            card.showFront()
        }

        repeat(game.players.size - 1) { index ->
            // Get the player at the current opponent index
            val player = game.players[(game.currentPlayer + 1 + index) % game.players.size]

            // Add all cards of the opponent's hand to the opponentHand
            player.openCards.forEach { card ->
                opponentOpen[index].add(
                    (cardMap[card]).apply {
                        showFront()
                    }
                )
            }
        }

//        repeat(game.players.size - 1) { index ->
//            // Get the player at the current opponent index
//            val player = game.players[(game.currentPlayer + 1 + index) % game.players.size]
//
//            // Add all cards of the opponent's hand to the opponentHand
//            player.hiddenCards.forEach { card ->
//                opponentHidden[index].add(
//                    (cardMap[card])
//                )
//            }
//        }

        game.deskCards.forEach { card ->
            deskCardView.add(cardMap[card])
        }
        deskCardView.forEach { card ->
            card.showFront()
        }

        listView.items.add(game.log[game.log.size - 1])
    }

    override fun refreshAfterMove() {
        val game = rootService.currentGame
        checkNotNull(game) { "Game not started" }

        deskCardView.clear()
        drawStack.pop()

        game.deskCards.forEach { card ->
            deskCardView.add(cardMap[card])
        }

        deskCardView.forEach { card ->
            card.showFront()
        }

        discardStack.add(cardMap[game.discardStack.peek()])
        discardStack.peek().showFront()

        listView.items.add(game.log[game.log.size - 1])
    }



}