/*
* Note to the reader:
* The Board interface is here for two reasons:
* First, the original problem was just to return the winner player.
* A class implementing a Board might have more functions, but those below are what I consider as essential.
* Second, I was thinking of adding different kinds of Board, for example Rectangular Boards.
* Having an interface makes things easier in the interpreter.
*/
interface Board {
    // return the winning Player if there is a winner, null otherwise
    fun winner(): Player?
    fun isGameOver(): Boolean
    fun place(cell: Cell, p: Position): Unit
}
