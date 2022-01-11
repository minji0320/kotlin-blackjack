package blackjack.domain

data class Card(val suit: Suit, val denomination: Denomination) {
    val score = denomination.score
    val isAce = denomination == Denomination.ACE

    override fun toString(): String {
        return denomination.displayName + suit.displayName
    }
}
