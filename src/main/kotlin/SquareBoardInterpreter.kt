import java.util.Scanner

class SquareBoardInterpreter(private val scanner: Scanner, private val dim: Int) : BoardInterpreter {

    private var currentPlayer: Player = Player.values()[0]
    private val board: SquareBoard = SquareBoard(dim)

    override fun startGame() {
        while (!board.isGameOver()) {
            currentPlayer = nextPlayerMove()
            println(board)
        }
        println("Game is over, player ${board.winner()} has won.")
    }

    private fun nextPlayerMove(): Player {
        board.place(Taken(currentPlayer), getPosition())
        return nextPlayer(currentPlayer)
    }

    private fun getPosition(): Position {
        var x = -1
        var y = -1
        while (x < 0 || y < 0) {
            println("Place $currentPlayer at position: (where 0 <= x, y < ${board.dim})")
            try {
                x = scanner.nextInt()
                y = scanner.nextInt()
            } catch (e: Exception) {
                scanner.next()
                scanner.next()
                println("position must be an integer within range 0..${board.dim}")
            }
        }
        return Position(x, y)
    }
}
