class BoardFactory {
    companion object {
        fun setUpBoard(size: Int): Board {
            return SquareBoard(size)
        }
    }
}
