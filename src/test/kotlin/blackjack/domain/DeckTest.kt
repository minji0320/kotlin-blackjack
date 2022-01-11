package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class DeckTest {
    private lateinit var deck: Deck
    private var initialSize: Int = 0

    @BeforeEach
    internal fun setUp() {
        deck = Deck()
        initialSize = Suit.values().size * Denomination.values().size
    }

    @Test
    fun `Deck 생성 시 카드 개수 체크`() {
        assertThat(deck.size() == initialSize).isTrue
    }

    @Test
    fun `카드 draw 시 카드 개수가 감소하는지 테스트`() {
        deck.draw()
        assertThat(deck.size() == initialSize - 1).isTrue

        deck.draw()
        assertThat(deck.size() == initialSize - 2).isTrue
    }

    @Test
    fun `카드 모두 소진 시에 draw 하는 경우`() {
        repeat(initialSize) {
            deck.draw()
        }

        assertThrows<IllegalArgumentException> { deck.draw() }
    }
}
