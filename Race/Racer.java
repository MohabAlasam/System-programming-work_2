package Race;

//מואב אלחרם 315059253
//סארה אלחמידי 213006018
public class Racer implements Runnable{

    private static int globalId = 1;
    private final int id;
    private int speed;
    private Track track;
    private String finishPosition;

    Racer(int speed, Track track){
        id = globalId;
        globalId++;
        this.track = track;
        try {
            if( speed > 10 || speed < 1) throw new Exception("Error: speed value is invalid!");
            this.speed = speed;
        } catch (Exception e) {
            e.printStackTrace();
            this.speed = 1;
        }
    }

    public void go(){
        Thread.currentThread().setPriority(speed);
        for (int i = 0; i < 100; i++) {
            System.out.printf("Runner %d ran %d meters%n", id , i+1);
        }
        finishRace();
    }
    
    public void finishRace(){
        track.incrementFinishedRacers();
        setFinishPosition();
        System.out.printf("Runner %d finished %s%n", id, finishPosition);
    }

    private void setFinishPosition() {
        int finishedRacersNum = track.getFinishedRacers();
        if( finishedRacersNum == 1) finishPosition = "1st";
        else if( finishedRacersNum == 2) finishPosition = "2nd";
        else if( finishedRacersNum == 3) finishPosition = "3rd";
        else finishPosition = finishedRacersNum + "th";
    }

    @Override
    public void run() {
        go();
    }
    
}
