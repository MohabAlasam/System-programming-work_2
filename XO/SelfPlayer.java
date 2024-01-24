package XO;

//מואב אלחרם 315059253
//סארה אלחמידי 213006018
public class SelfPlayer extends Player {

    Game game;

    SelfPlayer(PlayerType type, Game game) {
        super(type);
        this.game = game;
    }

    @Override
    public void run() {
        while (!game.isGameOver().get()) {
            if (game.getTurn() == type) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.playSelfTurn(this.type);
            }
        }
    }

}
