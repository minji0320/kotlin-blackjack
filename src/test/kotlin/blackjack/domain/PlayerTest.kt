package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {
    private lateinit var deck: Deck
    private lateinit var player: Player
    private val bustCards = mutableListOf(
        Card(Suit.CLUBS, Denomination.SIX),
        Card(Suit.HEARTS, Denomination.TEN),
        Card(Suit.DIAMONDS, Denomination.EIGHT)
    )
    private val blackjackCards = mutableListOf(
        Card(Suit.CLUBS, Denomination.ACE),
        Card(Suit.HEARTS, Denomination.TEN)
    )

    @Test
    fun `플레이어가 카드 draw 시 보유한 카드 개수 증가하는지 테스트`() {
        deck = Deck()
        player = Player("A")
        player.drawCard(deck)
        assertThat(player.cards.size).isEqualTo(1)

        player.drawCard(deck)
        assertThat(player.cards.size).isEqualTo(2)
    }

    @Test
    fun `플레이어가 ACE 카드를 보유한 경우`() {
        player = Player("A", mutableListOf(Card(Suit.CLUBS, Denomination.ACE)))
        assertThat(player.isExistAce()).isTrue
    }

    @Test
    fun `플레이어 점수 계산 테스트`() {
        player = Player("A", mutableListOf(Card(Suit.CLUBS, Denomination.SEVEN)))
        assertThat(player.score).isEqualTo(7)
    }

    @Test
    fun `ACE 카드가 11점으로 계산되는 경우`() {
        player = Player(
            "A",
            mutableListOf(
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.CLUBS, Denomination.FIVE)
            )
        )
        assertThat(player.score).isEqualTo(16)
    }

    @Test
    fun `ACE 카드가 1점으로 계산되는 경우`() {
        player = Player(
            "A",
            mutableListOf(
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.HEARTS, Denomination.FIVE),
                Card(Suit.DIAMONDS, Denomination.EIGHT)
            )
        )
        assertThat(player.score).isEqualTo(14)
    }

    @Test
    fun `21점 이상인 경우 draw 불가능`() {
        player = Player(
            "A",
            mutableListOf(
                Card(Suit.CLUBS, Denomination.SEVEN),
                Card(Suit.HEARTS, Denomination.TEN),
                Card(Suit.DIAMONDS, Denomination.EIGHT)
            )
        )

        assertThat(player.isAbleToDraw()).isFalse
    }

    @Test
    fun `player가 dealer보다 점수가 높을 때`() {
        player = Player(
            "A",
            mutableListOf(
                Card(Suit.CLUBS, Denomination.SEVEN),
                Card(Suit.HEARTS, Denomination.TEN)
            )
        )

        val dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.SEVEN),
                Card(Suit.HEARTS, Denomination.EIGHT),
            )
        )

        player.compete(dealer)
        assertThat(player.convertResultToString()).isEqualTo("승")
        assertThat(dealer.convertResultToString()).isEqualTo("1패")
    }

    @Test
    fun `베팅 시 금액이 옳게 저장되는지 체크`() {
        player = Player("A")
        player.bet(1000)
        assertThat(player.betting).isEqualTo(1000)
    }

    @Test
    fun `dealer가 Bust 상태인 경우`() {
        player = Player(
            "A",
            mutableListOf(
                Card(Suit.CLUBS, Denomination.THREE),
                Card(Suit.HEARTS, Denomination.TEN)
            )
        )

        val dealer = Dealer(bustCards)

        player.bet(1000)
        player.compete(dealer)
        assertThat(player.profit).isEqualTo(1000)
        assertThat(dealer.profit).isEqualTo(-1000)
    }

    @Test
    fun `player가 Bust 상태인 경우`() {
        player = Player("A", bustCards)

        val dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.SEVEN),
                Card(Suit.HEARTS, Denomination.EIGHT),
            )
        )

        player.bet(1000)
        player.compete(dealer)
        assertThat(player.profit).isEqualTo(-1000)
        assertThat(dealer.profit).isEqualTo(1000)
    }

    @Test
    fun `dealer와 player 모두 블랙잭인 경우`() {
        player = Player("A", blackjackCards)
        val dealer = Dealer(blackjackCards)

        player.bet(1000)
        player.compete(dealer)
        assertThat(player.profit).isEqualTo(0)
        assertThat(dealer.profit).isEqualTo(0)
    }

    @Test
    fun `player만 블랙잭인 경우`() {
        player = Player("A", blackjackCards)

        val dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.SEVEN),
                Card(Suit.HEARTS, Denomination.EIGHT),
            )
        )

        player.bet(1000)
        player.compete(dealer)
        assertThat(player.profit).isEqualTo(1500)
        assertThat(dealer.profit).isEqualTo(-1500)
    }

    @Test
    fun `player의 점수가 더 높은 경우`() {
        player = Player(
            "A",
            mutableListOf(
                Card(Suit.CLUBS, Denomination.EIGHT),
                Card(Suit.HEARTS, Denomination.TEN)
            )
        )

        val dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.SEVEN),
                Card(Suit.HEARTS, Denomination.EIGHT),
            )
        )

        player.bet(1000)
        player.compete(dealer)
        assertThat(player.profit).isEqualTo(1000)
        assertThat(dealer.profit).isEqualTo(-1000)
    }

    @Test
    fun `dealer와 player의 점수가 같은 경우`() {
        player = Player(
            "A",
            mutableListOf(
                Card(Suit.CLUBS, Denomination.EIGHT),
                Card(Suit.HEARTS, Denomination.TEN)
            )
        )

        val dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.TEN),
                Card(Suit.HEARTS, Denomination.EIGHT),
            )
        )

        player.bet(1000)
        player.compete(dealer)
        assertThat(player.profit).isEqualTo(0)
        assertThat(dealer.profit).isEqualTo(0)
    }

    @Test
    fun `player의 점수가 더 낮은 경우`() {
        player = Player(
            "A",
            mutableListOf(
                Card(Suit.CLUBS, Denomination.TWO),
                Card(Suit.HEARTS, Denomination.TEN)
            )
        )

        val dealer = Dealer(
            mutableListOf(
                Card(Suit.CLUBS, Denomination.SEVEN),
                Card(Suit.HEARTS, Denomination.EIGHT),
            )
        )

        player.bet(1000)
        player.compete(dealer)
        assertThat(player.profit).isEqualTo(-1000)
        assertThat(dealer.profit).isEqualTo(1000)
    }
}
