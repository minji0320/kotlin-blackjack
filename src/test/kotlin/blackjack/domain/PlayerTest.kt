package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PlayerTest {
    private lateinit var deck: Deck
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        deck = Deck()
        player = Player("A")
    }

    @Test
    fun `플레이어가 카드 draw 시 보유한 카드 개수 증가하는지 테스트`() {
        player.drawCard(deck.draw())
        assertThat(player.cards.size).isEqualTo(1)

        player.drawCard(deck.draw())
        assertThat(player.cards.size).isEqualTo(2)
    }
}
