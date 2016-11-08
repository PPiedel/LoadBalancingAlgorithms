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
            processes.add(new Process(generator.nextInt(8), generator.nextInt(10), generator.nextInt(10)));
        }
        return processes;
    }

    public static void main(String[] args) {
        int procNumber = 50;
        int processNumber = 5000;
        int maxProcessorUsage = 95;
        int minProcUsage = 85;
        int z= 15;
        int m = 15;
        ArrayList<Process> processes = generateProcessSeries(processNumber);



        ArrayList<Process> al1List = new ArrayList<Process>();
        for (Process p : processes){
            al1List.add(new Process(p));
        }
        Algorithm1 algorithm1 = new Algorithm1(al1List,z );
        algorithm1.createProcessors(procNumber,maxProcessorUsage);
        algorithm1.generateAlgorithm();
        algorithm1.printStatistics();

        System.out.println("");
        System.out.println("");
        System.out.println("");

        ArrayList<Process> al2List = new ArrayList<Process>();
        for (Process p : processes){
            al2List.add(new Process(p));
        }
        Algorithm2 algorithm2 = new Algorithm2(al2List);
        algorithm2.createProcessors(procNumber,maxProcessorUsage);
        algorithm2.generateAlgorithm();
        algorithm2.printStatistics();

        System.out.println("");
        System.out.println("");
        System.out.println("");

        ArrayList<Process> al3List = new ArrayList<Process>();
        for (Process p : processes){
            al3List.add(new Process(p));
        }
        Algorithm3 algorithm3 = new Algorithm3(al3List,minProcUsage );
        algorithm3.createProcessors(procNumber,maxProcessorUsage);
        algorithm3.generateAlgorithm(m);
        algorithm3.printStatistics();


    }
}
