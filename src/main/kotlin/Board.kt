interface Board {
    // return the winning Player if there is a winner, null otherwise
    fun winner(): Player?
    fun isGameOver(): Boolean
    fun place(cell: Cell, p: Position): Unit
}
