package service

import entity.Player

/**
 * [Refreshable] implementation that refreshes nothing, but remembers
 * if a refresh method has been called (since last [reset])
 */
class TestRefreshable: Refreshable {
    var refreshAfterGameStartCalled: Boolean = false
        private set

    var refreshAfterEndTurnCalled: Boolean = false
        private set

    var refreshAfterSwapCalled: Boolean = false
        private set

    var refreshAfterMoveCalled: Boolean = false
        private set

    var refreshAfterEndGameCalled: Boolean = false
        private set

    var refreshAfterDrawStackCalled: Boolean = false
        private set

    /**
     * resets all *Called properties to false
     */
    fun reset(){
        refreshAfterGameStartCalled = false
        refreshAfterEndTurnCalled = false
        refreshAfterSwapCalled = false
        refreshAfterMoveCalled = false
        refreshAfterEndGameCalled = false
        refreshAfterDrawStackCalled = false
    }

    override fun refreshAfterGameStart(){
        refreshAfterGameStartCalled = true
    }

    override fun refreshAfterEndTurn(){
        refreshAfterEndTurnCalled = true
    }

    override fun refreshAfterSwap(){
        refreshAfterSwapCalled = true
    }

    override fun refreshAfterMove(){
        refreshAfterMoveCalled = true
    }

    override fun refreshAfterEndGame(ranking: MutableList<Pair<Player, Int>> ){
        refreshAfterEndGameCalled = true
    }

    override fun refreshAfterDrawStack(){
        refreshAfterDrawStackCalled = true
    }
}