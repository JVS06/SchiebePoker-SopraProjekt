package gui

import service.Refreshable
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

/**
 * shows the results
 */
class ResultMenuScene: MenuScene(1920,1080), Refreshable {

    val backgroundLabel = Label(
        posX = 200,
        posY = 5,
        width = 1520,
        height = 1070,
        text = "",
        font = Font(
            size = 14,
            color = Color(0x000000),
            family = "Arial",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xDEB7BB)
        )
    )

    val rankingLabel = Label(
        posX = 238,
        posY = 20,
        width = 1440,
        height = 100,
        text = "Player Ranking",
        font = Font(
            size = 70,
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

    val list = Label(
        posX = 238,
        posY = 150,
        width = 1440,
        height = 800,
        text = "",
        font = Font(
            size = 70,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0x756062)
        )
    )

    val restart = Button(
        posX = 450,
        posY = 960,
        width = 250,
        height = 100,
        text = "Restart",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial BLACK",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xBBE2EC)
        )
    )

    val quit = Button(
        posX = 1190,
        posY = 960,
        width = 250,
        height = 100,
        text = "Quit",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial BLACK",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xBBE2EC)
        )
    )

    val playerLabel = Label(
        posX = 240,
        posY = 150,
        width = 660,
        height = 160,
        text = "Player",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER_LEFT,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player1 = Label(
        posX = 240,
        posY = 310,
        width = 660,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER_LEFT,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player2 = Label(
        posX = 240,
        posY = 470,
        width = 660,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER_LEFT,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player3 = Label(
        posX = 240,
        posY = 630,
        width = 660,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER_LEFT,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player4 = Label(
        posX = 240,
        posY = 790,
        width = 660,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER_LEFT,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val handLabel = Label(
        posX = 900,
        posY = 150,
        width = 540,
        height = 160,
        text = "Hand",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player1Hand = Label(
        posX = 900,
        posY = 310,
        width = 540,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player2Hand = Label(
        posX = 900,
        posY = 470,
        width = 540,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player3Hand = Label(
        posX = 900,
        posY = 630,
        width = 540,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player4Hand = Label(
        posX = 900,
        posY = 790,
        width = 540,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val pointsLabel = Label(
        posX = 1478,
        posY = 150,
        width = 200,
        height = 160,
        text = "Points",
        font = Font(
            size = 53,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player1Points = Label(
        posX = 1478,
        posY = 310,
        width = 200,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player2Points = Label(
        posX = 1478,
        posY = 470,
        width = 200,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val player3Points = Label(
        posX = 1478,
        posY = 630,
        width = 200,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xBEB1B1)
        ).apply {
            transparency = 0.0
        }
    )

    val player4Points = Label(
        posX = 1478,
        posY = 790,
        width = 200,
        height = 160,
        text = "-",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xFFFFFF)
        ).apply {
            transparency = 0.0
        }
    )

    val playerNames = listOf(player1, player2, player3, player4)

    val playerPoints = listOf(player1Points, player2Points, player3Points, player4Points)

    val playerHands = listOf(player1Hand, player2Hand, player3Hand, player4Hand)


    init {
        this.background = ColorVisual(
            color = Color(0xDEB7BB)
        )

        addComponents(
            backgroundLabel, list, rankingLabel, restart, quit,
            pointsLabel, handLabel, playerLabel,
            player1, player2, player3, player4,
            player1Points, player2Points, player3Points, player4Points,
            player1Hand, player2Hand, player3Hand, player4Hand,
        )
    }

}
