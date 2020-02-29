import java.lang.IllegalArgumentException

class SquareBoard: Board {

    private val dim: Int
    private val cells: MutableList<Cell>

    constructor(dim: Int) {
        this.dim = dim
        this.cells = (0 until (dim * dim)).map { Empty }.toMutableList()
        checkValidBoard()
    }

    constructor(cells: List<Cell>) {
        this.dim = cells.size
        this.cells = cells.toMutableList()
        checkValidBoard()
    }

    private fun checkValidBoard() {
        if (dim < 1) throw IllegalArgumentException("square board dimension must be greater than zero")
        if (isPerfectSquare(dim)) throw IllegalArgumentException("square board must have perfect square dimension")
    }

    private fun fillToWin() = listOf(rows(),  columns(), diagonals())

    fun rows(): List<List<Cell>> = cells.chunked(dim)

    fun columns(): List<List<Cell>> = rows().transpose()

    fun diagonals(): List<List<Cell>> = diagIndexes(dim).map { indexes -> indexes.map { cells[it] } }

    fun place(cell: Cell, p: Position): Unit {
        cells[p.x + p.y * dim] = cell
    }

    fun isGameOver(): Boolean = winner() != null

    override fun winner(): Player? = Player.values().firstOrNull{ hasWon(it) }

    private fun hasWon(p: Player): Boolean = fillToWin().any { p.hasFilledAny(it) }

    private fun Player.hasFilledAny(cells: List<List<Cell>>): Boolean =
        cells.any { cell -> cell.all { it.isTakenBy(this) } }

    fun toStringPretty(): String = rows().fold("", { acc, row -> acc + rowToString(row, "\n") })

    fun toStringWithCoordinates(): String =
        rows().foldIndexed("", { j, acc, row -> acc + rowToString(row, "| $j\n") }) +
            foldUntilDim { acc, _ -> "$acc- " } + '\n' +
            foldUntilDim { acc, i -> "$acc$i " } + '\n'

    private fun foldUntilDim(lambda: (String, Int) -> String): String = (0 until dim).fold("", lambda)

    private fun rowToString(row: List<Cell>, postfix: String): String =
        row.fold("", { acc, cell -> "$acc$cell " }) + postfix
}

