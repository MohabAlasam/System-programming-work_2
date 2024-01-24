package XO;

public class SelfGame extends Game {

    /* private SelfPlayer p1;
    private SelfPlayer p2;*/
    private Thread t1, t2; 
    
    SelfGame() {
        /* p1 = new SelfPlayer(PlayerType.X);
        p2 = new SelfPlayer(PlayerType.O); */
        currentTurn = PlayerType.X;
    }

    public void startGame(SelfPlayer p1, SelfPlayer p2) {
        t1 = new Thread(p1);
        t2 = new Thread(p2);
        t1.setName("X");
        t2.setName("O");
        t1.start();
        t2.start();
    }
}
