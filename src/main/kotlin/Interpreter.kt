import java.util.InputMismatchException
import java.util.Scanner

class Interpreter {

    private var scanner: Scanner
    private val board: SquareBoard
    private var currentPlayer: Player

    init {
        scanner = Scanner(System.`in`)
        board = setUpBoard()
        currentPlayer = Player.values()[0]
    }

    private fun setUpBoard(): SquareBoard {
        println("Welcome to a game of noughts and crosses.")
        println("Players are: ${Player.values().toList()}")
        var dim = 0
        while (dim < 1) {
            println("Please enter the dimension of the board: (where dim > 0)")
            try {
                dim = scanner.nextInt()
            } catch (e: InputMismatchException) {
                scanner = Scanner(System.`in`)
                println("dimension must be an integer\n")
            }
        }
        return SquareBoard(dim)
    }

    fun startGame(): Unit {
        while (!board.isGameOver()) {
            currentPlayer = nextPlayerMove()
            println(board.toStringPretty())
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
                scanner = Scanner(System.`in`)
                println("position must be an integer within range 0..${board.dim}\n")
            }
        }
        return Position(x, y)
    }
}