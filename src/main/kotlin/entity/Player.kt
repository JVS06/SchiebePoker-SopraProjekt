package entity

class Player(val name: String) {
    //aC = ActionCounter, zählt die Aktionen
    val aC = 0;

    var hiddenCards: MutableList<Card> = mutableListOf();

    var openCards: MutableList<Card> = mutableListOf();
}