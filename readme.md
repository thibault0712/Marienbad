# Marienbad Game 
![workflow](https://github.com/Thibault0712/Marienbad/actions/workflows/main.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Tests](https://img.shields.io/badge/tests-passing-brightgreen.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

This project implements the famous Marienbad game in Java. It includes two versions: a two-player game and a player vs. bot game.
![Main Menu](https://github.com/Thibault0712/Marienbad/blob/main/pictures/home.png?raw=true)

## Authors

- Thibault FALEZAN
- Augustin LETANG

## Project Structure

The project consists of two main files:

1. `Marienbad.java`: Implements the two-player version of the game.
2. `MarienbadBot.java`: Implements the player vs. bot version of the game.

## Game Rules

Marienbad is a strategic game where players take turns removing sticks from different rows. The player who removes the last stick won the game.

## Features

- Interactive command-line interface
- Colorful ASCII art for the game title
- Dynamic game board display
- Input validation and error handling
- Unit tests for core game logic

## How to Play

1. Run the game (either `Marienbad.java` or `MarienbadBot.java`).
2. Choose to start the game or run unit tests.
3. Enter player names and game parameters as prompted.
4. Take turns removing sticks from the rows.
5. The game ends when a player removes the last stick.

## Game Modes

### Two-Player Mode (`Marienbad.java`)

- Two human players compete against each other.
- Players take turns removing sticks.

### Player vs. Bot Mode (`MarienbadBot.java`)

- One human player competes against a computer-controlled bot.
- The bot makes random moves.

## Implementation Details

- The game uses ANSI escape codes for colored output in the terminal.
- The game board is represented as an array of integers, where each element represents the number of sticks in a row.
- Input validation ensures that players make legal moves.

## Unit Tests

Both versions include unit tests for the following methods:

- `generateLinesContent()`: Tests the initial game board generation.
- `playerWon()`: Tests the win condition detection.

To run the tests, choose option 'b' from the main menu.

## Execution Screenshots

Here are some screenshots of the game in action:

1. Main Menu
   ![Main Menu](https://github.com/Thibault0712/Marienbad/blob/main/pictures/home.png?raw=true)

2. Player Turn
   ![Player Turn](https://github.com/Thibault0712/Marienbad/blob/main/pictures/playerTurn.png?raw=true)

3. Player Select Line
   ![Player Turn](https://github.com/Thibault0712/Marienbad/blob/main/pictures/playerSelectLine.png?raw=true)

## Acknowledgements

This project was created as part of a programming assignment to evaluate our skills at the IUT Informatique de Vannes.
