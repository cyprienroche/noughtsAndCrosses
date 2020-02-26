
fun main() {
    val board = Board.squareBoard(2)
    board.place(Position(0,0), Taken(Player.X))
    board.place(Position(0,1), Taken(Player.O))
    board.place(Position(1,0), Taken(Player.X))
    println(board)
    println(board.toStringPretty())
    println(board.toStringWithCoordinates())
}
