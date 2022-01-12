package blackjack.domain

open class Player(val name: String, open val cards: MutableList<Card> = mutableListOf()) {
    private var winCount = 0
    private var tieCount = 0
    private var defeatCount = 0

    fun calculateScore(): Int {
        var score = cards.sumOf { it.denomination.score }
        if (isExistAce() && score <= CRITERIA_FOR_CHANGING_ACE) {
            score += ADD_ACE_SCORE
        }

        return score
    }

    fun isExistAce(): Boolean = cards.find { it.denomination == Denomination.ACE } != null

    open fun isAbleToDraw(): Boolean = calculateScore() < BLACK_JACK_SCORE

    fun drawCard(deck: Deck, count: Int = 1) {
        repeat(count) { cards.add(deck.draw()) }
    }

    fun compete(other: Player) {
        if (this === other) {
            return
        }

        when {
            this.calculateScore() > other.calculateScore() -> winCount++
            this.calculateScore() == other.calculateScore() -> tieCount++
            else -> defeatCount++
        }
    }

    fun convertResultToString(): String {
        var strBuffer = StringBuffer("")

        if (winCount > 0) {
            strBuffer.append("${winCount}승 ")
        }
        if (tieCount > 0) {
            strBuffer.append("${tieCount}무 ")
        }
        if (defeatCount > 0) {
            strBuffer.append("${defeatCount}패 ")
        }

        return strBuffer.toString().trim()
    }

    companion object {
        private const val CRITERIA_FOR_CHANGING_ACE = 11
        private const val ADD_ACE_SCORE = 10
        const val BLACK_JACK_SCORE = 21
    }
}
