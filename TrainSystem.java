
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jared
 */
public class TrainSystem {
    Station[] stations;
    Rail[] rails;
    
    public TrainSystem(){   
        Station[] stations = new Station[8];
        Rail[] trainSystem = new Rail[16];
        
        for(int i = 0; i < 8; i++){
            stations[i] = new Station(null,i+1); 
        }
        
        for(int i = 0; i < 16; i++){
            
            if(i%2==1)
                trainSystem[i] = new Rail(stations[(i/2)],null);
            else
                trainSystem[i] = new Rail(null,null);
        }
    }
    
    public void acceptPassengers(int stationNum,ArrayList<Passenger> passengers){
        for(Station s : stations){
            if(s.getStationNum() == stationNum){
                s.addPassenger(passengers);
                break;
            }
        }
    }
    
    public void moveTrain(int trainNum){

        Rail currentRailPosition = null
           , nextRailPosition = null;
        for(int i = 0; i < 16; i++){
            if(rails[i].getTrain() != null)
                if(rails[i].getTrain().getTrainNum() == trainNum){
                    currentRailPosition = rails[i];
                    nextRailPosition = rails[(i+1)%16];
                }
        }
        Train movingTrain = currentRailPosition.getTrain();
        
        currentRailPosition.setTrain(null);
        nextRailPosition.setTrain(movingTrain);
        
        
        if(currentRailPosition.getStation() != null){
            currentRailPosition.getStation().setTrain(null);
            movingTrain.setCurrentStation(null);
        }
        
        if(nextRailPosition.getStation() != null){
            nextRailPosition.getStation().setTrain(movingTrain);
            movingTrain.setCurrentStation(nextRailPosition.getStation());
        }
    }
}
