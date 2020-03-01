import java.util.Scanner

class Interpreter(private val scanner: Scanner, private val board: Board, private val size: Int) {

    private var currentPlayer: Player = Player.values()[0]

    // starts a game of noughts and crosses
    fun startGame() {
        while (!board.isGameOver()) {
            currentPlayer = nextPlayerMove()
            println(board)
        }
        println("Game is over, player ${board.winner()} has won.")
    }

    // allows the next player to play
    private fun nextPlayerMove(): Player {
        board.place(Taken(currentPlayer), getPosition())
        return nextPlayer(currentPlayer)
    }

    // reads two integers provided by the user and return a Position with those integers
    private fun getPosition(): Position {
        println("Place $currentPlayer at position: (where 0 <= x < ${this.size} and 0 <= y < ${this.size})")
        val x = readAnInt(scanner, -1) { x -> x in 0 until this.size }
        val y = readAnInt(scanner, -1) { y -> y in 0 until this.size }
        return Position(x, y)
    }
}
