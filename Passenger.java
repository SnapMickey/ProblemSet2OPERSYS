
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
    public Passenger(int id, int destination){
        this.id = id;
        this.currentStation = null;
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
        System.out.println("Passenger " + id + " is waiting at Station " + currentStation.getStationNum());
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
            try{
            currentStation.getBoardingLock().unlock();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void boardTrain(){
        train = currentStation.getTrain();
        train.addPassenger(this);
        currentStation.removePassenger(this);
        currentStation = null;
        
        System.out.println("Passenger " + id + " has boarded on Train" + train.getTrainNum());
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
        try {
            while(train.getCurrentStation().getStationNum() != destination)
                try {
                    seat.getOccupied().await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Passenger.class.getName()).log(Level.SEVERE, null, ex);
                }
            seat.getOccupied().signal();  
        } finally {
            seat.getLock().unlock();
        }
    }

    public int getID(){
        return id;
    }
    
    public void setCurrentStation(Station station){
        currentStation = station;
    }
    
    public Station getCurrentStation(){
        return currentStation;
    }
    
    public int getDestination(){
        return destination;
    }
}
