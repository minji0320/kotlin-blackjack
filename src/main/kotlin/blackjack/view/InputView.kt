package blackjack.view

object InputView {
    private const val ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    private const val ASK_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
    private const val INVALID_PLAYER_NAMES_MSG = "참가자의 이름을 올바르게 입력해 주세요. (쉼표 기준으로 분리)"
    private const val INVALID_MORE_CARD_INPUT_MSG = "응답은 y 또는 n만 가능합니다."
    private const val YES = "y"
    private const val NO = "n"

    fun askPlayerNames(): List<String> {
        println(ASK_PLAYER_NAMES)
        return validatePlayerNames(readLine())
    }

    private fun validatePlayerNames(input: String?): List<String> {
        require(!input.isNullOrBlank()) { INVALID_PLAYER_NAMES_MSG }
        val playerNames = input.split(",").map { it.trim() }
        require(playerNames.all { it.isNotBlank() }) { INVALID_PLAYER_NAMES_MSG }
        return playerNames
    }

    fun askMoreCard(name: String): Boolean {
        println(ASK_MORE_CARD.format(name))
        val input = readLine()
        require(input == YES || input == NO) { INVALID_MORE_CARD_INPUT_MSG }
        return input == YES
    }
}
