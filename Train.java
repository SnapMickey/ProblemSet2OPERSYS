
import java.util.ArrayList;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jared
 * @version 0
 * 
 * Thread class for Trains.
 */
public class Train extends Thread{
    TrainSystem system;
    int trainNum, numOfSeats; //, numOfFreeSeats;
    Station currentStation;
    //ArrayList<Passenger> passengers;
    Passenger[] passengers;
    
    /*
    *   CONSTRUCTOR
    */
    public Train(TrainSystem system, int trainNum, int seats){
        this.system = system;
        this.trainNum = trainNum;
        this.currentStation = null;
        numOfSeats = seats; //numOfFreeSeats = seats;
        
        passengers = new Passenger[seats];
        
        for(int i = 0; i < seats; i++)
            passengers[i] = null;
        //passengers = new ArrayList();
    }

    /*
    *   MAIN FUNCTION FOR THIS THREAD
    */
    @Override
    public void run() {
        system.enterRailSystem(this);
        while(true){
            system.moveTrain(this);

            System.out.println("Train " + trainNum + "  has arrived at Station " + currentStation.getStationNum());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
            }
            currentStation.stationLoadTrain();
            System.out.println("Train " + trainNum + "  is leaving Station " + currentStation.getStationNum());

            system.moveTrain(this);
        }
    }
    
    public void requestTrain(){
        system.requestNewTrain(currentStation);
    }
    
    
//    public void addPassenger(Passenger passenger){
//        passengers.add(passenger);
//        numOfFreeSeats--;
//    }
//    public void removePassenger(Passenger passenger){
//        passengers.remove(passenger);
//        numOfFreeSeats++;
//    }
//    
//    public ArrayList getPassengers(){
//        return passengers;
//    }
//    
//    public int getNumOfPassengers(){
//        return passengers.size();
//    }
    
    public Passenger[] getPassengers(){
        return passengers;
    }

    public int getNumOfPassengers(){
        
        int numOfPassengers = 0;
        for(Passenger p : passengers){
                if(p == null)
                    numOfPassengers++;
            }

        return numOfPassengers;


        //return passengers.size();
    }
    
    public boolean hasFreeSeat(){
        for(Passenger p : passengers){
            if(p == null)
                return true;
        }
        return false;
    }
    
    public int getNumOfSeats(boolean onlyFree){
        if(onlyFree){
            int numOfFreeSeats = 0;
            for(Passenger p : passengers){
                if(p == null)
                    numOfFreeSeats++;
            }
            
            return numOfFreeSeats;
        }
        
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
}
