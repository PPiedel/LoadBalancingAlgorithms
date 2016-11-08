import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pawel on 2015-05-03.
 */
public class Algorithm2 {
    Processor[] system ;
    ArrayList<Process> processes;
    ArrayList<Process> statisticList = new ArrayList<Process>();

    Algorithm2( ArrayList<Process> processes){
        this.processes=new ArrayList<Process>(processes);
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

            /*Przydziel proces do procesora zgodnie z algorytmem 2*/
            assignUnassignedProcessesToProcessor(queue, generator);

            /*Zasymuluj prace nad procesami w kolejce poprzez zmniejszenie ich czasu wykonywania
            * lub zaznaczenie, ?e proces jest skonczony */
            symulateProcessorWork(queue);

            time++;
        }



    }

    private void assignUnassignedProcessesToProcessor(ArrayList<Process> queue, Random generator) {
        for (Process p : queue){
            assignProcess(generator, p);
        }
    }

    /*Przydziel proces do procesora wg algorytmu*/
    private void assignProcess(Random generator, Process p) {
        if (!p.isDuringExecution() && !p.isDone()){

            int accessProcNumber = generator.nextInt(system.length);
            system[accessProcNumber].setUsageQueriesAmount( system[accessProcNumber].getActualUsage()+1);

            if (system[accessProcNumber].getActualUsage() < system[accessProcNumber].getMaxUsage()){

                system[accessProcNumber].setActualUsage(system[accessProcNumber].getActualUsage() + p.getPowerUsage());
                system[accessProcNumber].getUsageInTime().add(system[accessProcNumber].getActualUsage());
                p.setNumberOfProcessor(accessProcNumber);
                p.setIsDuringExecution(true);

            }
            else if (system[accessProcNumber].getActualUsage() >= system[accessProcNumber].getMaxUsage()){

               boolean found = false;
               for(int i=0; !found && i<system.length; i++){
                   int otherProcNumber=generator.nextInt(system.length);
                   system[otherProcNumber].setUsageQueriesAmount(system[otherProcNumber].getUsageQueriesAmount() + 1);
                   //p.setMovesNumber(p.getMovesNumber()+1);
                   if (system[otherProcNumber].getActualUsage()<system[otherProcNumber].getMaxUsage()){

                        system[otherProcNumber].setActualUsage(system[otherProcNumber].getActualUsage() + p.getPowerUsage());
                        system[otherProcNumber].getUsageInTime().add(system[otherProcNumber].getActualUsage());
                        p.setMovesNumber(p.getMovesNumber()+1);
                        p.setNumberOfProcessor(otherProcNumber);
                        p.setIsDuringExecution(true);
                        found=true;
                   }
               }

            }

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

    private void addProcesses(ArrayList<Process> processQueue, int time) {
        for (Process p : processes){
            if (p.getAccessTime()==time){
                processQueue.add(p);
            }
        }
    }

    public void printStatistics(){
        System.out.println("Algorytm nr2 : ");
        System.out.println("Ilosc zapytan poszczegolnych procesorow : ");
        for (Processor processor : system){
            System.out.println("Procesor nr "+processor.getNumber()+" : "+processor.getUsageQueriesAmount());
        }

        System.out.println("Srednie uzycie poszczegolnych procesorow : ");
        for (Processor processor : system){
            System.out.println("Procesor nr "+processor.getNumber()+" : "+processor.averageUsage());
        }

        System.out.println("Odchylenie œredniego u¿ycia : "+averageDeviation());

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
