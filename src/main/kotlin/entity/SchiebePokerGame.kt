package entity

import tools.aqua.bgw.net.common.notification.PlayerLeftNotification
import tools.aqua.bgw.util.Stack

/**
 *
 * Entity class that represents a game state of "Schiebe-Poker". This class has all the information about
 * the [currentPlayer], [roundsLeft], [log], [deskCards], [drawStack] and [discardStack]-
 *
 * @param players list of Players
 * @param roundsLeft showing how many Round are left
 * @param currentPlayer showing the Index of the Player, whose turn it is
 * @param log  list of Strings, showing the Log for other player, noting, what the actions the players did
 * @param drawStack  stack of cards to put a card on the table, for the move action
 * @param deskCards list of the 3 cards which are on the desk, these cards are used for the actions
 * @param discardStack stack of cards, to put the card, which was removed by the action move
 */

class SchiebePokerGame(val players: MutableList<Player>, var roundsLeft: Int) {
    var currentPlayer: Int = 0

    var log = mutableListOf("SchiebePokerGame")

    var drawStack: Stack<Card> = Stack()

    var deskCards: ArrayDeque<Card> = ArrayDeque(3)

    var discardStack: Stack<Card> = Stack()

}