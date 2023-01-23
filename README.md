# Maze-Game
Small graphical Pac-Man style Maze game.

### Prerequisites

* Java 8 or higher.


### Steps to Play the Game

#### Clone the repository 
    git clone https://github.com/eminsakk/Maze-Game
    cd src
#### Compile and Run
    javac *.java
    java Main ../data/map1.dat

## Game Details

### Enemy
* Enemies have health and get damaged by bullets. Moves horizontally, vertically or does not
move at all. (depending on the strategy)
* **Health**: 100 units.
* **Movement Speed**: 120 pixels per second.

### Player
* Players do not have health they immediately die if they collide with an enemy. Moves using
player input.
* **Movement Speed**: 110 pixels per second.

### Wall 
* Walls don’t do much. They never die, only there to collide with others.

### Bullet
* Bullets are created by the player when the fire key is pressed. They move toward the last moved
direction of the player. Bullets die if their time is ended.
* **Bullet Life**: 0.7 seconds.
* **Bullet Speed**: 300 pixels per second.

### PowerUps
* Power-ups are just there to get collected. They die when it collides with the player.


## Key Notes on Implementation Details
* GameWindow Class is implemented using  Singeton Design Pattern.
* AbstractActor Class is implemented using Decorator Design Pattern.
* Collision Component Class is implemented using Observer Design Pattern.
* AbstractPatrolStrategy Class is implemented using Strategy Design Pattern.

