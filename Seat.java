
import java.util.concurrent.locks.*;

/**
 *
 * @author Jared
 */
public class Seat {
    int seatId;
    Lock lock;
    Condition occupied;
    
    public Seat(int seatId){
        this.seatId = seatId;
        this.lock = new ReentrantLock();
        this.occupied = lock.newCondition();
    }
    
    public int getID(){
        return seatId;
    }
    
    public Lock getLock(){
        return lock;
    }
    
    public Condition getOccupied(){
        return occupied;
    }
}
