import kotlin.math.sqrt

private fun <E> List<E>.head(): E = first()

private fun <E> List<E>.tail(): List<E> = drop(1)

private infix fun <E> E.cons(xs: List<E>): List<E> = listOf(this).plus(xs)

fun <E> List<List<E>>.transpose(): List<List<E>> = when {
    this.first().isEmpty() -> emptyList()
    else -> this.asSequence().filter { it.isNotEmpty() }.map { it.head() }.toList() cons this.map { it.tail() }.transpose()
}

private fun diagIndexSW(n: Int) = (1..n).map { it * (n - 1) }

private fun diagIndexSE(n: Int) = (0..n - 1).map { it * (n + 1) }

fun diagIndexes(n: Int) = listOf(diagIndexSE(n), diagIndexSW(n))

fun isPerfectSquare(n: Int): Boolean = sqrt(n.toDouble()).toInt() * sqrt(n.toDouble()).toInt() == n
