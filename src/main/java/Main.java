import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pawel on 2015-05-03.
 */
public class Main {

    public static ArrayList<Process> generateProcessSeries(int processesNumber){
        ArrayList<Process> processes = new ArrayList<Process>();
        Random generator = new Random();
        for (int i=0; i<processesNumber; i++){
            processes.add(new Process(generator.nextInt(100), generator.nextInt(30), generator.nextInt(50)));
        }
        return processes;
    }

    public static void main(String[] args) {
        int processorsNumber = 7;
        int processNumber = 100;
        ArrayList<Process> processes = generateProcessSeries(processNumber);


        Algorithm1 algorithm1 = new Algorithm1(processorsNumber,processes);
        algorithm1.generateAlgorithm();
        algorithm1.printStatistics();

        Algorithm2 algorithm2 = new Algorithm2(processorsNumber,processes);
        algorithm2.generateAlgorithm();
        algorithm2.printStatistics();

        Algorithm3 algorithm3 = new Algorithm3(processorsNumber,processes);
        algorithm3.generateAlgorithm();
        algorithm3.printStatistics();


    }
}
