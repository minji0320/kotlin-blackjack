package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player

object ResultView {
    private const val DRAW_TWO_CARDS = "\n딜러와 %s에게 2장을 나누어 주었습니다."
    private const val CANNOT_MORE_CARD = "더 이상 카드를 받을 수 없습니다."
    private const val SHOW_PLAYER_CARDS = "%s카드: %s"
    private const val SHOW_DEALER_CARD = "딜러: %s"
    private const val MORE_CARD = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val BUST = "딜러의 점수가 21점을 초과하였습니다."
    private const val SHOW_PLAYER_CARDS_AND_SCORE = "%s카드: %s - 결과: %d"
    private const val FINAL_RESULT_MSG = "\n## 최종 수익"
    private const val FINAL_RESULT = "%s: %s"

    fun showStartGame(names: List<String>, players: List<Player>, dealer: Dealer) {
        println(DRAW_TWO_CARDS.format(names.joinToString(", ")))
        showDealerCard(dealer)
        players.forEach {
            showPlayerCards(it)
        }
        println()
    }

    fun showPlayerCards(player: Player) {
        println(SHOW_PLAYER_CARDS.format(player.name, player.cards.joinToString(", ")))
    }

    fun showDealerCard(dealer: Dealer) {
        println(SHOW_DEALER_CARD.format(dealer.cards[0]))
    }

    fun showCannotMoreDraw() {
        println(CANNOT_MORE_CARD)
    }

    fun showMoreCard() {
        println(MORE_CARD)
    }

    fun showBust() {
        println(BUST)
    }

    fun showGameResult(players: List<Player>, dealer: Dealer) {
        println()
        println(SHOW_PLAYER_CARDS_AND_SCORE.format(dealer.name, dealer.cards.joinToString(", "), dealer.score))
        players.forEach {
            println(SHOW_PLAYER_CARDS_AND_SCORE.format(it.name, it.cards.joinToString(", "), it.score))
        }

        println(FINAL_RESULT_MSG)
        println(FINAL_RESULT.format(dealer.name, dealer.profit))
        players.forEach {
            println(FINAL_RESULT.format(it.name, it.profit))
        }
    }
}
