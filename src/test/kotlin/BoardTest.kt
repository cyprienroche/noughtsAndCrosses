import java.lang.IllegalArgumentException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("Test board data structure")
class BoardTest {

    private fun board2(): Board {
        val board = Board.squareBoard(2)
        board.place(1, Taken(Player.X))
        board.place(2, Taken(Player.X))
        board.place(3, Taken(Player.O))
        return board
    }

    private fun board3(): Board {
        val board = Board.squareBoard(3)
        board.place(0, Taken(Player.X))
        board.place(1, Taken(Player.X))
        board.place(2, Taken(Player.X))
        board.place(4, Taken(Player.O))
        board.place(8, Taken(Player.O))
        return board
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
}
