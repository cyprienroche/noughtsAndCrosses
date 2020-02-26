
fun main() {
    val board = squareBoard(2)
    board.replace(0, Taken(0, Player.X))
    board.replace(1, Taken(1, Player.O))
    board.replace(2, Taken(2, Player.X))
    println(board)
}
