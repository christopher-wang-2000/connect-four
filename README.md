Connect Four (Created 2021 October 11)

Author: Christopher Wang (christopher.wang@wustl.edu)

This program runs a text-based user-playable Connect Four game, including:
- A two-player mode
- A one-player mode against a recursive AI opponent

The user can customize aspects of the game such as game board dimensions and the number of pieces in a row needed to win.

The following .java files are found in word-search-generator/src/wordsearch:
- ConnectFour.java serves as the entry point to the program and contains the code for setting up the game, reading user input during each turn, and determining when a player has won.
- Board.java contains the code that stores and displays the game board layout, adds pieces to the board, and checks how many pieces in a row a player has achieved.
- Opponent.java contains algorithms for an AI opponent that simulates future turns to determine which moves are the most optimal.
- Messages.java stores all the prompt messages and error messages that are displayed to the user.

How the game works:
- The program asks the user whether to play one-player against the computer-controlled opponent or two-player.
- The program also asks the user whether they want to customize the settings. If so, it asks for the board width and height, symbols to represent each player's game pieces, and the number of pieces in a row needed for victory. (Default settings: 7x7 grid, X and O, 4 in a row.)
- Each turn, the program prompts the user for a column to add their piece to. If the column is full or invalid, the program re-prompts the user.
- After each move, the program updates and prints the board and checks whether the piece that has just been added has resulted in a victory (4 in a row). If so, it prints a victory message for the winning player.
- If the board is full and nobody has won, the game ends in a draw.
