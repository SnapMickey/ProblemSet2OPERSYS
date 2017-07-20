
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
    ArrayList<Train> serviceStation;
    
    public TrainSystem(){   
        stations = new Station[8];
        rails = new Rail[16];
        serviceStation = new ArrayList(); 
        
        for(int i = 0; i < 8; i++){
            stations[i] = new Station(null,i+1); 
        }
        for(int i = 0; i < 16; i++){
            rails[i] = new Rail();
            if(i%2==1)
                rails[i].setStation(stations[i/2]);
        }
    }
    
    public void acceptPassengers(int stationNum,ArrayList<Passenger> passengers){
        for(Station s : stations){
            if(s.getStationNum() == stationNum){
                s.addPassenger(passengers);
                
                for(Passenger p : passengers){
                    p.setCurrentStation(s);
                    if(!p.isAlive())
                        p.start();
                }
                break;
            }
        }
    }
    
    public void createTrains(ArrayList<Train> trains){
        serviceStation.addAll(trains);
    }
    
    public void moveTrain(Train train){
        Rail currentRailPosition = null
           , nextRailPosition = null;
        
        for(int i = 0; i < 16; i++){
            if(rails[i].getTrain() != null)
                if(rails[i].getTrain().equals(train)){
                    currentRailPosition = rails[i];
                    nextRailPosition = rails[(i+1)%16];
                }
        }
        Train movingTrain = currentRailPosition.getTrain();
        
        nextRailPosition.getLock().lock();
        currentRailPosition.getLock().unlock();
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
        
    public void requestNewTrain(Station station){
        boolean approveNewTrain = true;
        for(int i=0;i<16;i++){
            if(rails[i].getTrain() != null)
                approveNewTrain = false;
            else if(rails[i].getStation().equals(station))
                break;
        }
        
        if(approveNewTrain){
            deployTrain();
        }
    }
    
    public void deployTrain(){
        Train newTrain = serviceStation.get(0);
        serviceStation.remove(0);
        newTrain.start();
    }
    
    public void enterRailSystem(Train train){
        rails[0].getLock().lock();
        rails[0].setTrain(train);
        System.out.println("Train " + train.getTrainNum() + " has entered the RailSystem");
    }
}

