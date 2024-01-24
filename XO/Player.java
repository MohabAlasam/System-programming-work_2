package XO;

public abstract class Player implements Runnable{
    
    
    PlayerType type;
    
    Player(PlayerType type){
        this.type = type;
    }
    
    public PlayerType getType() {
        return type;
    }
}
