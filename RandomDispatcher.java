/**
 * RandomDispatcher is a subclass of JobDispatcher that dispatches jobs to servers randomly.
 * 
 * File name: RandomDispatcher.java
 * Author: Seika Oelschig
 * Created on: 03/18/24
 * 
 * NOTE: Edit made on line 28 to change "Server" to "PreemptiveServer" is for Relfection Question 2.
 */

import java.util.Random;

public class RandomDispatcher extends JobDispatcher {
    private Random random;

    /**
     * Constructor for RandomDispatcher
     * @param k (number of servers)
     * @param showViz (boolean to show visualization or not)
     */
    public RandomDispatcher(int k, boolean showViz) {
        super(k, showViz);
        this.random = new Random();
    }


    @Override
    public Server pickServer(Job job) { // Server -> PreemptiveServer
        int index = random.nextInt(servers.size());
        return servers.get(index);
    }
}