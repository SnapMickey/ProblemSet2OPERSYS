
import java.util.ArrayList;


/**
 *
 * @author Jared
 * @version 0
 * 
 * Station class. 
 */
public class Station {
    int stationNum;
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
    }
    
    public int getNumOfPassengers(){
        return passengers.size();
    }
    
    public int getStationNum(){
        return stationNum;
    }
    
    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
    }
    
    public void removePassengers(ArrayList<Passenger> passengers){
        this.passengers.removeAll(passengers);
    }
    
    public void addPassenger(Passenger passenger){
            passengers.add(passenger);
    }
    
    public void addPassengers(ArrayList<Passenger> passengers){
        this.passengers.addAll(passengers);
    }
}
