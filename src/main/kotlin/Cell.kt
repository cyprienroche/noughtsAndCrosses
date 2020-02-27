sealed class Cell {
    fun isTakenBy(p: Player) = when (this) {
        is Empty -> false
        is Taken -> this.player == p
    }
}

object Empty : Cell() {
    override fun toString(): String = " "
}

data class Taken(val player: Player) : Cell() {
    override fun toString(): String = "$player"
}