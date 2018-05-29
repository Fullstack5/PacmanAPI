# PacmanAPI
API for Pacman chapter challenge


## Clone the application

https://github.com/Fullstack5/PacmanAPI

## Install the application

First, make sure you have all the dependencies installed. Run the following command to install all the application dependencies locally

```
maven clean install
```

After all the dependencies are installed

```
run main in PacmanApiApplication class
```

Endpoints: 

/register-game : register a new game
/register-player: register a new player
/perform-move: perform a move by sending authId, direction and type of the character
/current-state: Flux endpoint that will stream the current state of the game

