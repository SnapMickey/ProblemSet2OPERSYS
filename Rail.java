/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jared
 */
public class Rail {
    Train train;
    Station station;
    public Rail(Train train, Station station){
        this.train = train;
        this.station = station;
    }
    
    public Train getTrain(){
        return train;
    }
    
    public Station getStation(){
        return station;
    }
    
    public void setTrain(Train train){
        this.train = train;
    }
    
    public void setStation(Station station){
        this.station = station;
    }
}