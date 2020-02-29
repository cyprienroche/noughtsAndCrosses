import java.util.InputMismatchException
import java.util.Scanner

class Interpreter {

    companion object {
        fun setUpBoardInterpreter(): BoardInterpreter {
            val scanner: Scanner = Scanner(System.`in`)

            println("Welcome to a game of noughts and crosses.")
            println("Players are: ${Player.values().toList()}")
            var dim = 0
            while (dim < 1) {
                println("Please enter the dimension of the board: (where dim > 0)")
                try {
                    dim = scanner.nextInt()
                } catch (e: InputMismatchException) {
                    scanner.next()
                    println("dimension must be an integer")
                }
            }
            return SquareBoardInterpreter(scanner, dim)
        }
    }
}
