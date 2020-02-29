import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Test board data structure")
class BoardTest {

    private val board1WinnerX: Board = SquareBoard(listOf(Taken(Player.X)))
    private val board1NoWinner: Board = SquareBoard(2)

    private val board2WinnerX: Board = SquareBoard(listOf(Taken(Player.X), Taken(Player.X), Taken(Player.O), Empty))
    private val board2NoWinner: Board = SquareBoard(listOf(Taken(Player.X), Taken(Player.O), Empty, Empty))

    private val board3NoWinner: Board = SquareBoard(3)
    private val board3WinnerO: Board = SquareBoard(listOf(Taken(Player.O), Taken(Player.X), Empty, Empty, Taken(Player.O), Taken(Player.X), Empty, Empty, Taken(Player.O)))

    @Test
    internal fun correctlyIdentifyWinner1By1() {
        assertThat(board1WinnerX.winner(), `is`(Player.X))
        assertNull(board1NoWinner.winner())
    }

    @Test
    internal fun correctlyIdentifyWinner2By2() {
        assertThat(board2WinnerX.winner(), `is`(Player.X))
        assertNull(board2NoWinner.winner())
    }

    @Test
    internal fun correctlyIdentifyWinner3By3() {
        assertThat(board3WinnerO.winner(), `is`(Player.O))
        assertNull(board3NoWinner.winner())
    }
}
