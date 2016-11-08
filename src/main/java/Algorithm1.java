import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pawel on 2015-05-03.
 */
public class Algorithm1 {
    Processor[] system ;
    ArrayList<Process> processes;
    ArrayList<Process> statisticList = new ArrayList<Process>();
    int z;

    Algorithm1( ArrayList<Process> processes, int z){
        this.processes=processes;
        this.z=z;
    }

    public void createProcessors(int processesNumber,int maxProcessorUsage){
        system = new Processor[processesNumber];
        for (int i=0; i<system.length;i++){
            system[i]=new Processor(i,maxProcessorUsage );
        }
    }

    public void generateAlgorithm(){
        ArrayList<Process> queue = new ArrayList<Process>();
        int time = 0;
        Random generator = new Random();

        while (!areDone() ){

            /*Add all processes accessed in actual time*/
            addProcesses(queue, time);

            /*Przydziel proces do procesora zgodnie z algorytmem 1*/
            assignUnassignedProcessesToProcessor(queue, generator);

            /*Zasymuluj prace nad procesami w kolejce poprzez zmniejszenie ich czasu wykonywania
            * lub zaznaczenie, �e proces jest skonczony*/
            symulateProcessorWork(queue);


            time++;
        }



    }

    private void assignUnassignedProcessesToProcessor(ArrayList<Process> queue, Random generator) {
        for (Process p : queue){
            assignProcess(generator, p);
        }
    }

    private void symulateProcessorWork(ArrayList<Process> queue) {
        for (Process process : queue){
            if (process.isDuringExecution() && !process.isDone()){
                if (process.getExecutingTime()-1>0){
                    process.setExecutingTime(process.getExecutingTime()-1);
                }
                else if (process.getExecutingTime()-1<=0){
                    process.setExecutingTime(process.getExecutingTime() - 1);
                    process.setIsDone(true);
                    process.setIsDuringExecution(false);
                    system[process.getNumberOfProcessor()].setActualUsage(system[process.getNumberOfProcessor()].getActualUsage() - process.getPowerUsage());
                    system[process.getNumberOfProcessor()].getUsageInTime().add(system[process.getNumberOfProcessor()].getActualUsage());
                    statisticList.add(process);
                }
            }
        }
    }

    private void assignProcess(Random generator, Process p) {
        if (!p.isDuringExecution() && !p.isDone()){

            int accessProcessorNumber = generator.nextInt(system.length);
            system[accessProcessorNumber].setUsageQueriesAmount( system[accessProcessorNumber].getActualUsage()+1);
            p.setMovesNumber(p.getMovesNumber()+1);
            boolean found = false;
            for (int i=0; i<z && !found ; i++){
                int  otherProcessorNumber = generator.nextInt(system.length);
                system[otherProcessorNumber].setUsageQueriesAmount(system[otherProcessorNumber].getUsageQueriesAmount()+1);


                /*TOD Procesor moze wylosowac sam siebie*/
                if (system[otherProcessorNumber].getActualUsage()<system[otherProcessorNumber].getMaxUsage()){
                    system[otherProcessorNumber].setActualUsage(system[otherProcessorNumber].getActualUsage() + p.getPowerUsage());
                    system[otherProcessorNumber].getUsageInTime().add(system[otherProcessorNumber].getActualUsage());
                    p.setNumberOfProcessor(otherProcessorNumber);
                    p.setIsDuringExecution(true);
                    found=true;
                }
                else {
                    p.setMovesNumber(p.getMovesNumber()+1);
                }


            }

            /*Jesli nie znaleziono wlnego procka to proces wykonuje sie na "pierwotnym" procesorze jesli moze*/
            if (!found && system[accessProcessorNumber].getActualUsage()+p.getPowerUsage()<100){
                    system[accessProcessorNumber].setActualUsage(system[accessProcessorNumber].getActualUsage()+p.getPowerUsage());
                    system[accessProcessorNumber].getUsageInTime().add(system[accessProcessorNumber].getActualUsage());
                    p.setMovesNumber(p.getMovesNumber()+1);
                    p.setNumberOfProcessor(accessProcessorNumber);
                    p.setIsDuringExecution(true);
            }
            else {
                p.setWaitingTime(p.getWaitingTime() + 1);
                p.setMovesNumber(p.getMovesNumber()+1);
            }
        }
    }

    private void addProcesses(ArrayList<Process> processQueue, int time) {
        for (Process p : processes){
            if (p.getAccessTime()==time){
                processQueue.add(p);
            }
        }
    }

    public void printStatistics(){
        System.out.println("Algorytm nr1 : ");
        System.out.println("Ilosc zapytan poszczegolnych procesorow : ");
        for (Processor processor : system){
            System.out.println("Procesor nr " + processor.getNumber() + " : " + processor.getUsageQueriesAmount());
        }

        System.out.println("Srednie uzycie poszczegolnych procesorow : ");
        for (Processor processor : system){
            System.out.println("Procesor nr "+processor.getNumber()+" : "+processor.averageUsage());
        }
        System.out.println("Odchylenie średniego użycia : "+averageDeviation());

        System.out.println("Srednia ilosc przemieszczen procesow : "+averageMovesNumber());
    }

    private float usageSum(){
        float sum = 0;
        for (Processor p : system){
            sum+=p.averageUsage();
        }
        return sum;
    }

    private float averageUsage(){
        return usageSum()/system.length;
    }

    public float averageDeviation(){
        float deviationSum = 0;
        float avg = averageUsage();
        for (Processor p : system){
            deviationSum+= (p.averageUsage()-avg)*(p.averageUsage()-avg);
        }

        return (float) Math.sqrt(deviationSum/system.length);
    }

    private float movesNumberSum(){
        float sum=0;
        for (Process p :processes){
            sum+=p.getMovesNumber();
        }
        return sum;
    }

    public float averageMovesNumber(){
        return movesNumberSum()/processes.size();
    }

    private boolean areDone(){
        for (Process p : processes){
            if (!(p.isDone())){
                return false;
            }
        }
        return true;
    }
}
