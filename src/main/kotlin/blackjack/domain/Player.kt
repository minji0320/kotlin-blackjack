package blackjack.domain

open class Player(val name: String, open val cards: MutableList<Card> = mutableListOf()) {
    protected var winCount = 0
    protected var tieCount = 0
    protected var defeatCount = 0

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

    fun compete(other: Dealer) {
        if (this === other) {
            return
        }

        when {
            this.calculateScore() > other.calculateScore() -> {
                winCount++
                other.defeatCount++
            }
            this.calculateScore() == other.calculateScore() -> {
                tieCount++
                other.tieCount++
            }
            else -> {
                defeatCount++
                other.winCount++
            }
        }
    }

    open fun convertResultToString(): String {
        return when {
            winCount > 0 -> "승"
            tieCount > 0 -> "무"
            defeatCount > 0 -> "패"
            else -> throw IllegalArgumentException()
        }
    }

    companion object {
        private const val CRITERIA_FOR_CHANGING_ACE = 11
        private const val ADD_ACE_SCORE = 10
        const val BLACK_JACK_SCORE = 21
    }
}
