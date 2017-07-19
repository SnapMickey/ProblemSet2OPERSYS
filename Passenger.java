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
    Train train;

    /*
    *   CONSTRUCTOR
    */
    public Passenger(int id, Station source, int destination, Train train){
        this.id = id;
        this.currentStation = source;
        this.destination = destination;
        this.train = train;
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
    
    public Station getCurrentStation(){
        return currentStation;
    }
    
    public int getDestination(){
        return destination;
    }
}
