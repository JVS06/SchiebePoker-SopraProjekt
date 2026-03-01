package entity

/**
 * Entity to represent a player in the game "Schiebe-Poker Game". Besides having a [name] and the information
 * whether an action is executed this round ([actionCounter]), the player just consists of two
 * lists of cards: [hiddenCards], [openCards]
 *
 * @property name name of the player
 * @property hiddenCards list of cards that will be hidden for other players
 * @property openCards list of cards that will be open and be used for the swap
 * @property actionCounter counts the actions, which were executed by the player
 */
class Player(val name: String) {
    var actionCounter: Int = 0

    var hiddenCards: MutableList<Card> = mutableListOf()

    var openCards: MutableList<Card> = mutableListOf()
}