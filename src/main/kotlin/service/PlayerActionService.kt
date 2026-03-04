package service


/**
 * Service layer class that provides the logic for the five possible actions a player
 * can take in Schiebe-Poker-Game: swap one card, swap three cards, skip one action,
 * shift the desk cards to the right and shift the desk cards to the left
 *
 * @param rootService The [RootService] instance to access the other service methods and entity layer
 */
class PlayerActionService(private val rootService: RootService): AbstractRefreshingService() {

    /**
     * Swaps one card from the players open cards with one card from the desk
     *
     * Increments the current players action count by 1
     *
     * @param openCardPosition index of the card, which has to be swapped from the open cards
     * @param deskPosition index of the card, which has to be swapped from the desk cards
     *
     * @throws IllegalStateException if no game has started yet
     */
    public fun swapOneCard(openCardPosition: Int, deskPosition: Int){
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }
        val card = game.players.get(game.currentPlayer).openCards.get(openCardPosition)
        game.players.get(game.currentPlayer).openCards.set(openCardPosition,game.deskCards.get(deskPosition))
        game.deskCards.set(deskPosition, card)

        rootService.gameService.writeLog("${game.players[game.currentPlayer].name} swaped " +
                "${game.players[game.currentPlayer].openCards.get(openCardPosition).cardSuit}" +
                "${game.players[game.currentPlayer].openCards.get(openCardPosition).cardValue} with " +
                "${game.deskCards.get(deskPosition).cardSuit}${game.deskCards.get(deskPosition).cardValue}")

        game.players.get(game.currentPlayer).actionCounter++



        onAllRefreshables {
            refreshAfterSwap()
        }

        if (game.players.get(game.currentPlayer).actionCounter==2){
            rootService.gameService.endTurn()
        }
    }

    /**
     * Swaps all cards from the players open cards with all cards from the desk
     *
     * Increments the current players action count by 1
     *
     * @throws IllegalStateException if no game has started yet
     */
    public fun swapThreeCards(){
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }
        val cardList = game.deskCards
        game.deskCards = ArrayDeque(game.players.get(game.currentPlayer).openCards)
        game.players.get(game.currentPlayer).openCards = cardList

        game.players.get(game.currentPlayer).actionCounter++

        rootService.gameService.writeLog("${game.players[game.currentPlayer].name} swaped all cards")

        onAllRefreshables {
            refreshAfterSwap()
        }

        if (game.players.get(game.currentPlayer).actionCounter==2){
            rootService.gameService.endTurn()
        }
    }

    /**
     * Skips one action
     *
     * Increments the current players action count by 1
     *
     * @throws IllegalStateException if no game has started yet
     */
    public fun skipSwap(){
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }

        game.players.get(game.currentPlayer).actionCounter++

        rootService.gameService.writeLog("${game.players[game.currentPlayer].name} skipped one action")

        if (game.players.get(game.currentPlayer).actionCounter==2){
            rootService.gameService.endTurn()
        }

        onAllRefreshables {
            refreshAfterSwap()
        }
    }

    /**
     * Removes the last or first card from the desk cards and adds one to the first or last card
     *
     * Increments the current player action count by 1
     *
     * @param direction boolean to show the direction
     *
     * @throws IllegalStateException if no game has started yet
     */
    public fun move(direction: Boolean){
        val game = checkNotNull(rootService.currentGame) { "No game is currently active" }

        if (game.drawStack.isEmpty()){
            rootService.gameService.refillDrawStack()
        }

        if (!direction){
            game.discardStack.push(game.deskCards.removeFirst())
            game.deskCards.addLast(game.drawStack.pop())
        }else{
            game.discardStack.push(game.deskCards.removeLast())
            game.deskCards.addFirst(game.drawStack.pop())
        }

        game.players.get(game.currentPlayer).actionCounter++

        rootService.gameService.writeLog("${game.players[game.currentPlayer].name} moved right or left")

        onAllRefreshables {
            refreshAfterMove()
        }

        if (game.players.get(game.currentPlayer).actionCounter==2){
            rootService.gameService.endTurn()
        }
    }
}