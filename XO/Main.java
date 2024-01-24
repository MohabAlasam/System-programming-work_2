package XO;

//מואב אלחרם 315059253
//סארה אלחמידי 213006018

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {


        ///////self game///////////
       /*  SelfGame game = new SelfGame();
        SelfPlayer p1 = new SelfPlayer(PlayerType.X, game);
        SelfPlayer p2 = new SelfPlayer(PlayerType.O, game);
        game.startGame(p1, p2); */

        ///////User game///////////
        PlayerType userType = PlayerType.X;
        PlayerType pcType = PlayerType.O;
        UserGame uGame = new UserGame();
        
        Scanner input = new Scanner(System.in);
        String choice;
        do {
            System.err.println("Choose:\nO\nX");
            choice = input.nextLine();
        } while (!choice.equalsIgnoreCase("X")  && !choice.equalsIgnoreCase("O"));
        
        if(choice.equalsIgnoreCase("X")){
            userType = PlayerType.X;
            pcType = PlayerType.O;
        }
        else if(choice.equalsIgnoreCase("O")){
            userType = PlayerType.O;
            pcType = PlayerType.X;
        }
        UserPlayer p1 = new UserPlayer(userType, uGame);
        SelfPlayer p2 = new SelfPlayer(pcType, uGame);
        uGame.startGame(p1, p2);
    }

}
