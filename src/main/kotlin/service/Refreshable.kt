package service

/**
 * This interface provides a mechanism for the service layer classes to communicate
 * (usually to the GUI classes) that certain changes have been made to the entity
 * layer, so that the user interface can be updated accordingly.
 *
 * Default (empty) implementations are provided for all methods, so that implementing
 * GUI classes only need to react to events relevant to them.
 *
 * @see AbstractRefreshingService
 */
interface Refreshable{

    /**
     * perform refreshes that are necessary after a new game started
     */
    public fun refreshAfterGameStart()

    /**
     * perform refreshes that are necessary after a turn ended
     */
    public fun refreshAfterEndTurn()

    /**
     * perform refreshes that are necessary after the drawStack is filled
     */
    public  fun refreshAfterDrawStack()

    /**
     * perform refreshes that are necessary after the player swaps cards
     */
    public fun refreshAfterSwap()

    /**
     * perform refreshes that are necessary after the player moves cards
     */
    public fun refreshAfterMove()

    /**
     * perform refreshes that are necessary after the game ended
     */
    public fun refreshAfterEndGame()
}