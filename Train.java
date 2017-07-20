
import java.util.ArrayList;
import java.util.concurrent.locks.*;

/**
 *
 * @author Jared
 * @version 0
 * 
 * Thread class for Trains.
 */
public class Train extends Thread{
    TrainSystem system;
    int trainNum, numOfSeats, numOfFreeSeats;
    boolean passengersGettingOff;
    Station currentStation;
    ArrayList<Passenger> passengers;  
    Lock onBoard;
    Condition notAtDestination;
    
    /*
    *   CONSTRUCTOR
    */
    public Train(TrainSystem system, int trainNum, int seats){
        this.system = system;
        this.trainNum = trainNum;
        this.currentStation = null;
        passengersGettingOff = false;
        numOfSeats = numOfFreeSeats = seats;
        passengers = new ArrayList();
        onBoard = new ReentrantLock();
        notAtDestination = onBoard.newCondition();
    }

    /*
    *   MAIN FUNCTION FOR THIS THREAD
    */
    @Override
    public void run() {
        system.enterRailSystem(this);
        while(true){
            system.moveTrain(this);
            stationLoadTrain();   
            system.moveTrain(this);
        }
    }
    
    public void stationLoadTrain(){
        System.out.println("Train " + trainNum + "  has arrived at Station " + currentStation.getStationNum());
        try{
            Thread.sleep(1000);
            currentStation.getStationLock().lock();

            
            while((getNumOfSeats(true) > 0 && currentStation.getNumOfPassengers() > 0) || passengersGettingOff){
                currentStation.setIsBoarding(true);
                currentStation.getBoardingCondition().await();
            }
            System.out.println("Train " + trainNum + "  is leaving Station " + currentStation.getStationNum());
            currentStation.setIsBoarding(false);
            
            currentStation.getBoardingCondition().signal();
        }
        catch(InterruptedException e){}
        finally{
            try{
            currentStation.getBoardingLock().unlock();
            }
            catch(Exception e){}
        }
    }
    
    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
        numOfFreeSeats--;
    }
    
    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
        numOfFreeSeats++;
    }
    
    public int getNumOfPassengers(){
        return passengers.size();
    }
    
    public int getNumOfSeats(boolean onlyFree){
        if(onlyFree)
            return numOfFreeSeats;
        else
            return numOfSeats;
    }
    
    public int getTrainNum(){
        return trainNum;
    }
    
    public Station getCurrentStation(){
        return currentStation;
    }
    
    public void setCurrentStation(Station station){
        currentStation = station;
    }
    
    public boolean arePassengersGettingOff(){
        return passengersGettingOff;
    }
    
    public void setPassengersGettingOff(boolean truth){
        passengersGettingOff = truth;
    }
    
    public Lock getOnBoardLock(){
        return onBoard;
    }
    
    public Condition getNotAtDestinationCondition(){
        return notAtDestination;
    }
}
