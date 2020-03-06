[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-yellow.svg)](https://www.gnu.org/licenses/gpl-3.0)

The Board interface is here for two reasons:
* First, the original problem was just to return the winner player.
A class implementing a Board might have more functions, 
but those in the Board interface are what I consider as essential to a Board.
* Second, I was thinking of adding different kinds of Board, for example Rectangular Boards.
Having an interface makes things easier in the interpreter, which uses the Strategy design pattern. 

The Board Factory only creates SquareBoards for now. As mentioned above, it is there to handle more types of Boards.

Adding an enum to the Player enum should be possible and the game should update automatically. 

Position (0, 0), the origin, is taken to be the top-left corner of the screen.
If in doubt, create a SquareBoard explicitly and use the toStringWithCoordinates function. 

### License
[GPL v3](https://www.gnu.org/licenses/gpl-3.0)
