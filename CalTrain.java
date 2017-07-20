
import java.util.ArrayList;

/**
 *
 * @author Jared
 * @version 0
 * 
 * 
 * 
 * main class for CalTrain Simulation.
 */
public class CalTrain {
     
    public static void main(String args[]){
        TrainSystem system = new TrainSystem();
        ArrayList<Passenger> passengers = new ArrayList();
        ArrayList<Train> trains = new ArrayList();
        
        trains.add(new Train(system,1,1));
        trains.add(new Train(system,2,1));
        system.createTrains(trains);
        
        passengers.add(new Passenger(1,2));
        passengers.add(new Passenger(2,4));
        system.acceptPassengers(1,passengers);
        
        system.deployTrain();
    }
}
