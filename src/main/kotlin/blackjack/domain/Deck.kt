package blackjack.domain

class Deck {
    private val cards: MutableList<Card> = init()

    private fun init(): MutableList<Card> {
        val cards = mutableListOf<Card>()
        Suit.values().forEach { suit ->
            Denomination.values().forEach {
                cards.add(Card(suit, it))
            }
        }
        return cards
    }

    fun draw(): Card {
        require(cards.size > 0) { EMPTY_DECK_EXCEPTION_MSG }

        val card = cards.random()
        cards.remove(card)

        return card
    }

    fun size(): Int = cards.size

    companion object {
        private const val EMPTY_DECK_EXCEPTION_MSG: String = "Deck의 카드가 모두 소진되었습니다."
    }
}
