import java.util.Scanner

class Interpreter {

    private val scanner: Scanner = Scanner(System.`in`)
    private val board: Board
    private var currentPlayer: Player = Player.values()[0]
    private val n: Int

    init {
        println("Welcome to a game of noughts and crosses.")
        println("Players are: ${Player.values().toList()}")
        val n = getBoardSize()
        board = SquareBoard(n)
        this.n = n
    }

    private fun getBoardSize(): Int {
        println("Please enter the dimensions of the board: (where n > 0)")
        return readAnInt { x -> x > 0 }
    }

    private fun nextPlayerMove(): Player {
        board.place(Taken(currentPlayer), getPosition())
        return nextPlayer(currentPlayer)
    }

    private fun getPosition(): Position {
        println("Place $currentPlayer at position: (where 0 <= x < $n and 0 <= y < $n)")
        val x = readAnInt { x -> x in 0 until n}
        val y = readAnInt { y -> y in 0 until n}
        return Position(x, y)
    }

    private fun readAnInt(cond: (Int) -> Boolean): Int {
        var x = 0
        do {
            try {
                x = scanner.nextInt()
            } catch (e: Exception) {
                scanner.next()
                println("input must be an integer")
            }
        } while (!cond(x))
        return x
    }

    fun startGame() {
        while (!board.isGameOver()) {
            currentPlayer = nextPlayerMove()
            println(board)
        }
        println("Game is over, player ${board.winner()} has won.")
    }
}
