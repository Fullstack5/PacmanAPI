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

## Endpoints

### GET `/register-game`
Registers a new game with default parameters

#### Response
The response is the `GameRegistered` model containing the id of the game created.

##### Example
```json5
{
    "gameId": "abcd"
}
```

### POST `/register-game`
Registers a new game with optional parameters

#### Request
A JSON object needs to be passed that indicates which options should be set on the new game. All fields are optional.

- `stepDuration` - set how fast the game runs in duration per step, provide a integer or float (in seconds) or a string of the format `"PT0.2S"` (Default is 1)
- `ghostsRunner` - let the server play the ghosts, current options are `RANDOM` or `ASTAR` (Default is none)
- `pacmanRunner` - let the server play pacman, current option is `RANDOM` (Default is none)


##### Example
```json5
{
    "stepDuration": "PT0.5S",
    "pacmanRunner": "RANDOM",
    "ghostsRunner": "ASTAR"
}
```

#### Response
The response is the `GameRegistered` model containing the id of the game created.

##### Example
```json5
{
    "gameId": "abcd"
}
```

### POST `/register-player`
Registers a new player

#### Request
A JSON object is expected that indicates for which game and for which player type a new player needs to be registered.

- `gameId` - provide the `gameId` as replied from the `/register-game` endpoint (required)
- `type` - provide the type of the player, options are `GHOSTS` or `PACMAN` (required)

##### Example
```json5
{
    "gameId": "abcd",
    "type": "GHOSTS"
}
```

#### Response
The response is the `PlayerRegistered` model containing the `authId` needed to make moves and the maze specifications.

##### Example
```json5
{
    "authId": "loypjwlrcvlopsnirieejxgcpjthqkncxmalsgefvjrbkliibkqxabnrugpphwrm",
    "maze": {
        "walls": [ // there will be more wall columns forming a 2D grid of booleans
            [
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true
            ]
        ],
        "dots": [{ // there will be more dots
            "x": 1,
            "y": 1
        }],
        "powerPellets": [{
                "x": 1,
                "y": 2
            },
            {
                "x": 1,
                "y": 15
            },
            {
                "x": 17,
                "y": 2
            },
            {
                "x": 17,
                "y": 15
            }
        ],
        "pacmanSpawn": {
            "x": 9,
            "y": 15
        },
        "blinkySpawn": {
            "x": 9,
            "y": 7
        },
        "pinkySpawn": {
            "x": 9,
            "y": 9
        },
        "inkySpawn": {
            "x": 8,
            "y": 9
        },
        "clydeSpawn": {
            "x": 10,
            "y": 9
        },
        "width": 19,
        "height": 21,
        "maxScore": 150
    }
}
```

### POST `/current-state`
Flux endpoint that streams the current state of the game

#### Request
A JSON object needs to be passed that provides the gameId for which the status updates need to be sent

- `gameId` - provide the `gameId` as replied from the `/register-game` endpoint (required)

##### Example
```json5
{
  "gameId": "abcd"
}
```

#### Response
The response is a stream of the `GameState` model containing all the information of the current state of the game.

##### Example
```json5
{
    "time": 3,
    "result": null,
    "remainingDots": [{ // there will be more dots
        "x": 1,
        "y": 1
    }],
    "remainingPellets": [{
        "x": 1,
        "y": 2
    }, {
        "x": 1,
        "y": 15
    }, {
        "x": 17,
        "y": 2
    }, {
        "x": 17,
        "y": 15
    }],
    "pacman": {
        "oldPosition": {
            "x": 8,
            "y": 15
        },
        "currentPosition": {
            "x": 7,
            "y": 15
        },
        "direction": "WEST",
        "vulnerable": false,
        "active": true
    },
    "blinky": {
        "oldPosition": {
            "x": 8,
            "y": 7
        },
        "currentPosition": {
            "x": 7,
            "y": 7
        },
        "direction": "WEST",
        "vulnerable": false,
        "active": true
    },
    "pinky": {
        "oldPosition": null,
        "currentPosition": {
            "x": 9,
            "y": 9
        },
        "direction": "NORTH",
        "vulnerable": false,
        "active": false
    },
    "inky": {
        "oldPosition": null,
        "currentPosition": {
            "x": 8,
            "y": 9
        },
        "direction": "EAST",
        "vulnerable": false,
        "active": false
    },
    "clyde": {
        "oldPosition": null,
        "currentPosition": {
            "x": 10,
            "y": 9
        },
        "direction": "WEST",
        "vulnerable": false,
        "active": false
    },
    "pacmanScore": 2,
    "ghostsScore": 148
}
```

### POST `/perform-move`
Perform a move for one of the pieces in the game

#### Request
A JSON object needs to be passed that indicates which piece in which game needs to be moved in which direction.

- `gameId` - as replied from the `/register-game` endpoint (required)
- `authId` - as provided from the `/register-player` endpoint (required)
- `direction` - the direction the piece should move in, this can be `NORTH`, `WEST`, `SOUTH` or `EAST` (required)
- `type` - provide the piece you want to move this can be `PACMAN`, `INKY`, `BLINKY`, `PINKY` or `CLYDE`, the authId will need to match the a player that can control the piece (required)

##### Example
```json5
{
    "gameId": "abcd",
    "authId": "loypjwlrcvlopsnirieejxgcpjthqkncxmalsgefvjrbkliibkqxabnrugpphwrm",
    "direction": "WEST",
    "type": "BLINKY"
}
```

#### Response
The response empty.

