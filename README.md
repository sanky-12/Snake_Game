# Snake Game in Java

![Snake Game](path_to_your_game_screenshot.png)

A classic Snake Game implemented in Java using `Swing` for the graphical interface. The game allows the player to control a snake on the screen, where the objective is to eat food to grow longer without colliding with itself. The snake wraps around the edges of the screen, entering from the opposite side when it exits through any border.

## Table of Contents
- [Features](#features)
- [Game Rules](#game-rules)
- [Getting Started](#getting-started)
- [Running the Game](#running-the-game)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [Technologies Used](#technologies-used)

## Features
- Classic snake game mechanics
- The snake grows in length every time it eats food
- The game wraps around the screen, allowing the snake to exit one side and reappear on the opposite side
- Score tracking for the current game session
- High score tracking that persists during the game session
- Simple keyboard controls (arrow keys to move)
- Custom snake graphics

## Game Rules
1. **Movement**: Use the arrow keys (`Up`, `Down`, `Left`, `Right`) to control the snake.
2. **Objective**: The goal is to eat as much food as possible. Each time the snake eats food, it grows longer, and the player's score increases.
3. **Game Over**: The game ends when the snake collides with itself.
4. **Screen Wrapping**: If the snake exits through the left, it will reappear on the right side, and vice versa. Similarly, exiting through the top makes it re-enter from the bottom.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA (or any other Java IDE)
- Basic knowledge of Java

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/snake-game-java.git
   cd snake-game-java
2. Open the project in IntelliJ IDEA (or any other IDE).
3. Ensure your Java SDK is properly configured in your IDE.
4. Run the Game.java file to start the game.

## Running the Game

1. Compile and run the project using the IDE's built-in run configurations or use the following command in the terminal if you're using javac:
    ```bash
    javac -d bin src/org/example/*.java
    java -cp bin org.example.Game
2. Use the arrow keys (Up, Down, Left, Right) to move the snake.
3. The snake will grow each time it eats food, and the score will increase.

## Project Structure
- The project structure follows a simple MVC-like pattern:
    ```scss
    ├── src
    │   └── org
    │       └── example
    │           ├── Game.java        // Main class that runs the game
    │           ├── Snake.java       // Snake object and logic
    │           ├── Food.java        // Food object and logic
    │           └── GamePanel.java   // Handles rendering and game updates
    └── resources
        └── images                  // Image assets for the snake graphics

### Key Classes
- Game.java: The entry point of the game, initializes the game window and starts the game loop.
- Snake.java: Represents the snake's body and movement logic. Also handles collision detection and screen wrapping.
- Food.java: Represents the food that the snake consumes.
- GamePanel.java: Handles all graphical rendering and game updates on the screen.

## Screenshots

## Technologies Used
- Java: Main programming language
- Swing: For the graphical user interface
- IntelliJ IDEA: For development and running the project