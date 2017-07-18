
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
    Passenger seats[];  
    
    /*
    *   CONSTRUCTOR
    */
    public Train(int trainNum, int seats){
        this.trainNum = trainNum;
        this.currentStation = null;
        this.seats = new Passenger[seats];
        
        for(int i = 0; i < seats; i++)
            this.seats[i] = null;
    }

    /*
    *   MAIN FUNCTION FOR THIS THREAD
    */
    @Override
    public void run() {
        while(true){
            for(int i = 0; i < 8; i++){
                //Call stationLoadTrain
                move(); //Move train going to the next station
            }
        }
    }
    public void stationLoadTrain(Station station){
        currentStation = station;
        station.trainAtStation = this;
        station.trainIsPresent.signal();
         
        while(getNumOfSeats(true) > 0 && station.getNumOfPassengers() > 0)
            station.hasFreeSeats.signal();
    }
    
    public void move(){
        
    }
    public void freeSeat(Passenger p){
        for (Passenger seat : seats) 
            if(seat != null)
                if(seat.getID() == p.getID()) 
                    seat = null;
    }
     
     
    public int getNumOfPassengers(){
        int numOfPassengers = 0;
        for (Passenger seat : seats) 
            if (seat != null) 
                numOfPassengers++;

        return numOfPassengers;
    }
    
    public int getNumOfSeats(boolean onlyFree){
        if(onlyFree){
            int numOfFreeSeats = 0;
            for (Passenger seat : seats) 
                if (seat == null) 
                    numOfFreeSeats++;

            return numOfFreeSeats;
        }
        else
            return seats.length;
    }
    
    public int getTrainNum(){
        return trainNum;
    }
}
