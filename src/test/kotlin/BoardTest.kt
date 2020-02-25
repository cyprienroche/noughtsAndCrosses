
import java.lang.IllegalArgumentException
import java.util.stream.Stream
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Test board data structure")
class BoardTest {

    companion object {
        @JvmStatic
        fun boards(): Stream<Board> = (1..4).map { squareBoard(it) }.stream()
    }

    // fun boards(): Stream<Board> = (1..4).map { squareBoard(it) }.stream()

    // @ParameterizedTest
    // @MethodSource("boards")
    @Test
    internal fun canGetRowsFromSquaredBoard() {
        assertThat(rows(squareBoard(1)), `is`(listOf(listOf(0))))

        assertThat(rows(squareBoard(2)), `is`(listOf(listOf(0, 1), listOf(2, 3))))

        assertThat(rows(squareBoard(3)), `is`(listOf(listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8))))
    }

    @Test
    internal fun rowsFromMalformedBoardThrowsException() {
        assertThrows<IllegalArgumentException> { rows(squareBoard(0)) }

        assertThrows<IllegalArgumentException> { rows(squareBoard(-1)) }
    }

    @Test
    internal fun canGetColumnsFromSquareBoard() {
        assertThat(columns(squareBoard(1)), `is`(listOf(listOf(0))))

        assertThat(columns(squareBoard(2)), `is`(listOf(listOf(0, 2), listOf(1, 3))))

        assertThat(columns(squareBoard(3)), `is`(listOf(listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8))))
    }

    @Test
    internal fun columnsFromMalformedBoardThrowsException() {
        assertThrows<IllegalArgumentException> { columns(squareBoard(0)) }

        assertThrows<IllegalArgumentException> { columns(squareBoard(-1)) }
    }

    @Test
    internal fun canGetDiagonalFromWellFormedBoard() {
        assertThat(diagonals(squareBoard(1)), `is`(listOf(listOf(0), listOf(0))))

        assertThat(diagonals(squareBoard(2)), `is`(listOf(listOf(0, 3), listOf(1, 2))))

        assertThat(diagonals(squareBoard(3)), `is`(listOf(listOf(0, 4, 8), listOf(2, 4, 6))))
    }

    @Test
    internal fun malformedBoardDoesNotHaveDiagonals() {
        assertThat(diagonals(squareBoard(0)), `is`(listOf(listOf(), listOf())))

        assertThat(diagonals(squareBoard(-1)), `is`(listOf(listOf(), listOf())))
    }
}
