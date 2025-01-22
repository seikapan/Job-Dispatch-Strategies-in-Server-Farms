/**
 * RoundRobinDispatcher is a subclass of JobDispatcher that assigns jobs to servers in a round-robin fashion.
 * (Round-robin is a scheduling algorithm that assigns jobs to servers in a circular order.)
 * 
 * File name: RoundRobinDispatcher.java
 * Author: Seika Oelschig
 * Created on: 03/18/24
 * 
 * NOTE: Edits made on Lines 24 & 25 to change "Server" to "PreemptiveServer" 
 * is for Relfection Question 2.
 */

public class RoundRobinDispatcher extends JobDispatcher {

    private int nextServerIndex = 0; // Index of the next server to assign a job to!

    /**
     * Constructor for RoundRobinDispatcher
     * @param k (number of servers)
     * @param showViz (boolean to show visualization or not)
     */
    public RoundRobinDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job job) { // Server -> PreemptiveServer
        Server server = servers.get(nextServerIndex); // Server -> PreemptiveServer
        nextServerIndex = (nextServerIndex + 1) % servers.size();
        return server;
    }
}
