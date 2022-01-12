package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DealerTest {
    private lateinit var dealer: Dealer

    @Test
    fun `처음에 받은 2장의 합계가 16 이하인 경우`() {
        dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.CLUBS, Denomination.FIVE)
            )
        )
        Assertions.assertThat(dealer.isAbleToDraw()).isTrue
    }

    @Test
    fun `처음에 받은 2장의 합계가 16 초과인 경우`() {
        dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.TEN),
                Card(Suit.DIAMONDS, Denomination.EIGHT)
            )
        )
        Assertions.assertThat(dealer.isAbleToDraw()).isFalse
    }

    @Test
    fun `21점 이하인 경우`() {
        dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.TEN),
                Card(Suit.DIAMONDS, Denomination.EIGHT),
            )
        )
        Assertions.assertThat(dealer.isBust()).isFalse
    }

    @Test
    fun `21점을 초과한 경우`() {
        dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.TEN),
                Card(Suit.DIAMONDS, Denomination.EIGHT),
                Card(Suit.DIAMONDS, Denomination.SEVEN)
            )
        )
        Assertions.assertThat(dealer.isBust()).isTrue
    }
}
