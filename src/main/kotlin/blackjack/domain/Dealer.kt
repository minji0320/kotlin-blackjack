package blackjack.domain

class Dealer(override val cards: MutableList<Card> = mutableListOf()) : Player(NAME, cards) {
    override fun isAbleToDraw(): Boolean = calculateScore() <= CRITERIA_FOR_MORE_DRAW

    fun isBust(): Boolean = calculateScore() > BLACK_JACK_SCORE

    companion object {
        private const val NAME = "딜러"
        private const val CRITERIA_FOR_MORE_DRAW = 16
    }
}
