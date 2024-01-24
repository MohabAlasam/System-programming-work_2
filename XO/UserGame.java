package XO;

//מואב אלחרם 315059253
//סארה אלחמידי 213006018
import java.util.Scanner;

public class UserGame extends Game {

    private Thread t1, t2;

    UserGame() {
        currentTurn = PlayerType.X;
    }

    public void startGame(UserPlayer p1, SelfPlayer p2) {
        t1 = new Thread(p1);
        t2 = new Thread(p2);
        t1.setName("X");
        t2.setName("O");
        t1.start();
        t2.start();
    }
    //if the given values are valid then play the turn
    public synchronized void playUserTurn(PlayerType playerType) {

        Cell[] freeCells = getFreeCells();
        int i =0;
        if (freeCells.length > 0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the row you want to play " + playerType.toString());
            int row = sc.nextInt();
            System.out.println("Enter the column you want to play " + playerType.toString());
            int col = sc.nextInt();
            for (i = 0; i < freeCells.length; i++) {
                if (freeCells[i].getX() == col && freeCells[i].getY() == row) {
                    playTurn(playerType, col, row);
                    break;
                }
            }
        }
        //if the given values are not found in freeCells
         if(i == freeCells.length){
            System.out.println("Invalid input please try again!");
         }
        
    }
}
