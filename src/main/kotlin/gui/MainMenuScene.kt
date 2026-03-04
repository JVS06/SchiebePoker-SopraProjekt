package gui

import service.Refreshable
import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.Color
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

class MainMenuScene(private val rootService: RootService) : MenuScene(1920,1080), Refreshable {

    val menu = Label(
        posX = 110,
        posY = 110,
        width = 1700,
        height = 860,
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
            color = Color(0x756062)
        )
    )

    val nameLabel1 = Label(
        posX = 205,
        posY = 145,
        width = 1510,
        height = 85,
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

    val name1: TextField = TextField(
        posX = 210,
        posY = 150,
        width = 1500,
        height = 75,
        text = "",
        prompt = " Enter Name",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.SEMI_BOLD,
            fontStyle = Font.FontStyle.NORMAL
        ),
        visual = ColorVisual(
            color = Color(0xAFA1A1)
        ).apply {
            transparency = 1.0
        }
    ).apply {
        onKeyPressed = {
            startGameButton.isDisabled = this.text.isBlank() || name2.text.isBlank() || round.text.isBlank()
        }
    }


    val nameLabel2 = Label(
        posX = 205,
        posY = 245,
        width = 1510,
        height = 85,
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

    val name2: TextField = TextField(
        posX = 210,
        posY = 250,
        width = 1500,
        height = 75,
        text = "",
        prompt = " Enter Name",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.SEMI_BOLD,
            fontStyle = Font.FontStyle.NORMAL
        ),
        visual = ColorVisual(
            color = Color(0xAFA1A1)
        ).apply {
            transparency = 1.0
        }
    ).apply {
        onKeyPressed = {
            startGameButton.isDisabled = name1.text.isBlank() || this.text.isBlank() || round.text.isBlank()
        }
    }


    val nameLabel3 = Label(
        posX = 205,
        posY = 345,
        width = 1510,
        height = 85,
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
    ).apply {
        isVisible = false
    }
    val name3 = TextField(
        posX = 210,
        posY = 350,
        width = 1500,
        height = 75,
        text = "",
        prompt = " Enter Name",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.SEMI_BOLD,
            fontStyle = Font.FontStyle.NORMAL
        ),
        visual = ColorVisual(
            color = Color(0xAFA1A1)
        ).apply {
            transparency = 1.0
        }
    ).apply {
        isVisible = false
    }

    val nameButton3 = Button(
        posX = 1500,
        posY = 350,
        width = 210,
        height = 75,
        text = "X",
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
        ).apply {
            transparency = 0.0
        }
    ).apply {
        isVisible = false
        onMouseClicked = {
            if (!name4.isVisible) {
                name3.isVisible = false
                this.isVisible = false
                nameLabel3.isVisible = false
            }
        }
    }

    val nameLabel4 = Label(
        posX = 205,
        posY = 445,
        width = 1510,
        height = 85,
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
    ).apply {
        isVisible = false
    }

    val name4 = TextField(
        posX = 210,
        posY = 450,
        width = 1500,
        height = 75,
        text = "",
        prompt = " Enter Name",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.SEMI_BOLD,
            fontStyle = Font.FontStyle.NORMAL
        ),
        visual = ColorVisual(
            color = Color(0xAFA1A1)
        ).apply {
            transparency = 1.0
        }
    ).apply {
        isVisible = false
    }

    val nameButton4 = Button(
        posX = 1500,
        posY = 450,
        width = 210,
        height = 75,
        text = "X",
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
        ).apply {
            transparency = 0.0
        }
    ).apply {
        isVisible = false
        onMouseClicked = {
            name4.isVisible = false
            this.isVisible = false
            nameLabel4.isVisible = false
        }
    }

    val textFieldAddButton = Button(
        posX = 855,
        posY = 550,
        width = 210,
        height = 75,
        text = "+",
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
            color = Color(0xAFA1A1)
        )
    ).apply {
        onMouseClicked = {
            if (!name3.isVisible){
                name3.isVisible = true
                nameLabel3.isVisible = true
                nameButton3.isVisible = true
            }else if (!name4.isVisible){
                name4.isVisible = true
                nameButton4.isVisible = true
                nameLabel4.isVisible = true
            }
        }
    }

    val roundLabel = Label(
        posX = 205,
        posY = 645,
        width = 1510,
        height = 85,
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

    val round: TextField = TextField(
        posX = 210,
        posY = 650,
        width = 1500,
        height = 75,
        text = "",
        prompt = " Enter Number of Rounds",
        font = Font(
            size = 50,
            color = Color(0x000000),
            family = "Arial Black",
            fontWeight = Font.FontWeight.SEMI_BOLD,
            fontStyle = Font.FontStyle.NORMAL
        ),
        visual = ColorVisual(
            color = Color(0xAFA1A1)

        ).apply {
            transparency = 1.0
        }
    ).apply {
        onKeyReleased = {
            if (this.text.toIntOrNull() in 2..7) {
                startGameButton.isDisabled = name1.text.isBlank() || name2.text.isBlank() || this.text.isBlank()
            }else{
                this.text = ""
                this.prompt = "number between 2 and 7"
            }
        }
    }

    val startGameButton = Button(
        posX = 251,
        posY = 800,
        width = 500,
        height = 75,
        text = "START GAME",
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
            color = Color(0xAFA1A1)
        )
    ).apply {
        //this.isDisabled = true
        onMouseClicked = {
            val playerNames = mutableListOf<String>()
            playerNames.add(name1.text.trim())
            playerNames.add(name2.text.trim())
            if (!name3.text.isBlank()){
                playerNames.add(name3.text.trim())
            }
            if (!name4.text.isBlank()){
                playerNames.add(name4.text.trim())
            }
            rootService.gameService.startNewGame(playerNames, round.text.trim().toInt())
        }
    }

    val endGameButton = Button(
        posX = 1170,
        posY = 800,
        width = 500,
        height = 75,
        text = "END",
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
            color = Color(0xAFA1A1)
        ).apply {
            transparency = 1.0
        }
    )

    init {
        this.background = ColorVisual(
            color = Color(0xDEB7BB)
        )
        addComponents(
            menu,
            nameLabel1, name1,
            nameLabel2, name2,
            nameLabel3, name3, nameButton3,
            nameLabel4, name4, nameButton4,
            textFieldAddButton, roundLabel, round,
            startGameButton, endGameButton
        )
    }

}