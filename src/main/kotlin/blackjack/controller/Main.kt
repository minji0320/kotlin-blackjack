package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val playerNames = InputView.askPlayerNames()
    val players = initPlayers(playerNames)
    val deck = Deck()
    val dealer = Dealer()

    startGame(players, dealer, deck)
    ResultView.showStartGame(playerNames, players, dealer)

    playGame(players, dealer, deck)
    ResultView.showGameResult(players, dealer)
}

fun initPlayers(names: List<String>): List<Player> {
    val players = mutableListOf<Player>()
    names.forEach {
        players.add(Player(it))
    }
    return players
}

fun startGame(players: List<Player>, dealer: Dealer, deck: Deck) {
    dealer.drawCard(deck, 2)
    players.forEach { it.drawCard(deck, 2) }
}

fun playGame(players: List<Player>, dealer: Dealer, deck: Deck) {
    players.forEach { drawMore(it, deck) }

    if (dealer.isAbleToDraw()) {
        dealer.drawCard(deck)
        ResultView.showMoreCard()
    }

    if (dealer.isBust()) {
        ResultView.showBust()
    }

    players.forEach { it.compete(dealer) }
}

fun drawMore(player: Player, deck: Deck) {
    while (InputView.askMoreCard(player.name)) {
        if (player.isAbleToDraw()) {
            player.drawCard(deck)
            ResultView.showPlayerCards(player)
        } else {
            ResultView.showCannotMoreDraw()
            break
        }
    }

    ResultView.showPlayerCards(player)
}
