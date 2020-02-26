data class Board(val cells: MutableList<Cell>, val dim: Int) {

    companion object {
        fun squareBoard(dim: Int) = Board((0 until (dim * dim)).map { Empty }.toMutableList(), dim)
    }

    fun rows(): List<List<Cell>> = cells.chunked(dim)

    fun columns(): List<List<Cell>> = rows().transpose()

    fun diagonals(): List<List<Cell>> = diagIndexes(dim).map { indexes -> indexes.map { cells[it] } }

    fun place(p: Position, cell: Cell) = cells.set(p.x + p.y * dim, cell)

    fun toStringPretty(): String =
        rows().fold(
            "",
            { acc, row -> acc + row.fold("", { acc, cell -> "$acc$cell " }) + "\n" }
        )

    fun toStringWithCoordinates(): String =
        rows().foldIndexed(
            "",
            { j, acc, row -> acc + row.fold("", { acc, cell -> "$acc$cell " }) + "| $j\n" }
        ) + (0 until dim).fold("", {acc, i -> "$acc- "}) + (0 until dim).fold("\n", {acc, i -> "$acc$i "})
}

