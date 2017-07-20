
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
    Train train;                                      
    
    /*
    *   CONSTRUCTOR
    */
    public Passenger(int id, int destination){
        this.id = id;
        this.currentStation = null;
        this.destination = destination;
        this.train = null;
    }
    
    /*
    *   MAIN FUNCTION OF THIS THREAD
    */
    @Override
    public void run() {
        System.out.println("Passenger " + id + " is waiting at Station " + currentStation.getStationNum());
        currentStation.stationWaitForTrain(this);
        currentStation.stationOnBoard();
        currentStation = null;
    }
    
    /*
    *   WAIT FOR TRAIN FUNCTION
    */
    
    public void boardTrain(){
        train = currentStation.getTrain();
        //train.addPassenger(this);
        for(int i = 0; i < train.getNumOfSeats(false); i++){
            if(train.getPassengers()[i] == null){
                train.getPassengers()[i] = this;
                break;
            }
        }
        currentStation.removePassenger(this);
        //train.numOfFreeSeats--;
        System.out.println("Passenger " + id + " has boarded on Train" + train.getTrainNum());
    }
    
    public void getOffTrain(){
        //train.removePassenger(this);
        for(int i = 0; i < train.getNumOfSeats(false); i++){
            if(train.getPassengers()[i].equals(this)){
                train.getPassengers()[i] = null;
                break;
            }
        }
        currentStation = train.getCurrentStation();
        //train.numOfFreeSeats++;
        train = null;
        System.out.println("Passenger " + id + " is getting off at Station " + currentStation.getStationNum());
    }
    
    
    /*
    *   ON BOARD TRAIN FUNCTION
    */
    

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
