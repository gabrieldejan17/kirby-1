package Objects;

public class Timer {

    //tracks system time when the timer starts
    private long start; 

    //shows if timer is running
    private boolean isRunning;

    //tracks current amount of time the timer has been running since being reset
    //note: if the time is run for 1 second, then stopped for 2 seconds, then run for 3 seconds,
    // time will be 4000, since the units are in milliseconds
    private long time;

    Timer(){

    	//initializes isRunning, start, and time variables to its start values
    	isRunning=false;
    	start=0;
    	time=0;
    }
    
  //starts timer
    public void start(){
    	if(isRunning==false){
    		isRunning=true;
    		start=System.currentTimeMillis()-time;
    	}
    }

    //reset the variables to what they were initialized to
    public void reset(){
    	isRunning=false;
    	start=0;
    	time=0;
    }

    //stops timer
    public void stop(){
    	if(isRunning==true){
    		isRunning=false;
    		time=System.currentTimeMillis()-start;
    	}
    }

    //returns the amount of time that has passed since the timer started
    public long time_passed(){
    	if(isRunning==true){
    		time=System.currentTimeMillis()-start;
    		return time;
    	}else{
    		System.out.print("Timer is stopped");	//outputs that timer has stopped
    		return time;	//returns value of time
    	}
    }
    
    //returns value of isRunning
    public boolean isRunning(){
    	return isRunning;
    }
}
