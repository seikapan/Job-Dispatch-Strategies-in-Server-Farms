import java.util.ArrayList;
@SuppressWarnings("unused")

public class BalancedDispatcher extends JobDispatcher {

    public BalancedDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }



    @Override
    public Server pickServer(Job job) {
        int minScoreIndex = 0;
        double minScore = Double.MAX_VALUE;
        
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            double score = calculateServerScore(server);
            if (score < minScore) {
                minScore = score;
                minScoreIndex = i;
            }
        }
        
        return servers.get(minScoreIndex);
    }
    


    private double calculateServerScore(Server server) {
        // Defaults are 0.5
        double queueLengthWeight = 0.5;
        double remainingWorkWeight = 0.5;
        int queueLength = server.size();
        double remainingWork = server.remainingWorkInQueue();
        
        return (queueLengthWeight * queueLength) + (remainingWorkWeight * remainingWork);
    }
}
