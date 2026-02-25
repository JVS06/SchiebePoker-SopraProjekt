package entity

/**
 * Data class for the single typ of game elements that the game "SchiebePokerGame" knows: cards.
 *
 * It is characterized by a [CardSuit] and a [CardValue]
 *
 * @property cardSuit the suit of the card
 * @property cardValue the value of the card
 */
data class Card(val cardSuit: CardSuit, val cardValue: CardValue)
