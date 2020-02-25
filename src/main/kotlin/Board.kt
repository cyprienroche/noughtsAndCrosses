
data class Board(val cells: List<Cell>, val dim: Int)

fun squareBoard(dim: Int): Board = Board(cells = (0 until (dim * dim)).map { Empty(it) }.toList(), dim = dim)

fun rows(board: Board): List<List<Cell>> = board.cells.chunked(board.dim)

fun columns(board: Board): List<List<Cell>> = rows(board).transpose()

fun diagonals(board: Board): List<List<Cell>> = diagIndexes(board.dim).map { indexes -> indexes.map { board.cells[it] } }

sealed class Cell(val pos: Int)
data class Empty(val p: Int): Cell(p)
data class Taken(val p: Int, val player: Player): Cell(p)

enum class Player {
    X, O
}