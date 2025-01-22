/**
 * This class is an abstract class that represents a job dispatcher.
 * It has a list of servers, a time, a server farm visualization, and the number of jobs handled.
 * 
 * File name: JobDispatcher.java
 * Author: Seika Oelschig
 * Created on: 03/18/24
 * 
 * NOTE: Edit made on Line 45 to change "Server" to "PreemptiveServer" is for Relfection Question 2.
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public abstract class JobDispatcher {
    protected ArrayList<Server> servers; // Servers -> PreemptiveServer
    protected double time;
    protected ServerFarmViz serverFarmViz;
    protected int numJobsHandled;

    /**
     * Constructor for JobDispatcher
     * @param k (number of servers)
     * @param showViz (boolean to show visualization or not)
     */
    public JobDispatcher(int k, boolean showViz) {
        this.servers = new ArrayList<Server>(k); // Servers -> PreemptiveServer
        for (int i = 0; i < k; i++) {
            servers.add(new Server()); // Servers -> PreemptiveServer
        }
        this.time = 0;
        this.numJobsHandled = 0;
        this.serverFarmViz = new ServerFarmViz(this, showViz);
    }

    public double getTime() {
        return this.time;
    }

    /**
     * Returns the list of servers
     * @return this.servers
     */
    public ArrayList<Server> getServerList() { // Servers -> PreemptiveServer
        return this.servers;
    }

    /**
     * Advances time to the given time
     * @param time
     */
    public void advanceTimeTo(double time) {
        for (Server server : servers) {
            server.processTo(time);
        }
        this.time = time;
    }

    /**
     * Handles the given job
     * @param job
     */
    public void handleJob(Job job) {
        advanceTimeTo(job.getArrivalTime());
        serverFarmViz.repaint();
        Server server = pickServer(job);
        server.addJob(job);
        serverFarmViz.repaint();
        numJobsHandled++;
    }

    /**
     * Finishes up the simulation
     */
    public void finishUp() {
        boolean allJobsProcessed;
        do {
            allJobsProcessed = true;
            for (Server server : servers) {
                if (server.remainingWorkInQueue() > 0) {
                    server.processTo(this.time + 1);
                    allJobsProcessed = false;
                }
            }
            this.time += 1;
        } while (!allJobsProcessed);
    }

    public int getNumJobsHandled() {
        return numJobsHandled;
    }

    public double getAverageWaitingTime() {
        double totalWaitingTime = 0;
        int totalNumJobs = 0;
        for (Server server : servers) {
            totalWaitingTime += server.getTotalWaitingTime();
            totalNumJobs += server.getNumJobsProcessed();
        }
        return totalNumJobs > 0 ? totalWaitingTime / totalNumJobs : 0;
    }

    public abstract Server pickServer(Job job);

    public void draw(Graphics g){
        double sep = (ServerFarmViz.HEIGHT - 20) / (getServerList().size() + 2.0);
        g.drawString("Time: " + getTime(), (int) sep, ServerFarmViz.HEIGHT - 20);
        g.drawString("Jobs handled: " + getNumJobsHandled(), (int) sep, ServerFarmViz.HEIGHT - 10);
        for(int i = 0; i < getServerList().size(); i++){
            getServerList().get( i ).draw(g, (i % 2 == 0) ? Color.GRAY : Color.DARK_GRAY, (i + 1) * sep, getServerList().size());
        }
    }
}