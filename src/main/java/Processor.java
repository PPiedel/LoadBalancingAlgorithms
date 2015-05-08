import java.util.ArrayList;

/**
 * Created by Pawel on 2015-05-03.
 */
public class Processor {
    private final int number;
    private int actualUsage;
    private int usageQueriesAmount = 0;
    private ArrayList<Integer> usageInTime = new ArrayList<Integer>();

    public Processor(int number, int actualUsage) {
        this.number = number;
        this.actualUsage = actualUsage;
    }

    public double averageProcessLoad(){
        return queriesSum()/usageInTime.size();
    }

    private int queriesSum(){
        int usagesSummary = 0;
        for (Integer usage : usageInTime){
            usagesSummary+=usage;
        }
        return usagesSummary;
    }

    public int getActualUsage() {
        return actualUsage;
    }

    public void setActualUsage(int actualUsage) {
        this.actualUsage = actualUsage;
    }

    public int getNumber() {
        return number;
    }

    public int getUsageQueriesAmount() {
        return usageQueriesAmount;
    }

    public Processor setUsageQueriesAmount(int usageQueriesAmount) {
        this.usageQueriesAmount = usageQueriesAmount;
        return this;
    }

    public ArrayList<Integer> getUsageInTime() {
        return usageInTime;
    }
}
