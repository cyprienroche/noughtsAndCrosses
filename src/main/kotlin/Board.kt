data class Board(val cells: List<Int>, val dim: Int)

fun rows(board: Board): List<List<Int>> = board.cells.chunked(board.dim)

fun columns(board: Board): List<List<Int>> = rows(board).transpose()

fun <E> List<List<E>>.transpose(): List<List<E>> = when {
    this.first().isEmpty() -> emptyList()
    else -> this.map { it.head() } append this.map { it.tail() }.transpose()
}

fun <E> List<E>.head(): E = first()

fun <E> List<E>.tail(): List<E> = drop(1)

infix fun <E> E.append(xs: List<E>): List<E> = listOf(this).plus(xs)