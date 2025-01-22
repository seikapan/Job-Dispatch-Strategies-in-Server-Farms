/**
 * A server that processes jobs.
 * 
 * File name: Server.java
 * Author: Seika Oelschig
 * Created on: 03/18/24
 */

import java.util.LinkedList;
import java.util.Queue;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

public class Server {
    protected Queue<Job> jobs;
    protected double time; 
    protected double totalWaitingTime;
    protected double remainingTime;
    protected int numJobs;

    public Server() {
        this.jobs = new LinkedList<>();
        this.time = 0;
        this.totalWaitingTime = 0;
        this.remainingTime = 0;
        this.numJobs = 0;
    }

    /**
     * Adds a job to the server's queue.
     * @param job
     */
    public void addJob(Job job) {
        jobs.offer(job);
        remainingTime += job.getProcessingTimeNeeded();
    }

    /**
     * Processes jobs in the server's queue until the given time.
     * @param time
     */
    public void processTo(double time) {
        double elapsedTime = time - this.time;
        while (!jobs.isEmpty() && elapsedTime > 0) {
            Job currentJob = jobs.peek();
            double jobTime = currentJob.getProcessingTimeRemaining();

            if (jobTime <= elapsedTime) {
                elapsedTime -= jobTime;
                currentJob.process(jobTime, this.time);
                totalWaitingTime += (this.time - currentJob.getArrivalTime() + jobTime);
                remainingTime -= jobTime;
                jobs.poll(); // Remove job from queue
                numJobs++;
            } else {
                currentJob.process(elapsedTime, this.time);
                remainingTime -= elapsedTime;
                elapsedTime = 0;
            }
        }
        this.time = time;
    }

    /**
     * Processes jobs in the server's queue until the given time.
     * @param time
     */
    public double remainingWorkInQueue() {
        return remainingTime;
    }

    public double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public int size() {
        return jobs.size();
    }
    
    public int getNumJobsProcessed() {
        return numJobs;
    }

    public void draw(Graphics g, Color c, double loc, int numberOfServers){
        double sep = (ServerFarmViz.HEIGHT - 20) / (numberOfServers + 2.0);
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), (int) (72.0 * (sep * .5) / Toolkit.getDefaultToolkit().getScreenResolution())));
        g.drawString("Work: " + (remainingWorkInQueue() < 1000 ? remainingWorkInQueue() : ">1000"), 2, (int) (loc + .2 * sep));
        g.drawString("Jobs: " + (size() < 1000 ? size() : ">1000"), 5 , (int) (loc + .55 * sep));
        g.setColor(c); 
        g.fillRect((int) (3 * sep), (int) loc, (int) (.8 * remainingWorkInQueue()), (int) sep);
        g.drawOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
        if (remainingWorkInQueue() == 0) g.setColor(Color.GREEN.darker());
        else g.setColor(Color.RED.darker());
        g.fillOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
    }
}

