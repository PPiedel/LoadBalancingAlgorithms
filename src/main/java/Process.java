/**
 * Created by Pawel on 2015-05-03.
 */
public class Process {
    private final int accessTime;
    private final int computingPowerUsage;
    private int executingTime;
    private int waitingTime=0;
    private int movesNumber = 0;
    private int numberOfProcessor;

    public Process(int accessTime, int executingTime, int computingPowerUsage) {
        this.accessTime = accessTime;
        this.executingTime = executingTime;
        this.computingPowerUsage = computingPowerUsage;
    }

    public int getComputingPowerUsage() {
        return computingPowerUsage;
    }

    public int getMovesNumber() {
        return movesNumber;
    }

    public void setMovesNumber(int movesNumber) {
        this.movesNumber = movesNumber;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public Process setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
        return this;
    }

    public int getAccessTime() {
        return accessTime;
    }

    public int getExecutingTime() {
        return executingTime;
    }

    public Process setExecutingTime(int executingTime) {
        this.executingTime = executingTime;
        return this;
    }

    public int getNumberOfProcessor() {
        return numberOfProcessor;
    }

    public Process setNumberOfProcessor(int numberOfProcessor) {
        this.numberOfProcessor = numberOfProcessor;
        return this;
    }
}
