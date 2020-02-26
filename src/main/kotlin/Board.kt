data class Board(val cells: MutableList<Cell>, val dim: Int) {

    fun rows(): List<List<Cell>> = cells.chunked(dim)

    fun columns(): List<List<Cell>> = rows().transpose()

    fun diagonals(): List<List<Cell>> = diagIndexes(dim).map { indexes -> indexes.map { cells[it] } }

    fun place(n: Int, cell: Cell) = cells.set(n, cell)

    override fun toString(): String =
        rows().fold(
            "",
            { acc, row -> acc + row.fold("", { acc, cell -> "$acc$cell " }) + "\n" }
        )

    companion object {
        fun squareBoard(dim: Int) = Board((0 until (dim * dim)).map { Empty }.toMutableList(), dim)
    }
}

