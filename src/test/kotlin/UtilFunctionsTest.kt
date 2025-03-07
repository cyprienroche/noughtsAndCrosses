
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Test util functions on lists")
class UtilFunctionsTest {

    @Test
    internal fun canTransposeListOfListOfAnyLength() {
        assertThat(
            listOf(listOf(0, 1), listOf(2, 3)).transpose(),
            `is`(listOf(listOf(0, 2), listOf(1, 3)))
        )

        assertThat(
            listOf(listOf(0, 1), listOf(2)).transpose(),
            `is`(listOf(listOf(0, 2), listOf(1)))
        )

        assertThat(
            listOf(listOf(1, 2)).transpose(),
            `is`(listOf(listOf(1), listOf(2)))
        )

        assertThat(
            listOf(listOf(1, 2, 3), listOf(4, 5)).transpose(),
            `is`(listOf(listOf(1, 4), listOf(2, 5), listOf(3)))
        )

        assertThat(
            listOf(listOf(0, 1), listOf(), listOf(3)).transpose(),
            `is`(listOf(listOf(0, 3), listOf(1)))
        )
    }

    @Test
    internal fun canGetDiagonalIndexOfSquareBoards() {
        assertThat(diagIndexes(1), `is`(listOf(listOf(0), listOf(0))))

        assertThat(diagIndexes(2), `is`(listOf(listOf(0, 3), listOf(1, 2))))

        assertThat(diagIndexes(3), `is`(listOf(listOf(0, 4, 8), listOf(2, 4, 6))))
    }

    @Test
    internal fun malformedBoardDoesNotHaveDiagonals() {
        assertThat(diagIndexes(0), `is`(listOf(listOf(), listOf())))

        assertThat(diagIndexes(-1), `is`(listOf(listOf(), listOf())))
    }

    @Test
    internal fun canTellIfNumberIsPerfectSquare() {
        assertTrue(isPerfectSquare(1))
        assertTrue(isPerfectSquare(4))
        assertTrue(isPerfectSquare(9))

        assertFalse(isPerfectSquare(2))
        assertFalse(isPerfectSquare(3))
        assertFalse(isPerfectSquare(5))
        assertFalse(isPerfectSquare(7))
    }
    companion object {
        @JvmStatic
        fun squares() = (Player.values().indices).map { Arguments.of(it) }.toList()
    }

    @ParameterizedTest(name = "Next player after {0} should be {1}")
    @MethodSource("squares")
    fun canGetNextPlayer(input: Int) {
            assertThat(nextPlayer(Player.values()[input]), `is`(Player.values()[(input + 1) % Player.values().size]))
    }
}
