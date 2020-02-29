import java.lang.IllegalArgumentException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Test board data structure")
class SquareBoardTest {

    private fun board2(): SquareBoard {
        val board = SquareBoard(2)
        board.place(Taken(Player.X), Position(0, 1))
        board.place(Taken(Player.X), Position(1, 0))
        board.place(Taken(Player.O), Position(1, 1))
        return board
    }

    private fun board2GameNotOver(): SquareBoard {
        val board = SquareBoard(2)
        board.place(Taken(Player.O), Position(0, 0))
        return board
    }

    private fun board3(): SquareBoard {
        val board = SquareBoard(3)
        board.place(Taken(Player.X), Position(0, 0))
        board.place(Taken(Player.X), Position(1, 0))
        board.place(Taken(Player.X), Position(2, 0))
        board.place(Taken(Player.O), Position(1, 1))
        board.place(Taken(Player.O), Position(2, 2))
        return board
    }

    private fun board3GameNotOver(): SquareBoard {
        val board = SquareBoard(3)
        board.place(Taken(Player.X), Position(0, 0))
        board.place(Taken(Player.O), Position(1, 1))
        board.place(Taken(Player.X), Position(2, 2))
        return board
    }

    @Test
    internal fun canCreateAnyBoardSizeGreaterThanZero() {
        SquareBoard(1)
        SquareBoard(listOf(Taken(Player.X)))
        SquareBoard(listOf(Taken(Player.X), Taken(Player.O), Empty, Empty))
        SquareBoard(2)
        SquareBoard(3)
    }

    @Test
    internal fun boardWithDimSmallerThanOneThrowsError() {
        assertThrows<IllegalArgumentException> { SquareBoard(0) }
        assertThrows<IllegalArgumentException> { SquareBoard(-1) }
    }

    @Test
    internal fun canGetRowsCorrectly2By2() {
        val expected: List<List<Cell>> =
            listOf(
                listOf(Empty, Taken(Player.X)),
                listOf(Taken(Player.X), Taken(Player.O))
            )

        assertThat(board2().rows(), `is`(expected))
    }

    @Test
    internal fun canGetRowsCorrectly3By3() {
        val expected: List<List<Cell>> =
            listOf(
                listOf(Taken(Player.X), Taken(Player.X), Taken(Player.X)),
                listOf(Empty, Taken(Player.O), Empty),
                listOf(Empty, Empty, Taken(Player.O))
            )
        assertThat(board3().rows(), `is`(expected))
    }

    @Test
    internal fun canGetColumnsCorrectly2By2() {
        val expected: List<List<Cell>> =
            listOf(
                listOf(Empty, Taken(Player.X)),
                listOf(Taken(Player.X), Taken(Player.O))
            )

        assertThat(board2().columns(), `is`(expected))
    }

    @Test
    internal fun canGetColumnsCorrectly3By3() {
        val expected: List<List<Cell>> =
            listOf(
                listOf(Taken(Player.X), Empty, Empty),
                listOf(Taken(Player.X), Taken(Player.O), Empty),
                listOf(Taken(Player.X), Empty, Taken(Player.O))
            )
        assertThat(board3().columns(), `is`(expected))
    }

    @Test
    internal fun canGetDiagonalsCorrectly2By2() {
        val expected: List<List<Cell>> =
            listOf(
                listOf(Empty, Taken(Player.O)),
                listOf(Taken(Player.X), Taken(Player.X))
            )

        assertThat(board2().diagonals(), `is`(expected))
    }

    @Test
    internal fun canGetDiagonalsCorrectly3By3() {
        val expected: List<List<Cell>> =
            listOf(
                listOf(Taken(Player.X), Taken(Player.O), Taken(Player.O)),
                listOf(Taken(Player.X), Taken(Player.O), Empty)
            )
        assertThat(board3().diagonals(), `is`(expected))
    }

    @Test
    internal fun canPrettyPrint2By2() {
        val expected = """
              X 
            X O 
            
        """.trimIndent()

        assertThat(board2().toStringPretty(), `is`(expected))
    }

    @Test
    internal fun canPrettyPrint3By3() {
        val expected = """
            X X X 
              O   
                O 
            
        """.trimIndent()

        assertThat(board3().toStringPretty(), `is`(expected))
    }

    @Test
    internal fun canPrintWithCoordinates2By2() {
        val expected = """
              X | 0
            X O | 1
            - - 
            0 1 
            
        """.trimIndent()

        assertThat(board2().toStringWithCoordinates(), `is`(expected))
    }

    @Test
    internal fun canTellWinner2By2() {
        assertThat(board2().winner(), `is`(Player.X))
        assertNull(board2GameNotOver().winner())
    }

    @Test
    internal fun canTellGameState2By2() {
        assertThat(board2().isGameOver(), `is`(true))
        assertThat(board2GameNotOver().isGameOver(), `is`(false))
    }

    @Test
    internal fun canTellWinner3By3() {
        assertThat(board3().winner(), `is`(Player.X))
        assertNull(board3GameNotOver().winner())
    }

    @Test
    internal fun canTellGameState3By3() {
        assertThat(board3().isGameOver(), `is`(true))
        assertThat(board3GameNotOver().isGameOver(), `is`(false))
    }
}
