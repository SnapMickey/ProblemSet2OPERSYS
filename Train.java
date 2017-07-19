
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
    int trainNum;
    Station currentStation;
    ArrayList<Passenger> passengers;
    Seat seats[];  
    
    /*
    *   CONSTRUCTOR
    */
    public Train(int trainNum, int seats){
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
        while(true){
            for(int i = 0; i < 8; i++){
                //Call stationLoadTrain
                //Move train going to the next station
            }
        }
    }
    
    public void stationLoadTrain(){
    
    }
    
    public void freeSeat(Passenger p){
        for (Seat seat : seats) 
            if(seat != null)
                if(seat.getID() == p.getID()) 
                    seat = null;
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
