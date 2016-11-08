import java.util.ArrayList;

/**
 * Created by Pawel on 2015-05-03.
 */
public class Processor {
    private final int number;
    private int actualUsage=0;
    private int usageQueriesAmount = 0;
    private ArrayList<Integer> usageInTime = new ArrayList<Integer>();
    private int maxUsage;

    public Processor(int number, int maxUsage) {
        this.number = number;
        this.maxUsage = maxUsage;
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

    public Processor setUsageInTime(ArrayList<Integer> usageInTime) {
        this.usageInTime = usageInTime;
        return this;
    }

    public int getMaxUsage() {
        return maxUsage;
    }

    public Processor setMaxUsage(int maxUsage) {
        this.maxUsage = maxUsage;
        return this;
    }

    public float averageUsage(){
        return sumUsage()/usageInTime.size();
    }

    private int  sumUsage(){
        int sum=0;
        for (Integer e : usageInTime){
            sum+=e;
        }
        return sum;
    }


}
