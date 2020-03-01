import java.lang.IllegalArgumentException
import kotlin.math.sqrt

class SquareBoard : Board {

    private val size: Int
    private val cells: MutableList<Cell>

    constructor(dim: Int) {
        this.size = dim
        this.cells = (0 until (dim * dim)).map { Empty }.toMutableList()
        checkValidSquareBoard()
    }

    constructor(cells: List<Cell>) {
        this.size = sqrt(cells.size.toDouble()).toInt()
        this.cells = cells.toMutableList()
        checkValidSquareBoard()
    }

    private fun checkValidSquareBoard(): Unit {
        if (size < 1) throw IllegalArgumentException("square board dimension must be greater than zero")
        if (!isPerfectSquare(cells.size)) throw IllegalArgumentException("square board must have perfect square dimension, but dimension $size is not")
    }

    // A list containing list of cells that a player must fill to win
    private fun fillToWin(): List<List<Cell>> = listOf(rows(), columns(), diagonals()).flatten()

    fun rows(): List<List<Cell>> = cells.chunked(size)

    fun columns(): List<List<Cell>> = rows().transpose()

    fun diagonals(): List<List<Cell>> = diagIndexes(size).map { indexes -> indexes.map { cells[it] } }

    override fun place(cell: Cell, p: Position): Unit {
        cells[p.x + p.y * size] = cell
    }

    override fun isGameOver(): Boolean = winner() != null

    // return the winning Player if there is a winner, null otherwise
    override fun winner(): Player? = Player.values().firstOrNull { hasWon(it) }

    // return true if a given Player has won, false otherwise
    private fun hasWon(p: Player): Boolean = fillToWin().any { p.hasFilled(it) }

    // return true if a given player has filled all the given cells, false otherwise
    private fun Player.hasFilled(cells: List<Cell>): Boolean = cells.all { it.isTakenBy(this) }

    override fun toString(): String = toStringPretty()

    fun toStringPretty(): String = rows().fold("", { acc, row -> acc + rowToString(row, "\n") })

    fun toStringWithCoordinates(): String =
        rows().foldIndexed("", { j, acc, row -> acc + rowToString(row, "| $j\n") }) +
            foldUntilDim { acc, _ -> "$acc- " } + '\n' +
            foldUntilDim { acc, i -> "$acc$i " } + '\n'

    // folds the given function into a string, with same size as the board
    private fun foldUntilDim(lambda: (String, Int) -> String): String = (0 until size).fold("", lambda)

    private fun rowToString(row: List<Cell>, postfix: String): String =
        row.fold("", { acc, cell -> "$acc$cell " }) + postfix
}
