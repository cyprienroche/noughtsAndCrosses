
fun <E> List<E>.head(): E = first()

fun <E> List<E>.tail(): List<E> = drop(1)

infix fun <E> E.append(xs: List<E>): List<E> = listOf(this).plus(xs)

fun <E> List<List<E>>.transpose(): List<List<E>> = when {
    this.first().isEmpty() -> emptyList()
    else -> this.filter { it.isNotEmpty() }.map { it.head() } append this.map { it.tail() }.transpose()
}

fun diagIndexSW(n: Int) = (1..n).map { it * (n - 1) }

fun diagIndexSE(n: Int) = (0..n - 1).map { it * (n + 1) }

fun diagIndexes(n: Int) = listOf(diagIndexSE(n), diagIndexSW(n))
