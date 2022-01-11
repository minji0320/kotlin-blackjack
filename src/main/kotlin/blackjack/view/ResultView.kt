package blackjack.view

import blackjack.domain.Player

object ResultView {
    private const val DRAW_TWO_CARDS = "\n%s에게 2장을 나누어 주었습니다."
    private const val CANNOT_MORE_CARD = "더 이상 카드를 받을 수 없습니다."
    private const val SHOW_PLAYER_CARDS = "%s카드: %s"
    private const val SHOW_PLAYER_CARDS_AND_SCORE = "%s카드: %s - 결과: %d"

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

    fun showCannotMoreDraw() {
        println(CANNOT_MORE_CARD)
    }

    fun showGameResult(players: List<Player>) {
        println()
        players.forEach {
            println(SHOW_PLAYER_CARDS_AND_SCORE.format(it.name, it.cards.joinToString(", "), it.calculateScore()))
        }
    }
}
