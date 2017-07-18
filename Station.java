
import java.util.ArrayList;
import java.util.concurrent.locks.*;


/**
 *
 * @author Jared
 * @version 0
 * 
 * Station class. 
 */
public class Station {
    int stationNum;
    Train trainAtStation;
    Lock boarding;
    Condition trainIsPresent, hasFreeSeats;
    
    
    ArrayList<Passenger> passengers;
    
    /*
    *   CONSTRUCTOR
    */
    public Station(ArrayList<Passenger> passengers, int stationNum){
        if(passengers == null)
            this.passengers = new ArrayList();
        else
            this.passengers.addAll(passengers);
        
        this.stationNum = stationNum;
        this.trainAtStation = null;
        
        boarding = new ReentrantLock();
        trainIsPresent = boarding.newCondition();
        hasFreeSeats = boarding.newCondition();
    }
    
    public int getNumOfPassengers(){
        return passengers.size();
    }
    
    public int getStationNum(){
        return stationNum;
    }
}
