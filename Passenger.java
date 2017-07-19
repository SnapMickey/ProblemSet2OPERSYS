
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jared
 * @version 0
 * 
 * Thread class for Robot Passengers.
 */
public class Passenger implements Runnable{
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
        

        
        
    }
    
    /*
    *   WAIT FOR TRAIN FUNCTION
    */
    public void stationWaitForTrain(Station station){
        while(station.getTrain() == null)
            try{
            station.trainIsPresent.await();
            }
            catch(Exception e){}
    }
    
    /*
    *   ON BOARD TRAIN FUNCTION
    */
    public void stationOnBoard(){
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
