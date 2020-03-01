import java.util.Scanner
import kotlin.math.sqrt

private fun <E> List<E>.head(): E = first()

private fun <E> List<E>.tail(): List<E> = drop(1)

private infix fun <E> E.cons(xs: List<E>): List<E> = listOf(this).plus(xs)

fun <E> List<List<E>>.transpose(): List<List<E>> = when {
    this.first().isEmpty() -> emptyList()
    else -> this.asSequence().filter { it.isNotEmpty() }.map { it.head() }.toList() cons this.map { it.tail() }.transpose()
}

// return the diagonal index of a square, starting from top-right down to bottom-left corner
private fun diagIndexSW(n: Int): List<Int> = (1..n).map { it * (n - 1) }

// return the diagonal index of a square, starting from top-left down to bottom-right corner.
private fun diagIndexSE(n: Int): List<Int> = (0..n - 1).map { it * (n + 1) }

// return both diagonal indexes of a square
fun diagIndexes(n: Int): List<List<Int>> = listOf(diagIndexSE(n), diagIndexSW(n))

// return true if the given integer is a perfect square
fun isPerfectSquare(n: Int): Boolean = sqrt(n.toDouble()).toInt() * sqrt(n.toDouble()).toInt() == n

// return the next player after the given player according to the order given in the enum Player
fun nextPlayer(player: Player): Player = Player.values()[(player.ordinal + 1) % Player.values().size]

// keeps asking the user for an integer until provided with an integer which satisfies the given condition cond
fun readAnInt(scanner: Scanner, initial: Int, cond: (Int) -> Boolean): Int {
    var x = initial
    while (!cond(x)) {
        try {
            x = scanner.nextInt()
        } catch (e: Exception) {
            scanner.next()
            println("input must be an integer")
        }
    }
    return x
}
