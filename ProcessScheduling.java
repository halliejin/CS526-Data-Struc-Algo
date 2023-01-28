import net.datastructures.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class ProcessScheduling {

    public static void main(String[] args) throws IOException {

        // create a new file to record the output
        FileWriter myWriter = new FileWriter("process_scheduling_output.txt");

        // create an arraylist to store the input processes
        List<Process> processItems = new ArrayList<Process>();

        File file = new File("D:\\0 Hallie Jin\\0 Hallie\\0 CS\\0 BU CS526 Data Stucture and Algorithms\\Assignments\\Term Project\\process_scheduling_input.txt");
        Scanner inputScanner = new Scanner(file);

        // set three variables to receive the value we scanned and split
        int id0, priority0, duration0, arrivalTime0;

        // read the file line by line and get all inputs
        while (inputScanner.hasNextLine()){
            String lineInput = inputScanner.nextLine();
            String[] splitLines = lineInput.split(" ");
            id0 = Integer.parseInt(splitLines[0]);

            // trim the strings after split them and then cast the variable type
            priority0 = Integer.parseInt(splitLines[1].trim());
            duration0 = Integer.parseInt(splitLines[2].trim());
            arrivalTime0 = Integer.parseInt(splitLines[3].trim());


            // pass the four values to the new ProcessItems object created
            // the runTimeLeft is the same as duration but is just used for counting
            // here, pass duration0 to runTimeLeft variable
            Process pi = new Process(id0, priority0, duration0, arrivalTime0, duration0, 0);
            processItems.add(pi);
            // print all processes
            System.out.println(pi);

            // write to the myWriter file
            myWriter.write(pi.toString() );
            myWriter.write("\n");
        }

        // close the scanner
        inputScanner.close();
        System.out.println();
        myWriter.write("\n");

        // override the compareTo method from the Process class
        // sort the arraylist by arrival time
        Collections.sort(processItems);

        int processCnt = processItems.size();


        // create an empty priority queue Q
        HeapAdaptablePriorityQueue<Integer, Process> priorityQueue = new HeapAdaptablePriorityQueue<>();

        // set the maximum wait time
        int maxWaitTime = 30;
        System.out.println("Maximum wait time = " + maxWaitTime);
        System.out.println();

        // write to file
        myWriter.write("Maximum wait time = " + maxWaitTime);
        myWriter.write("\n");

        // initialize currentTime as a clock
        int currentTime = 0;
        // initialize wait time per process
        Double waitTime = 0.0;
        // initialize total wait time for average wait time calculation
        long totalWaitTime = 0;
        // initialize the time left for completing the process
        int timeLeft = 0;
        // initialize the id check variable
        int prevIdCheck = 0;



        // start the while loop
        // the outer loop serves as a clock by increase currentTime by 1 when execute each loop
        while (!priorityQueue.isEmpty() || !processItems.isEmpty()){
            // initialize a new object p
            Process p = new Process();

            if (!processItems.isEmpty()){
                // get the first item from the sorted arraylist and pass it to an initialized p
                p = processItems.get(0);

                // when the arrival time equals to the current time, add p to the PQ
                if (p.getArrivalTime() == currentTime){
                    priorityQueue.insert(p.getPriority(), p);

                    // remove the first item
                    // keep the first item of arraylist always be the one that is about to pass to p and add to PQ
                    processItems.remove(0);
                }
            }

            // initialize a new process object that represents the running process
            Process currentProcess = new Process();



            // if priority queue is not empty
            if (!priorityQueue.isEmpty()){

                // get the process that is about to run
                currentProcess = priorityQueue.min().getValue();
                timeLeft = currentProcess.getRunTimeLeft();
                currentProcess.setRunTimeLeft(currentProcess.getRunTimeLeft() - 1);

                // check if the id of the process executing from the previous loop is the same as the current one
                int currentIdCheck = currentProcess.getId();


                // using id check to print out the process messages
                if (prevIdCheck != currentIdCheck) {
                    System.out.println("Now running Process id = " + currentProcess.getId());
                    System.out.println("Arrival = " + currentProcess.getArrivalTime());
                    System.out.println("Duration = " + currentProcess.getDuration());
                    System.out.println("Run time left = " + timeLeft + "\n\t at time " + currentTime);

                    // write to file
                    myWriter.write("Now running Process id = " + currentProcess.getId() + "\n");
                    myWriter.write("Arrival = " + currentProcess.getArrivalTime() + "\n");
                    myWriter.write("Duration = " + currentProcess.getDuration() + "\n");
                    myWriter.write("Run time left = " + timeLeft + "\n\t at time " + currentTime + "\n");
                }
                //execute the top process in the priority queue
                System.out.println("Executed process ID: " + currentProcess.getId() +
                                                ", at time " + currentTime +
                                                " Remaining: " + currentProcess.getRunTimeLeft());

                // write to file
                myWriter.write("Executed process ID: " + currentProcess.getId() +
                                                ", at time " + currentTime +
                                                " Remaining: " + currentProcess.getRunTimeLeft() + "\n");




                // loop through the PQ to lower the priorities for all the processes that reach the maxWaitTime
                for (Entry<Integer, Process> pro: priorityQueue){

                    // create an arraylist to record all processes in PQ
                    ArrayList<Process> updatedWaitTimes = new ArrayList<>();
                    updatedWaitTimes.add(pro.getValue());

                    for (Process i: updatedWaitTimes){
                        if (currentIdCheck != i.getId()){
                            i.setWaitTime(i.getWaitTime() + 1);
                            waitTime += 1;
                        }

                        // find the objects that reach the maxWaitTime
                        if (i.getWaitTime() == maxWaitTime){

                            // update priorities of processes that have been waiting longer than max wait time
                            priorityQueue.replaceKey(pro, i.getPriority() - 1);

                            System.out.println("Process " + i.getId() +
                                    " reached maximum wait time... " +
                                    "decreasing priority to " + (i.getPriority() - 1));

                            // write to the file
                            myWriter.write("Process " + i.getId() +
                                    " reached maximum wait time... " +
                                    "decreasing priority to " + (i.getPriority() - 1) + "\n");

                            // update the priority
                            i.setPriority(i.getPriority() - 1);
                            // reset the waitTime
                            i.setWaitTime(0);
                        }

                        // update the object with a new runTimeLeft
                        priorityQueue.replaceValue(pro, i);
                    }
                }

                // when the current running process reaches the duration
                // remove it from PQ and print the complete message
                if (currentProcess.getRunTimeLeft() == 0){
                    System.out.println("Finish running Process id = " + currentProcess.getId());
                    System.out.println("Arrival = " + currentProcess.getArrivalTime());
                    System.out.println("Duration = " + currentProcess.getDuration());
                    System.out.println("Run time left = " + (timeLeft - 1) + "\n\t at time " + currentTime);
                    System.out.println();

                    // write to file
                    myWriter.write("Finish running Process id = " + currentProcess.getId() + "\n");
                    myWriter.write("Arrival = " + currentProcess.getArrivalTime() + "\n");
                    myWriter.write("Duration = " + currentProcess.getDuration() + "\n");
                    myWriter.write("Run time left = " + (timeLeft - 1) + "\n\t at time " + currentTime + "\n");

                    priorityQueue.removeMin();
                }

            }


            // record the id of the current loop and save for id check operation in next loop
            prevIdCheck = currentProcess.getId();

            // add the counter by 1
            currentTime++;
        }

        // calculate the average wait time
        Double averageWaitTime = (waitTime / processCnt);
        System.out.println("Finished running all processes at time " + (currentTime - 1));
        System.out.println("Average wait time: " + averageWaitTime);

        // write to file
        myWriter.write("Finished running all processes at time " + (currentTime - 1) + "\n");
        myWriter.write("Average wait time: " + averageWaitTime + "\n");

        myWriter.close();

    }
}
