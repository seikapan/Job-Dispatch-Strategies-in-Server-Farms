/*
file name:      Job.java
Authors:        Ike Lage
last modified:  03/07/2024
*/

public class Job {

	public double arrivalTime ;
	private double finishTime ;
	private double processingTimeNeeded ;
	private double processingTimeSpent ;

	public Job(double arrivalTime, double processingTimeNeeded) {
		this.arrivalTime = arrivalTime ;
		this.processingTimeNeeded = processingTimeNeeded ;
		this.processingTimeSpent = 0. ;
	}

	/**
	 * @return the time the job arrived at the server farm.
	 */
	public double getArrivalTime() {
		return this.arrivalTime ;
	}

	/**
	 * @return the total amount of processing time the job needed to finish when it arrived at the server farm.
	 */
	public double getProcessingTimeNeeded() {
		return this.processingTimeNeeded ;
	}

	/**
	 * @return the amount of processing time the job still needs to be considered done (this will be less than or equal to getProcessingTimeNeeded(). It will go down as you spend time processing the job).
	 */
	public double getProcessingTimeRemaining() {
		return this.processingTimeNeeded - this.processingTimeSpent ;
	}

	/**
	 * @return returns TRUE if this job has been processed for long enough to finish (i.e. it's done).
	 */
	public boolean isFinished() {
		return ( this.getProcessingTimeRemaining() <= 0 ) ;
	}

	/**
	 * @return the amount of time between when the job arrived at the server farm, and when it was finished processing. This will include the total processing time, and potentially some time spent waiting to be processed.
	 */
	public double timeInQueue() {
		return this.finishTime - this.arrivalTime ;
	}

	/**
	 * Spends timeToProcessFor units of time processing the job. If the job finishes, this method updates the values of timeInQueue and isFinished to be accurate. The timeProcessingStarted indicates what time it was on the system clock when process was called. (This just helps compute when the job finished).
	 * @param timeToProcessFor (the amount of time to spend processing the job. This will be less than or equal to getProcessingTimeRemaining()
	 * @param timeProcessingStarted (the time on the system clock when process was called. This is used to compute when the job finished processing.)
	 */
	public void process ( double timeToProcessFor , double timeProcessingStarted ) {
		this.processingTimeSpent = this.processingTimeSpent + timeToProcessFor ;
		if ( this.isFinished() ){
			this.finishTime = timeProcessingStarted + timeToProcessFor ;
		}
	}

	public String toString() {
		return "Arrival: " + arrivalTime + ", Finish: " + finishTime + ", Time Needed: " + processingTimeNeeded + ", Time Spent: " + processingTimeSpent ;
	}

}