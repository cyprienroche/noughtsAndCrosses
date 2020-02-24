import java.lang.IllegalArgumentException

data class Board(val cells: List<Int>, val dim: Int)

fun squareBoard(dim: Int): Board = Board(cells = (0 until (dim * dim)).toList(), dim = dim)

fun rows(board: Board): List<List<Int>> = board.cells.chunked(board.dim)

fun columns(board: Board): List<List<Int>> = rows(board).transpose()

fun <E> List<List<E>>.transpose(): List<List<E>> = when {
    this.first().isEmpty() -> emptyList()
    else -> this.filter { it.isNotEmpty() }.map { it.head() } append this.map { it.tail() }.transpose()
}

fun <E> List<E>.head(): E = first()

fun <E> List<E>.tail(): List<E> = drop(1)

infix fun <E> E.append(xs: List<E>): List<E> = listOf(this).plus(xs)
