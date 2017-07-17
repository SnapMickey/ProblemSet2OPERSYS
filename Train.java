
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author Jared
 * @version 0
 * 
 * Thread class for Trains.
 */
public class Train implements Runnable{
    int trainNum, numOfSeats;
    ArrayList<Passenger> passengers;

    
    /*
    *   CONSTRUCTOR
    */
    public Train(int trainNum, int seats){
        this.trainNum = trainNum;
        this.numOfSeats = seats;
        
        this.passengers = new ArrayList();
    }

    /*
    *   MAIN FUNCTION FOR THIS THREAD
    */
    @Override
    public void run() {
        
    }
    
    public int getNumOfPassengers(){
        return passengers.size();
    }
    
    public int getNumOfSeats(boolean onlyFree){
        if(onlyFree){
            return numOfSeats - passengers.size();
        }
        else
            return numOfSeats;
    }
    
    public int getTrainNum(){
        return trainNum;
    }
    
    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
    }
    
    public void addPassenger(Passenger passenger){
            passengers.add(passenger);
    }
    
}
