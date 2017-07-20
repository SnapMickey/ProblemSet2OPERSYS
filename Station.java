
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
    boolean isBoarding;
    Lock stationLock, boardingLock;
    Condition notBoarding, boarding;
    
    
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
        
        isBoarding = false;
        
        boardingLock = new ReentrantLock();
        notBoarding = boardingLock.newCondition();
        
        stationLock = new ReentrantLock();
        boarding = stationLock.newCondition();
    }
    
    public void addPassenger(ArrayList<Passenger> passengers){
        this.passengers.addAll(passengers);
        for(Passenger p : passengers)
            p.start();
    }
    
    public void addPassenger(Passenger passenger){
        this.passengers.add(passenger);
        passenger.start();
    }
    
    public void removePassenger(ArrayList<Passenger> passengers){
        this.passengers.removeAll(passengers);
    }
    
    public void removePassenger(Passenger passenger){
        this.passengers.remove(passenger);
    }
    
    public int getNumOfPassengers(){
        return passengers.size();
    }
    
    public int getStationNum(){
        return stationNum;
    }
    
    public Train getTrain(){
        return trainAtStation;
    }
    
    public void setTrain(Train train){
        trainAtStation = train;
    }
    
    public boolean isBoarding(){
        return isBoarding;
    }
    
    public void setIsBoarding(boolean truth){
        this.isBoarding = truth;
    }
    
    public Lock getBoardingLock(){
        return boardingLock;
    }
    
    public Condition getNotBoardingCondition(){
        return notBoarding;
    }
    
    public Lock getStationLock(){
        return stationLock;
    }
    
    public Condition getBoardingCondition(){
        return boarding;
    }
    
    
}
