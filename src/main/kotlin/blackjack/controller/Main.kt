package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playerNames = InputView.askPlayerNames()
    val players = initPlayers(playerNames)
    val deck = Deck()

    startGame(players, deck)
    ResultView.showStartGame(playerNames, players)
}

fun initPlayers(names: List<String>): List<Player> {
    val players = mutableListOf<Player>()
    names.forEach {
        players.add(Player(it))
    }
    return players
}

fun startGame(players: List<Player>, deck: Deck) {
    players.forEach { player ->
        repeat(2) { player.drawCard(deck.draw()) }
    }
}
