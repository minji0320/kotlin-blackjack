package blackjack.domain

class Dealer(override val cards: MutableList<Card> = mutableListOf()) : Player(NAME, cards) {
    override fun isAbleToDraw(): Boolean = calculateScore() <= CRITERIA_FOR_MORE_DRAW

    override fun convertResultToString(): String {
        val strBuffer = StringBuffer("")

        if (winCount > 0) {
            strBuffer.append("${winCount}승 ")
        }
        if (tieCount > 0) {
            strBuffer.append("${tieCount}무 ")
        }
        if (defeatCount > 0) {
            strBuffer.append("${defeatCount}패")
        }

        return strBuffer.toString().trim()
    }
    fun isBust(): Boolean = calculateScore() > BLACK_JACK_SCORE

    companion object {
        private const val NAME = "딜러"
        private const val CRITERIA_FOR_MORE_DRAW = 16
    }
}
