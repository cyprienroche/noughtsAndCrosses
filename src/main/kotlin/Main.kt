
fun main() {
    val board = Board.squareBoard(2)
    board.place(0, Taken(Player.X))
    board.place(1, Taken(Player.O))
    board.place(2, Taken(Player.X))
    println(board)
}
