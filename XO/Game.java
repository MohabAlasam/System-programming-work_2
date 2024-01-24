package XO;

//מואב אלחרם 315059253
//סארה אלחמידי 213006018
import java.util.concurrent.atomic.AtomicBoolean;

abstract class Game {

    protected PlayerType[][] gameBoard = new PlayerType[5][5];
    protected AtomicBoolean gameOver = new AtomicBoolean(false);
    protected PlayerType currentTurn;

    public synchronized PlayerType getTurn() {
        return currentTurn;
    }

    public synchronized AtomicBoolean isGameOver() {
        return gameOver;
    }

    //print the board
    public void printBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == null)
                    System.out.print("_ ");
                else
                    System.out.print(gameBoard[i][j].toString() + " ");
            }
            System.out.println();
        }
        System.out.println("________________");
    }

    //returns an array of type Cell of all the empty cells in the board, each cell contains values of its row and column
    public Cell[] getFreeCells() {
        Cell[] freeCells;
        int arrLen = 0, loc = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == null)
                    arrLen++;
            }
        }
        freeCells = new Cell[arrLen];

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == null) {
                    freeCells[loc++] = new Cell(i, j);
                }
            }
        }

        return freeCells;
    }


    /* play the turn in the given row and column, and check if the game ends */
    public void playTurn(PlayerType type, int cellX, int cellY) {
        gameBoard[cellY][cellX] = type;
        printBoard();
        System.out.println(type.toString() + " played " + cellY + ',' + cellX);
        System.out.println();
        if (checkIfGameEnds()) {
            gameOver.set(true);
        } else if (type == PlayerType.O)
            currentTurn = PlayerType.X;
        else
            currentTurn = PlayerType.O;
    }

    //play self turn for self threads only
    public synchronized void playSelfTurn(PlayerType playerType) {
            Cell[] freeCells = getFreeCells();
            if (freeCells.length > 0) {
                int random = (int) Math.floor(Math.random() * freeCells.length);
                playTurn(playerType, freeCells[random].getX(), freeCells[random].getY());
            }
        
    }

    //if there is a winner, print him and return true, else return false
    public boolean checkIfGameEnds() {

        int count = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            count = 0;
            for (int j = 0; j < gameBoard[i].length - 1; j++) {
                if (gameBoard[i][j] != null && gameBoard[i][j] == gameBoard[i][j + 1])
                    count++;
                else
                    count = 0;
                if (count == 3) {
                    System.out.println(gameBoard[i][j].toString() + " Wins!" );
                    return true;
                }
            }
        }
        for (int j = 0; j < gameBoard[0].length; j++) {
            count = 0;
            for (int i = 0; i < gameBoard.length - 1; i++) {
                if (gameBoard[i][j] != null && gameBoard[i][j] == gameBoard[i + 1][j])
                    count++;
                else
                    count = 0;
                if (count == 3) {
                    System.out.println(gameBoard[i][j].toString() + " Wins!");
                    return true;
                }
            }
        }
        if (gameBoard[0][0] != null && gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2]
                && gameBoard[0][0] == gameBoard[3][3]) {
            System.out.println(gameBoard[0][0].toString() + " Wins!");
            return true;
        }
        if (gameBoard[1][1] != null && gameBoard[1][1] == gameBoard[2][2] && gameBoard[1][1] == gameBoard[3][3]
                && gameBoard[1][1] == gameBoard[4][4]) {
            System.out.println(gameBoard[1][1].toString() + " Wins!");
            return true;
        }
        if (gameBoard[1][0] != null && gameBoard[1][0] == gameBoard[2][1] && gameBoard[1][0] == gameBoard[3][2]
                && gameBoard[1][0] == gameBoard[4][3]) {
            System.out.println(gameBoard[1][0].toString() + " Wins!");
            return true;
        }
        if (gameBoard[0][1] != null && gameBoard[0][1] == gameBoard[1][2] && gameBoard[0][1] == gameBoard[2][3]
                && gameBoard[0][1] == gameBoard[3][4]) {
            System.out.println(gameBoard[0][1].toString() + " Wins!");
            return true;
        }
        if (gameBoard[0][4] != null && gameBoard[0][4] == gameBoard[1][3] && gameBoard[0][4] == gameBoard[2][2]
                && gameBoard[0][4] == gameBoard[3][1]) {
            System.out.println(gameBoard[0][4].toString() + " Wins!");
            return true;
        }
        if (gameBoard[1][3] != null && gameBoard[1][3] == gameBoard[2][2] && gameBoard[1][3] == gameBoard[3][1]
                && gameBoard[1][3] == gameBoard[4][0]) {
            System.out.println(gameBoard[1][3].toString() + " Wins!");
            return true;
        }
        if (gameBoard[1][4] != null && gameBoard[1][4] == gameBoard[2][3] && gameBoard[1][4] == gameBoard[3][2]
                && gameBoard[1][4] == gameBoard[4][1]) {
            System.out.println(gameBoard[1][4].toString() + " Wins!");
            return true;
        }
        if (gameBoard[0][3] != null && gameBoard[0][3] == gameBoard[1][2] && gameBoard[0][3] == gameBoard[2][1]
                && gameBoard[0][3] == gameBoard[3][0]) {
            System.out.println(gameBoard[0][3].toString() + " Wins!");
            return true;
        }
        if (getFreeCells().length == 0) {
            System.out.println("Draw!");
            return true;
        }
        return false;
    }
}
