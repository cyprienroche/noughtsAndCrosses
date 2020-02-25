
data class Board(val cells: List<Int>, val dim: Int)

fun squareBoard(dim: Int): Board = Board(cells = (0 until (dim * dim)).toList(), dim = dim)

fun rows(board: Board): List<List<Int>> = board.cells.chunked(board.dim)

fun columns(board: Board): List<List<Int>> = rows(board).transpose()

fun diagonals(board: Board): List<List<Int>> = diagIndexes(board.dim).map { indexes -> indexes.map { board.cells[it] } }
