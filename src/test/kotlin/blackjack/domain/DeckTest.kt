package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {
    @Test
    fun `Deck 생성 시 카드 개수 체크`() {
        val deck = Deck()
        assertThat(deck.size() == Suit.values().size * Denomination.values().size).isTrue
    }
}
