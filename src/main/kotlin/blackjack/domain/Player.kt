package blackjack.domain

open class Player(val name: String, open val cards: MutableList<Card> = mutableListOf()) {
    var betting = 0
        private set
    var profit = 0
        protected set
    var score = 0
        get() = calculateScore()
        private set
    protected var winCount = 0
    protected var tieCount = 0
    protected var defeatCount = 0

    fun bet(bettingAmount: Int) {
        betting = bettingAmount
    }

    private fun calculateScore(): Int {
        var score = cards.sumOf { it.denomination.score }
        if (isExistAce() && score <= CRITERIA_FOR_CHANGING_ACE) {
            score += ADD_ACE_SCORE
        }

        return score
    }

    fun isExistAce(): Boolean = cards.find { it.denomination == Denomination.ACE } != null

    open fun isAbleToDraw(): Boolean = score < BLACK_JACK_SCORE

    fun drawCard(deck: Deck, count: Int = 1) {
        repeat(count) { cards.add(deck.draw()) }
    }

    fun isBust(): Boolean = score > BLACK_JACK_SCORE

    fun isBlackjack(): Boolean = cards.size == INITIAL_CARD_COUNT && score == BLACK_JACK_SCORE

    fun compete(dealer: Dealer) {
        when {
            dealer.isBust() -> win(dealer)
            isBust() -> lose(dealer)
            dealer.isBlackjack() && isBlackjack() -> tie(dealer)
            isBlackjack() -> blackjack(dealer)
            score > dealer.score -> win(dealer)
            score == dealer.score -> tie(dealer)
            else -> lose(dealer)
        }
    }

    private fun win(dealer: Dealer) {
        winCount++
        dealer.defeatCount++
        profit += betting
        dealer.profit -= betting
    }

    private fun lose(dealer: Dealer) {
        defeatCount++
        dealer.winCount++
        profit -= betting
        dealer.profit += betting
    }

    private fun tie(dealer: Dealer) {
        tieCount++
        dealer.tieCount++
    }

    private fun blackjack(dealer: Dealer) {
        winCount++
        dealer.defeatCount++
        profit += (betting * 1.5).toInt()
        dealer.profit -= (betting * 1.5).toInt()
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
        private const val INITIAL_CARD_COUNT = 2
    }
}
