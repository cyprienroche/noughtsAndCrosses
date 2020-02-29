import java.lang.IllegalArgumentException
import kotlin.math.sqrt

class SquareBoard : Board {

    private val dim: Int
    private val cells: MutableList<Cell>

    constructor(dim: Int) {
        this.dim = dim
        this.cells = (0 until (dim * dim)).map { Empty }.toMutableList()
        checkValidSquareBoard()
    }

    constructor(cells: List<Cell>) {
        this.dim = sqrt(cells.size.toDouble()).toInt()
        this.cells = cells.toMutableList()
        checkValidSquareBoard()
    }

    private fun checkValidSquareBoard() {
        if (dim < 1) throw IllegalArgumentException("square board dimension must be greater than zero")
        if (!isPerfectSquare(cells.size)) throw IllegalArgumentException("square board must have perfect square dimension, but dimension $dim is not")
    }

    private fun fillToWin() = listOf(rows(), columns(), diagonals())

    fun rows(): List<List<Cell>> = cells.chunked(dim)

    fun columns(): List<List<Cell>> = rows().transpose()

    fun diagonals(): List<List<Cell>> = diagIndexes(dim).map { indexes -> indexes.map { cells[it] } }

    override fun place(cell: Cell, p: Position) {
        cells[p.x + p.y * dim] = cell
    }

    override fun isGameOver(): Boolean = winner() != null

    override fun winner(): Player? = Player.values().firstOrNull { hasWon(it) }

    private fun hasWon(p: Player): Boolean = fillToWin().any { p.hasFilledAny(it) }

    private fun Player.hasFilledAny(cells: List<List<Cell>>): Boolean =
        cells.any { cell -> cell.all { it.isTakenBy(this) } }

    override fun toString(): String = toStringPretty()

    fun toStringPretty(): String = rows().fold("", { acc, row -> acc + rowToString(row, "\n") })

    fun toStringWithCoordinates(): String =
        rows().foldIndexed("", { j, acc, row -> acc + rowToString(row, "| $j\n") }) +
            foldUntilDim { acc, _ -> "$acc- " } + '\n' +
            foldUntilDim { acc, i -> "$acc$i " } + '\n'

    private fun foldUntilDim(lambda: (String, Int) -> String): String = (0 until dim).fold("", lambda)

    private fun rowToString(row: List<Cell>, postfix: String): String =
        row.fold("", { acc, cell -> "$acc$cell " }) + postfix
}
