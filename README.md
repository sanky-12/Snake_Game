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
- [Contributing](#contributing)
- [License](#license)

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
