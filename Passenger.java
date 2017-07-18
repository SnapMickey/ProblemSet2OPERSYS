/**
 *
 * @author Jared
 * @version 0
 * 
 * Thread class for Robot Passengers.
 */
public class Passenger implements Runnable{
    int id;
    Station source, destination;
    Train train;

    /*
    *   CONSTRUCTOR
    */
    public Passenger(int id, Station source, Station destination, Train train){
        this.id = id;
        this.source = source;
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
        while(station.trainAtStation == null)
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
    
    public Station getSource(){
        return source;
    }
    
    public Station getDestination(){
        return destination;
    }
}
