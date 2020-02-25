
import java.lang.IllegalArgumentException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Test board data structure")
class BoardTest {

    private fun List<List<Int>>.posToCells(): List<List<Cell>> = this.map { it.map { id -> Empty(id) } }

    @Test
    internal fun canGetRowsFromSquaredBoard() {
        assertThat(rows(squareBoard(1)), `is`(listOf(listOf(0)).posToCells()))

        assertThat(rows(squareBoard(2)), `is`(listOf(listOf(0, 1), listOf(2, 3)).posToCells()))

        assertThat(rows(squareBoard(3)), `is`(listOf(listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8)).posToCells()))
    }

    @Test
    internal fun rowsFromMalformedBoardThrowsException() {
        assertThrows<IllegalArgumentException> { rows(squareBoard(0)) }

        assertThrows<IllegalArgumentException> { rows(squareBoard(-1)) }
    }

    @Test
    internal fun canGetColumnsFromSquareBoard() {
        assertThat(columns(squareBoard(1)), `is`(listOf(listOf(0)).posToCells()))

        assertThat(columns(squareBoard(2)), `is`(listOf(listOf(0, 2), listOf(1, 3)).posToCells()))

        assertThat(columns(squareBoard(3)), `is`(listOf(listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8)).posToCells()))
    }

    @Test
    internal fun columnsFromMalformedBoardThrowsException() {
        assertThrows<IllegalArgumentException> { columns(squareBoard(0)) }

        assertThrows<IllegalArgumentException> { columns(squareBoard(-1)) }
    }

    @Test
    internal fun canGetDiagonalFromWellFormedBoard() {
        assertThat(diagonals(squareBoard(1)), `is`(listOf(listOf(0), listOf(0)).posToCells()))

        assertThat(diagonals(squareBoard(2)), `is`(listOf(listOf(0, 3), listOf(1, 2)).posToCells()))

        assertThat(diagonals(squareBoard(3)), `is`(listOf(listOf(0, 4, 8), listOf(2, 4, 6)).posToCells()))
    }

    @Test
    internal fun malformedBoardDoesNotHaveDiagonals() {
        assertThat(diagonals(squareBoard(0)), `is`(listOf(listOf(), listOf())))

        assertThat(diagonals(squareBoard(-1)), `is`(listOf(listOf(), listOf())))
    }
}
