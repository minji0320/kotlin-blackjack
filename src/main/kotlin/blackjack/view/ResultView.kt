package blackjack.view

import blackjack.domain.Player

object ResultView {
    private const val DRAW_TWO_CARDS = "\n%s에게 2장을 나누어 주었습니다."
    private const val SHOW_PLAYER_CARDS = "%s카드: %s"

    fun showStartGame(names: List<String>, players: List<Player>) {
        println(DRAW_TWO_CARDS.format(names.joinToString(", ")))
        players.forEach {
            showPlayerCards(it)
        }
        println()
    }

    fun showPlayerCards(player: Player) {
        println(SHOW_PLAYER_CARDS.format(player.name, player.cards.joinToString(", ")))
    }
}
