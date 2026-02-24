package entity

/**
 * Data class for the single typ of game elements that the game "War" knows: cards.
 *
 * It is characterized by a [CardSuit] and a [CardValue]
 *
 * @property suit the suit of the card
 * @property value the value of the card
 */
data class Card(val suit: CardSuit, val value: CardValue) {}
