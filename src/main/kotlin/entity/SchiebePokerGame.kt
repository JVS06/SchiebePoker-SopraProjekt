package entity

import tools.aqua.bgw.util.Stack

class SchiebePokerGame(val players: MutableList<Player>) {
    var currentPlayer: Int = 0;
    var currentRound: Int = 0;
    var log = mutableListOf<String>("SchiebePokerGame");

    var drawStack: Stack<Card> = Stack();

    var deskCards = mutableListOf<Card>();

    var discardstack: Stack<Card> = Stack();
}
