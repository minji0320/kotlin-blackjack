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
        val card = cards.random()
        cards.remove(card)

        return card
    }

    fun size(): Int = cards.size
}
