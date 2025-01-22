/**
 * ShortestQueueDispatcher is a subclass of JobDispatcher that 
 * dispatches jobs to the server with the shortest queue.
 * 
 * File name: ShortestQueueDispatcher.java
 * Author: Seika Oelschig
 * Created on: 03/18/24
 * 
 * NOTE: Edits made on Lines 22, 23, and 24 to change "Server" to "PreemptiveServer" 
 * are for Reflection Question 2.
 */

public class ShortestQueueDispatcher extends JobDispatcher {

    /**
     * Constructor for ShortestQueueDispatcher
     * @param k (number of servers)
     * @param showViz (boolean to show visualization or not)
     */
    public ShortestQueueDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job job) { // Server -> PreemptiveServer
        Server selectedServer = servers.get(0); // Server -> PreemptiveServer
        for (Server server : servers) { // Server -> PreemptiveServer
            if (server.size() < selectedServer.size()) {
                selectedServer = server;
            }
        }
        return selectedServer;
    }
}
