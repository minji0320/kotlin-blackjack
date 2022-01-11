package blackjack.view

object InputView {
    private const val ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    private const val INVALID_PLAYER_NAMES_MSG = "참가자의 이름을 올바르게 입력해 주세요. (쉼표 기준으로 분리)"

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
}
