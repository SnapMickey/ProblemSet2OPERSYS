
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jared
 * @version 0
 * 
 * Thread class for Robot Passengers.
 */
public class Passenger extends Thread{
    int id, destination;
    Station currentStation;
    Seat seat;
    Train train;                                      

    /*
    *   CONSTRUCTOR
    */
    public Passenger(int id, Station source, int destination){
        this.id = id;
        this.currentStation = source;
        this.destination = destination;
        this.seat = null;
        this.train = null;
    }
    
    /*
    *   MAIN FUNCTION OF THIS THREAD
    */
    @Override
    public void run() {
        stationWaitForTrain();
        boardTrain();
        
        getOffTrain();
        Thread.currentThread().interrupt();
        return;
    }
    
    /*
    *   WAIT FOR TRAIN FUNCTION
    */
    public void stationWaitForTrain(){
        currentStation.getBoardingLock().lock();
        try {
            while (currentStation.getTrain() == null) {
                try{
                currentStation.getNotBoardingCondition().await();
                }
                catch(InterruptedException e){}
            }
            
            boardTrain();
            stationOnBoard();
            
            currentStation.getNotBoardingCondition().signal();
        } finally {
            currentStation.getBoardingLock().unlock();
        }
    }
    
    public void boardTrain(){
        currentStation.removePassenger(this);
        currentStation = null;
        train.addPassenger(this);
        
        System.out.println("Passenger " + id + " is getting on Train" + train.getTrainNum());
    }
    
    public void getOffTrain(){
        train.removePassenger(this);
        currentStation = train.getCurrentStation();
        System.out.println("Passenger " + id + " is getting off at Station " + currentStation.getStationNum());
    }
    
    
    /*
    *   ON BOARD TRAIN FUNCTION
    */
    public void stationOnBoard(){
        seat.getLock().lock();
        while(train.getCurrentStation().getStationNum() != destination)
            try {
                seat.getOccupied().await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Passenger.class.getName()).log(Level.SEVERE, null, ex);
            }
        seat.getOccupied().signal();
        seat.getLock().unlock();  
    }

    public int getID(){
        return id;
    }
    
    public Station getCurrentStation(){
        return currentStation;
    }
    
    public int getDestination(){
        return destination;
    }
}
