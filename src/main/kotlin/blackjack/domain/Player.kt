package blackjack.domain

data class Player(val name: String, val cards: MutableList<Card> = mutableListOf())
