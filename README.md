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
```json
{
  "gameId": "abcd"
}
```

### POST `/register-game`
Registers a new game with optional parameters

#### Request
A JSON object needs to be passed that indicates which options should be set on the new game. All fields are optional.

- `stepDuration` - set how fast the game runs in duration per step, provide a integer or float (in seconds) or a string of the format `"PT0.2S"` (Default is 1)
- `ghostsRunner` - let the server play the ghosts, current options are `RANDOM` or `ASTAR` (Default is empty)
- `pacmanRunner` - let the server play pacman, current option is `RANDOM` (Default is none)


##### Example
```json
{
  "stepDuration": "PT0.5S",
  "pacmanRunner": "RANDOM",
  "ghostsRunner": "ASTAR"
}
```

#### Response
The response is the `GameRegistered` model containing the id of the game created.

##### Example
```json
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
```json
{
  "gameId": "abcd",
  "type": "GHOSTS"
}
```

#### Response
The response is the `PlayerRegistered` model containing the `authId` needed to make moves and the maze specifications.

##### Example
```json
{
    "authId": "loypjwlrcvlopsnirieejxgcpjthqkncxmalsgefvjrbkliibkqxabnrugpphwrm",
    "maze": {
        "walls": [
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
            ],
            [
                true,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                true,
                true,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                false,
                true,
                true,
                true,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true
            ],
            [
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                false,
                false,
                false,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                false,
                true
            ],
            [
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                true,
                true,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                false,
                true,
                true,
                true,
                false,
                true
            ],
            [
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                true
            ],
            [
                true,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true
            ],
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
        "dots": [
            {
                "x": 1,
                "y": 1
            },
            {
                "x": 1,
                "y": 3
            },
            {
                "x": 1,
                "y": 4
            },
            {
                "x": 1,
                "y": 5
            },
            {
                "x": 1,
                "y": 13
            },
            {
                "x": 1,
                "y": 14
            },
            {
                "x": 1,
                "y": 17
            },
            {
                "x": 1,
                "y": 18
            },
            {
                "x": 1,
                "y": 19
            },
            {
                "x": 2,
                "y": 1
            },
            {
                "x": 2,
                "y": 3
            },
            {
                "x": 2,
                "y": 5
            },
            {
                "x": 2,
                "y": 13
            },
            {
                "x": 2,
                "y": 15
            },
            {
                "x": 2,
                "y": 16
            },
            {
                "x": 2,
                "y": 17
            },
            {
                "x": 2,
                "y": 19
            },
            {
                "x": 3,
                "y": 1
            },
            {
                "x": 3,
                "y": 3
            },
            {
                "x": 3,
                "y": 5
            },
            {
                "x": 3,
                "y": 13
            },
            {
                "x": 3,
                "y": 17
            },
            {
                "x": 3,
                "y": 19
            },
            {
                "x": 4,
                "y": 1
            },
            {
                "x": 4,
                "y": 2
            },
            {
                "x": 4,
                "y": 3
            },
            {
                "x": 4,
                "y": 4
            },
            {
                "x": 4,
                "y": 5
            },
            {
                "x": 4,
                "y": 6
            },
            {
                "x": 4,
                "y": 7
            },
            {
                "x": 4,
                "y": 8
            },
            {
                "x": 4,
                "y": 9
            },
            {
                "x": 4,
                "y": 10
            },
            {
                "x": 4,
                "y": 11
            },
            {
                "x": 4,
                "y": 12
            },
            {
                "x": 4,
                "y": 13
            },
            {
                "x": 4,
                "y": 14
            },
            {
                "x": 4,
                "y": 15
            },
            {
                "x": 4,
                "y": 16
            },
            {
                "x": 4,
                "y": 17
            },
            {
                "x": 4,
                "y": 19
            },
            {
                "x": 5,
                "y": 1
            },
            {
                "x": 5,
                "y": 3
            },
            {
                "x": 5,
                "y": 13
            },
            {
                "x": 5,
                "y": 15
            },
            {
                "x": 5,
                "y": 19
            },
            {
                "x": 6,
                "y": 1
            },
            {
                "x": 6,
                "y": 3
            },
            {
                "x": 6,
                "y": 4
            },
            {
                "x": 6,
                "y": 5
            },
            {
                "x": 6,
                "y": 13
            },
            {
                "x": 6,
                "y": 15
            },
            {
                "x": 6,
                "y": 16
            },
            {
                "x": 6,
                "y": 17
            },
            {
                "x": 6,
                "y": 19
            },
            {
                "x": 7,
                "y": 1
            },
            {
                "x": 7,
                "y": 3
            },
            {
                "x": 7,
                "y": 5
            },
            {
                "x": 7,
                "y": 13
            },
            {
                "x": 7,
                "y": 15
            },
            {
                "x": 7,
                "y": 17
            },
            {
                "x": 7,
                "y": 19
            },
            {
                "x": 8,
                "y": 1
            },
            {
                "x": 8,
                "y": 2
            },
            {
                "x": 8,
                "y": 3
            },
            {
                "x": 8,
                "y": 5
            },
            {
                "x": 8,
                "y": 13
            },
            {
                "x": 8,
                "y": 14
            },
            {
                "x": 8,
                "y": 15
            },
            {
                "x": 8,
                "y": 17
            },
            {
                "x": 8,
                "y": 18
            },
            {
                "x": 8,
                "y": 19
            },
            {
                "x": 9,
                "y": 3
            },
            {
                "x": 9,
                "y": 19
            },
            {
                "x": 10,
                "y": 1
            },
            {
                "x": 10,
                "y": 2
            },
            {
                "x": 10,
                "y": 3
            },
            {
                "x": 10,
                "y": 5
            },
            {
                "x": 10,
                "y": 13
            },
            {
                "x": 10,
                "y": 14
            },
            {
                "x": 10,
                "y": 15
            },
            {
                "x": 10,
                "y": 17
            },
            {
                "x": 10,
                "y": 18
            },
            {
                "x": 10,
                "y": 19
            },
            {
                "x": 11,
                "y": 1
            },
            {
                "x": 11,
                "y": 3
            },
            {
                "x": 11,
                "y": 5
            },
            {
                "x": 11,
                "y": 13
            },
            {
                "x": 11,
                "y": 15
            },
            {
                "x": 11,
                "y": 17
            },
            {
                "x": 11,
                "y": 19
            },
            {
                "x": 12,
                "y": 1
            },
            {
                "x": 12,
                "y": 3
            },
            {
                "x": 12,
                "y": 4
            },
            {
                "x": 12,
                "y": 5
            },
            {
                "x": 12,
                "y": 13
            },
            {
                "x": 12,
                "y": 15
            },
            {
                "x": 12,
                "y": 16
            },
            {
                "x": 12,
                "y": 17
            },
            {
                "x": 12,
                "y": 19
            },
            {
                "x": 13,
                "y": 1
            },
            {
                "x": 13,
                "y": 3
            },
            {
                "x": 13,
                "y": 13
            },
            {
                "x": 13,
                "y": 15
            },
            {
                "x": 13,
                "y": 19
            },
            {
                "x": 14,
                "y": 1
            },
            {
                "x": 14,
                "y": 2
            },
            {
                "x": 14,
                "y": 3
            },
            {
                "x": 14,
                "y": 4
            },
            {
                "x": 14,
                "y": 5
            },
            {
                "x": 14,
                "y": 6
            },
            {
                "x": 14,
                "y": 7
            },
            {
                "x": 14,
                "y": 8
            },
            {
                "x": 14,
                "y": 9
            },
            {
                "x": 14,
                "y": 10
            },
            {
                "x": 14,
                "y": 11
            },
            {
                "x": 14,
                "y": 12
            },
            {
                "x": 14,
                "y": 13
            },
            {
                "x": 14,
                "y": 14
            },
            {
                "x": 14,
                "y": 15
            },
            {
                "x": 14,
                "y": 16
            },
            {
                "x": 14,
                "y": 17
            },
            {
                "x": 14,
                "y": 19
            },
            {
                "x": 15,
                "y": 1
            },
            {
                "x": 15,
                "y": 3
            },
            {
                "x": 15,
                "y": 5
            },
            {
                "x": 15,
                "y": 13
            },
            {
                "x": 15,
                "y": 17
            },
            {
                "x": 15,
                "y": 19
            },
            {
                "x": 16,
                "y": 1
            },
            {
                "x": 16,
                "y": 3
            },
            {
                "x": 16,
                "y": 5
            },
            {
                "x": 16,
                "y": 13
            },
            {
                "x": 16,
                "y": 15
            },
            {
                "x": 16,
                "y": 16
            },
            {
                "x": 16,
                "y": 17
            },
            {
                "x": 16,
                "y": 19
            },
            {
                "x": 17,
                "y": 1
            },
            {
                "x": 17,
                "y": 3
            },
            {
                "x": 17,
                "y": 4
            },
            {
                "x": 17,
                "y": 5
            },
            {
                "x": 17,
                "y": 13
            },
            {
                "x": 17,
                "y": 14
            },
            {
                "x": 17,
                "y": 17
            },
            {
                "x": 17,
                "y": 18
            },
            {
                "x": 17,
                "y": 19
            }
        ],
        "powerPellets": [
            {
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
```json
{
  "gameId": "abcd"
}
```

#### Response
The response is a stream of the `GameState` model containing all the information of the current state of the game.

##### Example
```json
{
	"time": 3,
	"result": null,
	"remainingDots": [{
		"x": 1,
		"y": 1
	}, {
		"x": 1,
		"y": 3
	}, {
		"x": 1,
		"y": 4
	}, {
		"x": 1,
		"y": 5
	}, {
		"x": 1,
		"y": 13
	}, {
		"x": 1,
		"y": 14
	}, {
		"x": 1,
		"y": 17
	}, {
		"x": 1,
		"y": 18
	}, {
		"x": 1,
		"y": 19
	}, {
		"x": 2,
		"y": 1
	}, {
		"x": 2,
		"y": 3
	}, {
		"x": 2,
		"y": 5
	}, {
		"x": 2,
		"y": 13
	}, {
		"x": 2,
		"y": 15
	}, {
		"x": 2,
		"y": 16
	}, {
		"x": 2,
		"y": 17
	}, {
		"x": 2,
		"y": 19
	}, {
		"x": 3,
		"y": 1
	}, {
		"x": 3,
		"y": 3
	}, {
		"x": 3,
		"y": 5
	}, {
		"x": 3,
		"y": 13
	}, {
		"x": 3,
		"y": 17
	}, {
		"x": 3,
		"y": 19
	}, {
		"x": 4,
		"y": 1
	}, {
		"x": 4,
		"y": 2
	}, {
		"x": 4,
		"y": 3
	}, {
		"x": 4,
		"y": 4
	}, {
		"x": 4,
		"y": 5
	}, {
		"x": 4,
		"y": 6
	}, {
		"x": 4,
		"y": 7
	}, {
		"x": 4,
		"y": 8
	}, {
		"x": 4,
		"y": 9
	}, {
		"x": 4,
		"y": 10
	}, {
		"x": 4,
		"y": 11
	}, {
		"x": 4,
		"y": 12
	}, {
		"x": 4,
		"y": 13
	}, {
		"x": 4,
		"y": 14
	}, {
		"x": 4,
		"y": 15
	}, {
		"x": 4,
		"y": 16
	}, {
		"x": 4,
		"y": 17
	}, {
		"x": 4,
		"y": 19
	}, {
		"x": 5,
		"y": 1
	}, {
		"x": 5,
		"y": 3
	}, {
		"x": 5,
		"y": 13
	}, {
		"x": 5,
		"y": 15
	}, {
		"x": 5,
		"y": 19
	}, {
		"x": 6,
		"y": 1
	}, {
		"x": 6,
		"y": 3
	}, {
		"x": 6,
		"y": 4
	}, {
		"x": 6,
		"y": 5
	}, {
		"x": 6,
		"y": 13
	}, {
		"x": 6,
		"y": 15
	}, {
		"x": 6,
		"y": 16
	}, {
		"x": 6,
		"y": 17
	}, {
		"x": 6,
		"y": 19
	}, {
		"x": 7,
		"y": 1
	}, {
		"x": 7,
		"y": 3
	}, {
		"x": 7,
		"y": 5
	}, {
		"x": 7,
		"y": 13
	}, {
		"x": 7,
		"y": 17
	}, {
		"x": 7,
		"y": 19
	}, {
		"x": 8,
		"y": 1
	}, {
		"x": 8,
		"y": 2
	}, {
		"x": 8,
		"y": 3
	}, {
		"x": 8,
		"y": 5
	}, {
		"x": 8,
		"y": 13
	}, {
		"x": 8,
		"y": 14
	}, {
		"x": 8,
		"y": 17
	}, {
		"x": 8,
		"y": 18
	}, {
		"x": 8,
		"y": 19
	}, {
		"x": 9,
		"y": 3
	}, {
		"x": 9,
		"y": 19
	}, {
		"x": 10,
		"y": 1
	}, {
		"x": 10,
		"y": 2
	}, {
		"x": 10,
		"y": 3
	}, {
		"x": 10,
		"y": 5
	}, {
		"x": 10,
		"y": 13
	}, {
		"x": 10,
		"y": 14
	}, {
		"x": 10,
		"y": 15
	}, {
		"x": 10,
		"y": 17
	}, {
		"x": 10,
		"y": 18
	}, {
		"x": 10,
		"y": 19
	}, {
		"x": 11,
		"y": 1
	}, {
		"x": 11,
		"y": 3
	}, {
		"x": 11,
		"y": 5
	}, {
		"x": 11,
		"y": 13
	}, {
		"x": 11,
		"y": 15
	}, {
		"x": 11,
		"y": 17
	}, {
		"x": 11,
		"y": 19
	}, {
		"x": 12,
		"y": 1
	}, {
		"x": 12,
		"y": 3
	}, {
		"x": 12,
		"y": 4
	}, {
		"x": 12,
		"y": 5
	}, {
		"x": 12,
		"y": 13
	}, {
		"x": 12,
		"y": 15
	}, {
		"x": 12,
		"y": 16
	}, {
		"x": 12,
		"y": 17
	}, {
		"x": 12,
		"y": 19
	}, {
		"x": 13,
		"y": 1
	}, {
		"x": 13,
		"y": 3
	}, {
		"x": 13,
		"y": 13
	}, {
		"x": 13,
		"y": 15
	}, {
		"x": 13,
		"y": 19
	}, {
		"x": 14,
		"y": 1
	}, {
		"x": 14,
		"y": 2
	}, {
		"x": 14,
		"y": 3
	}, {
		"x": 14,
		"y": 4
	}, {
		"x": 14,
		"y": 5
	}, {
		"x": 14,
		"y": 6
	}, {
		"x": 14,
		"y": 7
	}, {
		"x": 14,
		"y": 8
	}, {
		"x": 14,
		"y": 9
	}, {
		"x": 14,
		"y": 10
	}, {
		"x": 14,
		"y": 11
	}, {
		"x": 14,
		"y": 12
	}, {
		"x": 14,
		"y": 13
	}, {
		"x": 14,
		"y": 14
	}, {
		"x": 14,
		"y": 15
	}, {
		"x": 14,
		"y": 16
	}, {
		"x": 14,
		"y": 17
	}, {
		"x": 14,
		"y": 19
	}, {
		"x": 15,
		"y": 1
	}, {
		"x": 15,
		"y": 3
	}, {
		"x": 15,
		"y": 5
	}, {
		"x": 15,
		"y": 13
	}, {
		"x": 15,
		"y": 17
	}, {
		"x": 15,
		"y": 19
	}, {
		"x": 16,
		"y": 1
	}, {
		"x": 16,
		"y": 3
	}, {
		"x": 16,
		"y": 5
	}, {
		"x": 16,
		"y": 13
	}, {
		"x": 16,
		"y": 15
	}, {
		"x": 16,
		"y": 16
	}, {
		"x": 16,
		"y": 17
	}, {
		"x": 16,
		"y": 19
	}, {
		"x": 17,
		"y": 1
	}, {
		"x": 17,
		"y": 3
	}, {
		"x": 17,
		"y": 4
	}, {
		"x": 17,
		"y": 5
	}, {
		"x": 17,
		"y": 13
	}, {
		"x": 17,
		"y": 14
	}, {
		"x": 17,
		"y": 17
	}, {
		"x": 17,
		"y": 18
	}, {
		"x": 17,
		"y": 19
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
```json
{
	"gameId": "abcd",
    "authId": "loypjwlrcvlopsnirieejxgcpjthqkncxmalsgefvjrbkliibkqxabnrugpphwrm",
	"direction": "WEST",
	"type": "BLINKY"
}
```

#### Response
The response empty.

