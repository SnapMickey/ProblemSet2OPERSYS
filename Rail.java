
import java.util.concurrent.locks.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jared
 */
public class Rail{
    Train train;
    Station station;
    Lock lock; 
    
    public Rail(){
        this.station = null;
        this.train = null;
        this.lock = new ReentrantLock();
    }
    
    public Train getTrain(){
        return train;
    }
    
    public Station getStation(){
        return station;
    }
    
    public void setStation(Station station){
        this.station = station;
    }
    
    public Lock getLock(){
        return lock;
    }
    
    public void setTrain(Train train){
        this.train = train;
    }
    
    
}
