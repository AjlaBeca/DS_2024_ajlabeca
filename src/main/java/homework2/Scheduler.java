package homework2;

import java.util.ArrayList;

public class Scheduler {

    public static void scheduleAndRun(ArrayList<Process> processes) {
        ProcessQueue pq = new ProcessQueue();
        int t = 0;
        int itemsLeft = processes.size();
        float totalWaitTime = 0;
        while(true){

            String name = "no process";
            for(Process p : processes){
                if(p.getArrival()==t){
                    pq.addProcess(p);
                    itemsLeft--;
                }
            }
            if(!pq.isEmpty()){

                Process p = pq.peekNextProcess();
                int burst = p.getBurst();

                if(burst==1){
                    p = pq.runNextProcess();
                    p.setWaitTime(t+1);
                    totalWaitTime += p.getWaitTime();
                }

                p.setBurst(--burst);
                name = p.getName();
            }

            System.out.println("t: " + t + "   |   " + name);
            t++;

            if(pq.isEmpty() && itemsLeft==0){
                System.out.println("Total time: " + t);
                System.out.println("Average waiting time:" + (totalWaitTime/processes.size()));
                break;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 2, 4, 1));
        processes.add(new Process("P2", 1, 1, 2));
        processes.add(new Process("P3", 3, 2, 8));
        //processes.add(new Process("P4", 5, 6, 3));
        //processes.add(new Process("P5", 4, 5, 4));
        //processes.add(new Process("P6", 10, 15, 5));
        //processes.add(new Process("P7", 9, 8, 15));
        scheduleAndRun(processes);
    }
}