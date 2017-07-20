
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author Jared
 * @version 0
 * 
 * Thread class for Trains.
 */
public class Train extends Thread{
    TrainSystem system;
    int trainNum;
    Station currentStation;
    ArrayList<Passenger> passengers;
    Seat seats[];  
    
    /*
    *   CONSTRUCTOR
    */
    public Train(TrainSystem system, int trainNum, int seats){
        this.system = system;
        this.trainNum = trainNum;
        this.currentStation = null;
        this.passengers = new ArrayList();
        this.seats = new Seat[seats];
        
        for(int i = 0; i < seats; i++)
            this.seats[i] = new Seat(i);
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
        }
    }
    
    public void stationLoadTrain(){
        System.out.println("Train " + trainNum + "  has arrived at Station " + currentStation.getStationNum());
        try{
            Thread.sleep(1000);
            currentStation.getStationLock().lock();

            while(getNumOfSeats(true) > 0 && currentStation.getNumOfPassengers() > 0){
                currentStation.setIsBoarding(true);
                currentStation.getBoardingCondition().await();
            }
            System.out.println("Train " + trainNum + "  is leaving Station " + currentStation.getStationNum());
            currentStation.setIsBoarding(false);
            
            currentStation.getBoardingCondition().signal();
        }
        catch(InterruptedException e){}
        finally{
            currentStation.getBoardingLock().unlock();
        }
    }
    
    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
    }
    
    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
    }
    
    public int getNumOfPassengers(){
        return passengers.size();
    }
    
    public int getNumOfSeats(boolean onlyFree){
        if(onlyFree){
            int numOfFreeSeats = 0;
            for (Seat seat : seats) 
                try{
                    if(seat.getLock().tryLock())
                        numOfFreeSeats++;
                }
                finally{seat.getLock().unlock();}

            return numOfFreeSeats;
        }
        else
            return seats.length;
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
}
