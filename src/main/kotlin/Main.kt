import java.util.Scanner

fun main() {
    welcomeMessage()
    val scanner = Scanner(System.`in`)
    val size = getBoardSize(scanner)
    val board = BoardFactory.setUpBoard(size)
    Interpreter(scanner, board, size).startGame()
}

private fun welcomeMessage() {
    println("Welcome to a game of noughts and crosses.")
    println("Players are: ${Player.values().toList()}")
}

private fun getBoardSize(scanner: Scanner): Int {
    println("Please enter the size of the board: (where size > 0)")
    return readAnInt(scanner, -1) { x -> x > 0 }
}
