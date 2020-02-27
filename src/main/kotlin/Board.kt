import java.util.Optional

data class Board(val cells: MutableList<Cell>, val dim: Int) {

    companion object {
        fun squareBoard(dim: Int) = Board((0 until (dim * dim)).map { Empty }.toMutableList(), dim)
    }

    fun rows(): List<List<Cell>> = cells.chunked(dim)

    fun columns(): List<List<Cell>> = rows().transpose()

    fun diagonals(): List<List<Cell>> = diagIndexes(dim).map { indexes -> indexes.map { cells[it] } }

    fun place(cell: Cell, p: Position): Unit {
        cells[p.x + p.y * dim] = cell
    }

    fun toStringPretty(): String = rows().fold("", { acc, row -> acc + rowToString(row, "\n") })

    fun toStringWithCoordinates(): String =
        rows().foldIndexed("", { j, acc, row -> acc + rowToString(row, "| $j\n") }) +
            foldUntilDim { acc, _ -> "$acc- " } + '\n' +
            foldUntilDim { acc, i -> "$acc$i " } + '\n'

    private fun foldUntilDim(lambda: (String, Int) -> String): String = (0 until dim).fold("", lambda)

    private fun rowToString(row: List<Cell>, postfix: String): String =
        row.fold("", { acc, cell -> "$acc$cell " }) + postfix

    fun isGameOver(): Boolean = winner().isPresent

    fun winner(): Optional<Player> = when {
        hasWon(Player.X) -> Optional.of(Player.X)
        hasWon(Player.O) -> Optional.of(Player.O)
        else -> Optional.empty()
    }

    fun hasWon(p: Player): Boolean =
        hasFilledAny(p, rows()) || hasFilledAny(p, columns()) || hasFilledAny(p, diagonals())

    fun hasFilledAny(p: Player, cells: List<List<Cell>>): Boolean = cells.any { cell -> cell.all { it.isTakenBy(p) } }
}

