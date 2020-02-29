import java.util.Scanner

class DimTwoBoardInterpreter(private val scanner: Scanner) : BoardInterpreter {

    private var currentPlayer: Player = Player.values()[0]
    private val board: Board
    private val n: Int
    private val m: Int

    init {
        val (n, m) = getBoardSize()
        board = SquareBoard(n)
        this.n = n
        this.m = m
    }

    private fun getBoardSize(): Pair<Int, Int> {
        println("Please enter the dimensions of the board: (where m, n > 0)")
        return readTwoInt { m, n -> m < 1 || n < 1 }
    }

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
        println("Place $currentPlayer at position: (where 0 <= x < $n and 0 <= y < $m)")
        val (x, y) = readTwoInt { x, y -> x < 0 || y < 0 }
        return Position(x, y)
    }

    private fun readTwoInt(cond: (Int, Int) -> Boolean): Pair<Int, Int> {
        var x = 0
        var y = 0
        do {
            try {
                x = scanner.nextInt()
                y = scanner.nextInt()
            } catch (e: Exception) {
                scanner.next()
                scanner.next()
                println("input must be an integer")
            }
        } while (cond(x, y))

        return x to y
    }
}
