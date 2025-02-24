Rock Paper Scissors Game in C

Description

This is a simple Rock Paper Scissors game implemented in C. The game reads inputs from a file, processes the moves, and determines the winner based on traditional Rock Paper Scissors rules.

Features

Reads player moves from an input file

Determines the winner for each round

Supports multiple rounds

Displays the final result after all rounds

Requirements

GCC Compiler (or any C compiler)

C Standard Library

How to Compile

gcc -o rps_game rps_game.c

How to Run

./rps_game input.txt

Input File Format

Each line in the input file represents a round.

The format is: Player1_Move Player2_Move

Moves can be rock, paper, or scissors (case-insensitive).

Example Input (input-1.txt):

rock paper
rock scissors
paper paper
scissors paper
paper rock

Output Example

Player B wins
Player A wins
Players tie
Player A wins
Player A wins
+--------+-------+
| A Wins |     3 |
+--------+-------+
| B Wins |     1 |
+--------+-------+
| Ties   |     1 |
+--------+-------+

Rules

Rock beats Scissors

Scissors beats Paper

Paper beats Rock

Same moves result in a draw