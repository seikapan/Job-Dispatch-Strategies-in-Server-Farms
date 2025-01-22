/**
 * LeastWorkDispatcher is a subclass of JobDispatcher that implements the pickServer method
 * 
 * File name: LeastWorkDispatcher.java
 * Author: Seika Oelschig
 * Created on: 03/18/24
 * 
 * NOTE: Edit made on line 22 to change "Server" to "PreemptiveServer" is for Relfection Question 2.
 */

public class LeastWorkDispatcher extends JobDispatcher {

    /**
     * Constructor for LeastWorkDispatcher
     * @param k (number of servers)
     * @param showViz (boolean to show visualization or not)
     */
    public LeastWorkDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job job) {
        Server selectedServer = servers.get(0); // Server -> PreemptiveServer
        for (Server server : servers) {
            if (server.remainingWorkInQueue() < selectedServer.remainingWorkInQueue()) {
                selectedServer = server;
            }
        }
        return selectedServer;
    }
}
