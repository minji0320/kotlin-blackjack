package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView

fun main() {
    val playerNames = InputView.askPlayerNames()
    val players = initPlayers(playerNames)
    val deck = Deck()
}

fun initPlayers(names: List<String>): List<Player> {
    val players = mutableListOf<Player>()
    names.forEach {
        players.add(Player(it))
    }
    return players
}
