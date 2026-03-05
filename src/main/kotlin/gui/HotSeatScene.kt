package gui

import service.Refreshable
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

class HotSeatScene: MenuScene(1920,1080), Refreshable {

    val label = Label(
        posX = 0,
        posY = 400,
        width = 1920,
        height = 150,
        text = "Turn",
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

    val label1 = Label(
        posX = 455,
        posY = 645,
        width = 1010,
        height = 160,
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
            color = Color(0x000000)
        )
    )

    val button = Button(
        posX = 460,
        posY = 650,
        width = 1000,
        height = 150,
        text = "Show my cards",
        font = Font(
            size = 100,
            color = Color(0x000000),
            family = "Arial",
            fontWeight = Font.FontWeight.NORMAL,
            fontStyle = Font.FontStyle.NORMAL
        ),
        alignment = Alignment.CENTER,
        isWrapText = false,
        visual = ColorVisual(
            color = Color(0xC4B2B3)
        )
    )

    init {

        addComponents(label1, label, button)
    }


}