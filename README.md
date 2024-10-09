**Chess Game Project**


**Overview**

This is a full-featured chess game application developed using Java and Spring Boot. The project implements standard chess rules, allowing users to play against each other with a responsive front-end interface.



**Features**

RESTful API for managing game state and logic.

Support for all chess pieces with specific movement rules.

Interactive user interface built with HTML, CSS, and JavaScript.

Piece movement and valid move validation.

Game reset functionality.



**Technologies Used**

Backend: Java, Spring Boot

Frontend: HTML, CSS, JavaScript



**Usage**

Start a game: Click on the designated area to begin.

Select a piece: Click on a piece to view its possible movements.

Move a piece: Click on a valid destination to move the selected piece.

Reset the game: Click the "Reset" button to clear the board and start a new game.





**API Endpoints**

**Valid Moves**

GET /api/valid-moves?row={row}&col={col}

Returns a list of valid moves for the piece located at the specified coordinates.

**Move Piece**

POST /api/move-piece

Moves a piece from the start coordinate to the end coordinate

**Reset Game**

POST /api/reset




Resets the game to its initial state.
