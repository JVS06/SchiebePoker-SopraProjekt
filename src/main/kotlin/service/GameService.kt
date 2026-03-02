package service

import entity.Card
import entity.CardSuit
import entity.CardValue
import entity.Player
import entity.SchiebePokerGame

/**
 * Service layer class that provides the logic for actions not directly
 * related to a single player.
 *
 * @param rootService The [RootService] instance to access the other service methods and entity layer
 */
class GameService(private val rootService: RootService): AbstractRefreshingService(){

    /**
     * Starts a new game (overwriting a currently active one, if it exists)
     *
     * Adds all names to player and shuffles the list to have a random player to go first
     *
     * @param playerNames name of all players
     * @param roundsLeft showing, how many rounds there will be
     *
     * @throws IllegalStateException if no game has started yet
     */
    public fun startNewGame(playerNames: MutableList<String>, roundsLeft: Int){
        val players: MutableList<Player> = mutableListOf()
        for (name in playerNames){
            players.add(Player(name))
        }
        players.shuffle()
        val game: SchiebePokerGame = SchiebePokerGame(players,roundsLeft)

        rootService.currentGame = game
        createDrawStack()
        dealCards()
        writeLog("New Game started")

        onAllRefreshables {
            refreshAfterGameStart()
        }
    }


    /**
     * Deals 3 open and 2 hidden cards to each player and 3 cards on the desk
     *
     * @throws IllegalStateException if no game has started yet
     */
    private fun dealCards(){
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }
        repeat(3){
            game.deskCards.add(game.drawStack.pop())
        }
        for (player in game.players){
            repeat(2) {
                player.hiddenCards.add(game.drawStack.pop())
            }
            repeat(3) {
                player.openCards.add(game.drawStack.pop())
            }
        }
    }

    /**
     * Creates a new DrawStack
     *
     *@throws IllegalStateException if no game has started yet
     */
    private fun createDrawStack(){
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }
         // Iterate over all possible card values of a deck
        for (value in CardValue.entries) {
            // For each card value, iterate over all possible suits and add them to the draw stack
            for (suit in CardSuit.entries) {
                // Add the card to the draw stack
                game.drawStack.push(Card(suit, value))
            }
        }
        game.drawStack.shuffle()
        
        onAllRefreshables { 
            refreshAfterDrawStack()
        }
    }

    /**
     * Refills the draw stack with the cards in the discard pile and shuffles them
     *
     * @throws IllegalStateException if no game has started yet
     */
    public fun refillDrawStack(){
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }
        game.drawStack = game.discardStack
        game.drawStack.shuffle()
        
        onAllRefreshables { 
            refreshAfterDrawStack()
        }
    }

    /**
     * Saves all action by writing it to a list
     *
     * @param lastAction description of the last action
     *
     * @throws IllegalStateException if no game has started yet
     */
    public fun writeLog(lastAction: String){
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }
        game.log.add(lastAction)
    }

    /**
     * Checks if there are any round left and
     * checks if a round is over to reset currentPlayer or to increment currentPlayer
     *
     *@throws IllegalStateException if no game has started yet
     */
    public fun endTurn(){
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }

        game.players.get(game.currentPlayer).actionCounter=0

        if (game.currentPlayer == game.players.size-1){
            game.roundsLeft--
            game.currentPlayer = 0
        }else{
            game.currentPlayer++
        }

        if (game.roundsLeft == 0){
            endGame()
        }else{
            onAllRefreshables {
                refreshAfterEndTurn()
            }
        }
    }

    /**
     * Ends the game and prints the winner(s)
     *
     * @throws IllegalStateException if no game has started yet
     */
    private fun endGame(){
        writeLog("Game Ended")
        
        val ranking = selectWinner()
        if (ranking.count { it.second == ranking.get(0).second } > 1){
            val winners = mutableListOf<String>()
            for (player in ranking){
                if (player.second == ranking.get(0).second){
                    winners.add(player.first.name)
                }
            }
            writeLog("The winners are ${winners.toString()}")
        }else{
            writeLog("The winner is " + ranking.first().first.name)
        }
        
        onAllRefreshables { 
            refreshAfterEndGame()
        }
        rootService.currentGame = null
    }

    /**
     * Returns a list sorted by how many points each player got
     *
     * @throws IllegalStateException if no game has started yet
     */
    private fun selectWinner(): MutableList<Pair<Player, Int>>{
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }
        val ranking = mutableListOf<Pair<Player, Int>>()
        for (player in game.players){
            ranking.add(Pair(player,assertCardCombination(player.openCards,player.hiddenCards)))
        }
        ranking.sortByDescending { it.second }
        return ranking
    }

    /**
     * Asserts Card Combination for the players [openCards] and [hiddenCards]
     * by sorting the card suits and card values
     *
     * @param openCards the players three open cards
     * @param hiddenCards the players two hidden cards
     *
     * @throws IllegalStateException if no game has started yet
     */
    private fun assertCardCombination(openCards: MutableList<Card>, hiddenCards: MutableList<Card>): Int {
        val cards: MutableList<Card> = (openCards + hiddenCards) as MutableList<Card>
        cards.sortBy { it.cardValue }
        val values = IntArray(13) {0}
        val suits = IntArray(4) {0}

        for (card in cards){
            when (card.cardSuit){
                CardSuit.CLUBS -> suits[0] +=1
                CardSuit.SPADES -> suits[1] +=1
                CardSuit.HEARTS -> suits[2] += 1
                CardSuit.DIAMONDS -> suits[3] +=1
            }
        }
        for (card in cards){
            when (card.cardValue){
                CardValue.TWO -> values[0] += 1
                CardValue.THREE -> values[1] += 1
                CardValue.FOUR -> values[2] += 1
                CardValue.FIVE -> values[3] += 1
                CardValue.SIX -> values[4] += 1
                CardValue.SEVEN -> values[5] += 1
                CardValue.EIGHT -> values[6] += 1
                CardValue.NINE -> values[7] += 1
                CardValue.TEN -> values[8] += 1
                CardValue.JACK -> values[9] += 1
                CardValue.QUEEN -> values[10] += 1
                CardValue.KING -> values[11] += 1
                CardValue.ACE -> values[12] += 1
            }
        }

        if (suits.any { it == 5 }){
            if (cards.get(0).cardValue == CardValue.TEN){
                return 10
            }else if (cards.get(4).cardValue.ordinal == cards.get(0).cardValue.ordinal+4){
                return 9
            }else{
                return 6
            }
        }
        if (values.any { it >= 3 }){
            if (values.any { it == 4 }){
                return 7
            }else if (values.any { it == 3 }){
                if (values.any { it == 2 }){
                    return 6
                }else {
                    return 4
                }
            }
        }
        if (cards.get(1).cardValue.ordinal == cards.get(0).cardValue.ordinal+1 &&
            cards.get(2).cardValue.ordinal == cards.get(1).cardValue.ordinal+1){
            if (cards.get(3).cardValue.ordinal == cards.get(2).cardValue.ordinal+1 &&
                cards.get(4).cardValue.ordinal == cards.get(3).cardValue.ordinal+1 ) {
                return 5
            }
        }

        if (values.any { it == 2 }){
            if(values.count { it == 2 } == 2){
                return 3
            }else{
                return 2
            }
        }
        return 1
    }
}