sealed class Cell

object Empty : Cell() {
    override fun toString(): String = " "
}

data class Taken(val player: Player) : Cell() {
    override fun toString(): String = "$player"
}