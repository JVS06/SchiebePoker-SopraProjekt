package gui

import entity.Player
import service.Refreshable
import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.SelectionMode
import tools.aqua.bgw.components.uicomponents.TableView
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

class ResultMenuScene (private val rootService: RootService) : MenuScene(1920,1080), Refreshable {

    val label = Label(
        posX = 0,
        posY = 0,
        width = 1920,
        height = 150,
        text = "LEADERBOARD",
        font = Font(
            size = 100,
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

    val button = Button(
        posX = 480,
        posY = 940,
        width = 250,
        height = 150,
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
            color = Color(0xFFFFFF)
        )
    )

    val button1 = Button(
        posX = 1190,
        posY = 940,
        width = 250,
        height = 150,
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
            color = Color(0xFFFFFF)
        )
    )

    val tableView = TableView<String>(
        posX = 240,
        posY = 185,
        width = 1440,
        height = 730,
        items = listOf(),
        visual = ColorVisual(
            color = Color(0xFF0000)
        ).apply {
            transparency = 0.5
        },
        selectionMode = SelectionMode.NONE,
        selectionBackground = ColorVisual(
            Color(0x0000FF)
        )
    )

//    val tableColumn = TableColumn<String>(
//        title = "Names",
//        width = 360,
//        font = Font(
//            size = 100,
//            color = Color(0x000000),
//            family = "Arial Black",
//            fontWeight = Font.FontWeight.NORMAL,
//            fontStyle = Font.FontStyle.NORMAL
//        ),
//        formatFunction = String()
//    )


    init {
        addComponents(tableView, label, button1, button)
    }

    override fun refreshAfterEndGame(ranking: MutableList<Pair<Player, Int>>) {
        ranking.forEach { pair ->
            tableView.items.add("${pair.first.name} ${pair.second}")
        }
    }
}