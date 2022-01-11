package blackjack.domain

data class Player(val name: String, val cards: MutableList<Card> = mutableListOf()) {
    fun calculateScore(): Int {
        var score = cards.sumOf { it.denomination.score }
        if (isExistAce() && score <= CRITERIA_FOR_CHANGING_ACE) {
            score += ADD_ACE_SCORE
        }

        return score
    }

    private fun isExistAce(): Boolean {
        return cards.find { it.denomination == Denomination.ACE } != null
    }

    fun isAbleToDraw(): Boolean {
        return calculateScore() < BLACK_JACK_SCORE
    }

    fun drawCard(card: Card): Boolean {
        return if (isAbleToDraw()) {
            cards.add(card)
            true
        } else {
            false
        }
    }

    companion object {
        private const val CRITERIA_FOR_CHANGING_ACE = 11
        private const val ADD_ACE_SCORE = 10
        private const val BLACK_JACK_SCORE = 21
    }
}
