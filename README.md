# Project 4: Analysis of Job Dispatch Strategies in Server Farms

### Introduction
This project explores the real-world problem of managing job queues in an efficient manner in server farms. This is critical for reducing the processing times in data centers. More information on this project can be found in the `Abstract` section of my Project Report.

### How to Use

Testing Queue and LinkedList from 03/12 Lab:
```
% java QueueTests
```

Running the ServerFarmSimulation file steps:
1. Adjust the string on line 30 of the ServerFarmSimulation.java file to either "random", "round", "shortest", or "least". Any other string will give an error when running the class file. 
    - "random" : RandomDispatcher.java
    - "round" : RoundRobinDispatcher.java
    - "shortest" : ShortestQueueDispatcher.java
    - "least" : LeastWorkDispatcher.java

2. Adjust other parameters of the simulation when asked for in the `Exploration` section of this project:
```
    int numServers = 34 ; //Numbers of servers in the farm (default: 34)
    int numJobs = 10000000 ; //Number of jobs to process (default: 10,000,000)
    boolean showViz = false ; //Set to true to see the visualization, and false to run your experiments
```

3. Run the following line in the terminal:
```
% java ServerFarmSimulation
```

### NOTES ON REFLECTION QUESTION 2 FILE EDITS
The following files should be updated to accommodate for the PreemptiveServer.java file instead of the previous Server.java file. All lines to be edited can be found in the comment at the top of the files.
- JobDispatcher.java
- LeastWorkDispatcher.java
- RandomDispatcher.java
- RoundRobinDispatcher.java
- ShortestQueueDispatcher.java

To run Reflection Question 2:
```
% java ServerFarmSimulation
```

### NOTES ON EXTENSION 2
To use extension 2, follow the same instructions as the "how to use" section of this README but instead, use "balanced" as the string in place for deciding on the dispatcher type. This dispatcher type is a hybrid of LeastWorkDispatcher and ShortestQueueDispatcher. It also has the lowest average wait time out of all dispatchers I've created.
