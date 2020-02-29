fun main() {
    val board = SquareBoard(2)
    println(board.winner())

    board.place(Taken(Player.O), Position(0, 0))
    board.place(Taken(Player.O), Position(0, 1))
    board.place(Taken(Player.X), Position(1, 0))
    println(board)
    println(board.toStringPretty())
    println(board.toStringWithCoordinates())

    println(board.winner())
    println(board.isGameOver())
}
