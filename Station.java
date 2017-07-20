
import java.util.ArrayList;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        
        boardingLock = new ReentrantLock();
        boarding = boardingLock.newCondition();
        
        stationLock = new ReentrantLock();
        notBoarding = stationLock.newCondition();
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
    
    public ArrayList<Passenger> getPassengers(){
        return passengers;
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
    
    public void stationLoadTrain(){
        stationLock.lock();
        try{
            
            ArrayList<Passenger>passengersOnTrain = new ArrayList();
            passengersOnTrain.addAll(trainAtStation.getPassengers());
            
            boardingLock.lock();
            try{
                for(Passenger p : passengersOnTrain){
                    if(p.getDestination() == stationNum){
                        trainAtStation.removePassenger(p);
                        p.getOffTrain();
                    }
                }
                    boarding.signal();
            }finally {
                    boardingLock.unlock();
            }
            
            try {
                while(trainAtStation.getNumOfSeats(true) > 0 && passengers.size() > 0)
                    notBoarding.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
            }
            notBoarding.signal();
        }finally{ 
            stationLock.unlock();
        }
        
        if(passengers.size() > 0)
            trainAtStation.requestTrain();
    }
    
    public void stationWaitForTrain(Passenger p){
        
        boardingLock.lock();
        try {
            while (trainAtStation == null || trainAtStation.getNumOfSeats(true) == 0) {
                try{
                    boarding.await();
                }
                catch(InterruptedException e){}
            }
            
            p.boardTrain();
            
            boarding.signal();
        } finally {
            boardingLock.unlock();
        }
    }
    
    public void stationOnBoard(){
        stationLock.lock();
        try {
            if((trainAtStation.getNumOfSeats(true) > 0 && passengers.size() > 0)){
                try {
                    notBoarding.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Station.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            notBoarding.signal();
        } finally {
            stationLock.unlock();
        }
    }
}
