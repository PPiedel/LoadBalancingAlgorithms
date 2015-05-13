import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pawel on 2015-05-03.
 */
public class Algorithm3 {
    Processor[] system ;
    ArrayList<Process> processes;
    ArrayList<Process> statisticList = new ArrayList<Process>();

    Algorithm3( ArrayList<Process> processes){
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
            symulateWorkingOnProcesses(queue);


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

            if (system[accessProcNumber].getActualUsage() < system[accessProcNumber].getMaxUsage()){

                system[accessProcNumber].setActualUsage(system[accessProcNumber].getActualUsage() + p.getPowerUsage());
                system[accessProcNumber].getUsageInTime().add(system[accessProcNumber].getActualUsage());
                system[accessProcNumber].setUsageQueriesAmount(system[accessProcNumber].getUsageQueriesAmount() + 1);
                p.setNumberOfProcessor(accessProcNumber);
                p.setIsDuringExecution(true);

            }
            else {
                boolean found = false;
                int i=0;
                while (!found && i<system.length){
                    int otherProcNumber=generator.nextInt(system.length);
                    if (system[otherProcNumber].getActualUsage()<system[otherProcNumber].getMaxUsage()){
                        system[otherProcNumber].setActualUsage(system[otherProcNumber].getActualUsage()+p.getPowerUsage());
                        system[otherProcNumber].getUsageInTime().add(system[otherProcNumber].getActualUsage());
                        system[otherProcNumber].setUsageQueriesAmount(system[otherProcNumber].getUsageQueriesAmount() + 1);
                        p.setNumberOfProcessor(otherProcNumber);
                        p.setIsDuringExecution(true);
                        found=true;
                    }
                    else {
                        system[otherProcNumber].setUsageQueriesAmount(system[otherProcNumber].getUsageQueriesAmount()+1);

                    }
                    i++;
                }

            }


        }
    }

    private void symulateWorkingOnProcesses(ArrayList<Process> queue) {
        for (Process process : queue){
            if (process.isDuringExecution() && !process.isDone()){
                if (process.getExecutingTime()-1>0){
                    process.setExecutingTime(process.getExecutingTime()-1);
                }
                else if (process.getExecutingTime()-1<=0){
                    process.setIsDone(true);
                    process.setIsDuringExecution(false);
                    system[process.getNumberOfProcessor()].setActualUsage(system[process.getNumberOfProcessor()].getActualUsage() - process.getPowerUsage());
                    system[process.getNumberOfProcessor()].getUsageInTime().add(system[process.getNumberOfProcessor()].getActualUsage());
                    process.setExecutingTime(process.getExecutingTime() - 1);
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
            System.out.println("Procesor nr "+processor.getNumber()+" : "+processor.averageProcessorUsage());
        }

        /*System.out.println("Ilosc przemieszczeñ poszczegonych procesow : ");
        for (int i=0; i<processes.size();i++){
            System.out.println("Proces nr "+i+ " : "+processes.get(i).getMovesNumber());
        }*/

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
