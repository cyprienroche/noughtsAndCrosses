interface Board {
    fun winner(): Player?
    fun isGameOver(): Boolean
    fun place(cell: Cell, p: Position): Unit
}
