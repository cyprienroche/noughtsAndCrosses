
data class Board(var cells: List<Cell>, val dim: Int) {
    fun replace(n: Int, elem: Cell) {
        cells = replace(n, elem, cells)
    }

    override fun toString(): String = rows(this).fold("", { acc, row -> acc + row.fold("", { acc, cell -> "$acc$cell " }) + "\n" })
}

fun squareBoard(dim: Int): Board = Board(cells = (0 until (dim * dim)).map { Empty(it) }.toList(), dim = dim)

fun rows(board: Board): List<List<Cell>> = board.cells.chunked(board.dim)

fun columns(board: Board): List<List<Cell>> = rows(board).transpose()

fun diagonals(board: Board): List<List<Cell>> = diagIndexes(board.dim).map { indexes -> indexes.map { board.cells[it] } }

fun <E> replace(n: Int, elem: E, list: List<E>): List<E> = when(n) {
    0 -> elem cons list.tail()
    else -> list.head() cons replace(n - 1, elem, list.tail())
}

sealed class Cell(val pos: Int)
data class Empty(val p: Int): Cell(p) {
    override fun toString(): String = ""
}
data class Taken(val p: Int, val player: Player): Cell(p) {
    override fun toString(): String = "$player"
}

enum class Player { X, O }