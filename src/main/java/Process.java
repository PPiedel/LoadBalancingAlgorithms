/**
 * Created by Pawel on 2015-05-03.
 */
public class Process {
    private final int accessTime;
    private final int powerUsage;
    private int executingTime;
    private int waitingTime=0;
    private int movesNumber = 0;
    private int numberOfProcessor;
    private boolean duringExecution = false;
    private boolean done =false;

    public Process(int accessTime, int executingTime, int powerUsage) {
        this.accessTime = accessTime;
        this.executingTime = executingTime;
        this.powerUsage = powerUsage;
    }

    public Process(Process p){
        accessTime=p.getAccessTime();
        powerUsage=p.getPowerUsage();
        executingTime=p.getExecutingTime();
    }

    public int getPowerUsage() {
        return powerUsage;
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

    public boolean isDone() {
        return done;
    }

    public Process setIsDone(boolean isDone) {
        this.done = isDone;
        return this;
    }

    public boolean isDuringExecution() {
        return duringExecution;
    }

    public Process setIsDuringExecution(boolean isDuringExecution) {
        this.duringExecution = isDuringExecution;
        return this;
    }
}
