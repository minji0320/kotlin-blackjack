package blackjack.domain

class Dealer : Player(NAME) {
    override fun isAbleToDraw(): Boolean {
        return calculateScore() <= CRITERIA_FOR_MORE_DRAW
    }

    companion object {
        private const val NAME = "딜러"
        private const val CRITERIA_FOR_MORE_DRAW = 16
    }
}
