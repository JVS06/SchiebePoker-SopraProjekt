package entity

import tools.aqua.bgw.util.Stack

/**
 *
 * Entity class that represents a game state of "Schiebe-Poker". This class has all the informations about
 * the current Player, current Round, the log, the Cards on the desk, the Stack to put a card on the table and
 * the discard pile.
 *
 * @property players The list of Players
 * @param currentRound The Int showing how many Round are left
 * @param currentPlayer The Index showing the Player, whose turn it is
 * @param log The list of Strings, showing the Log for other player, noting, what the actions the players did
 * @param drawStack The Stack of Cards to put a Card on the table, for the move action
 * @param deskCards The List of the 3 Cards which are on the desk, this cards are used for the actions
 * @param discardStack The Stack of Cards, to put the card, which was removed by the action move.
 */

class SchiebePokerGame(val players: MutableList<Player>, var currentRound: Int) {
    var currentPlayer: Int = 0;

    var log = mutableListOf<String>("SchiebePokerGame");

    var drawStack: Stack<Card> = Stack();

    var deskCards: ArrayDeque<Card> = ArrayDeque(3);

    var discardStack: Stack<Card> = Stack();

}