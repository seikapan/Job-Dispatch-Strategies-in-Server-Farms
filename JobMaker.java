/*
file name:      JobMaker.java
Authors:        Max Bender, Modified by Ike Lage
last modified:  03/07/2024
*/

import java.util.Random;
import java.io.*;
    @SuppressWarnings("unused")

public class JobMaker {

    private double meanArrivalTime ;
    private double meanProcessingTime ;
    private double curTime ;

    public JobMaker( double meanArrivalTime , double meanProcessingTime ) {
        this.meanArrivalTime = meanArrivalTime ;
        this.meanProcessingTime = meanProcessingTime ;
        this.curTime = 0;
    }

    public double nextExponential(double mean) {
        Random r = new Random();
        double val = - mean * Math.log(r.nextDouble());
        double valTweeked = (1.0 * ((int) (val * 128))) / 128;
        valTweeked = Math.max(valTweeked, 1.0/128);
        return valTweeked;
    }

    public Job getNextJob(){ 
        //Get total processing time of job
        double jobProcessingTime = nextExponential( this.meanProcessingTime );
        //jobProcessingTime = 5 ;
        Job nextJob = new Job( this.curTime , jobProcessingTime );
        //Set arrival time of the following job
        this.curTime += nextExponential( this.meanArrivalTime ); 
        return nextJob ;
    }
    
}
