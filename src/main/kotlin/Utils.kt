fun <E> List<E>.head(): E? = when {
    isEmpty() -> null
    else -> first()
}

fun <E> List<E>.tail(): List<E> = drop(1)

infix fun <E> E.cons(xs: List<E>): List<E> = listOf(this).plus(xs)

fun <E> List<List<E>>.transpose(): List<List<E>> = when {
    this.first().isEmpty() -> emptyList()
    else -> this.asSequence().filter { it.isNotEmpty() }.map { it.head()!! }.toList() cons this.map { it.tail() }.transpose()
}

fun diagIndexSW(n: Int) = (1..n).map { it * (n - 1) }

fun diagIndexSE(n: Int) = (0..n - 1).map { it * (n + 1) }

fun diagIndexes(n: Int) = listOf(diagIndexSE(n), diagIndexSW(n))
