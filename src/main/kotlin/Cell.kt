sealed class Cell {
    fun isTakenBy(p: Player) = this is Taken && player == p
}

object Empty : Cell() {
    override fun toString(): String = " "
}

data class Taken(val player: Player) : Cell() {
    override fun toString(): String = "$player"
}
