/**
 *
 * @author Jared
 * @version 0
 * 
 * Thread class for Robot Passengers.
 */
public class Passenger implements Runnable{
    int id, destination;

    /*
    *   CONSTRUCTOR
    */
    public Passenger(int id, int destination){
        this.id = id;
        this.destination = destination;
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
        
    }
    
    /*
    *   ON BOARD TRAIN FUNCTION
    */
    public void stationOnBoard(Station station){
        
    }
    
    /*
    *   BOARDING TRAIN FUNCTION
    */
    public void boardTrain(){
        
    }
    
    public int getID(){
        return id;
    }
    
    public int getDestination(){
        return destination;
    }
}
