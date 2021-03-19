import java.util.*;
import java.io.*;

public class RoundRobin2 {
    final static int[] JOB_SIZES = {5, 10, 15};
    final static String FILE_PATH = "C:\\Users\\tonyp\\Documents\\CS 4310\\Project1\\src\\jobs.txt";
    final static int TIME_SLICE = 2;

    public static void main(String[] args) throws Exception {
        for(int size : JOB_SIZES) {
            generateRandomJobsInFile(size);
            List<Job> jobList = new ArrayList<Job>();
            readJobFile(FILE_PATH, jobList);

            roundRobin(TIME_SLICE, jobList);
        }
    }

    private static void roundRobin(int timeSlice, List<Job> jobs) {
        System.out.println("Performing Round-Robin (Time Slice: " + timeSlice + ") Scheduling...");
        System.out.println("Job Name\tStart Time\tEnd Time\tJob Status");

        Queue<Job> queue = new LinkedList<Job>(jobs);
        int numJobs = queue.size();

        float totalTime = 0;
        int timer = 0;
        while(!queue.isEmpty()) {
            Job currentJob = queue.poll();
            currentJob.setStartTime(timer);
            if(currentJob.getBurstTime() - timeSlice < 0) {
                timer += currentJob.getBurstTime();
                currentJob.setEndTime(timer);
                currentJob.setBurstTime(0);
                totalTime += currentJob.getEndTime();
                jobs.add(currentJob);
            }
            else if(currentJob.getBurstTime() - timeSlice == 0) {
                timer += timeSlice;
                currentJob.setEndTime(timer);
                currentJob.setBurstTime(0); 
                totalTime += currentJob.getEndTime();
                jobs.add(currentJob);
            }
            else {
                timer += timeSlice;
                currentJob.setEndTime(timer);
                currentJob.decrementBurstTime(timeSlice);
                queue.add(currentJob);
            }

            if(currentJob.getBurstTime() <= 0) {
                System.out.printf("%s\t\t%d\t\t%d\t\t%s completed at time %d\n", currentJob.getName(), currentJob.getStartTime(), currentJob.getEndTime(), currentJob.getName(), currentJob.getEndTime());
            }
            else {
                System.out.printf("%s\t\t%d\t\t%d\n", currentJob.getName(), currentJob.getStartTime(), currentJob.getEndTime());
            }
        }
        System.out.println("Average turn around time is: " + totalTime / (float)numJobs);        
    }

    private static void generateRandomJobsInFile(int numJobs) {
        try {
            FileWriter writer = new FileWriter("jobs.txt");
            for(int i = 0; i < numJobs; ++i) {
                writer.write("Job" + (i + 1) + "\n");
                int jobLength = (int)(Math.random() * 30 + 1);
                writer.write(String.valueOf(jobLength));
                if(i + 1 < numJobs) {
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    private static void readJobFile(String file, List<Job> jobs) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line != null) {
                String name = line;
                //read next line
                line = reader.readLine();
                int burstTime = Integer.parseInt(line);
                jobs.add(new Job(name, burstTime));

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
}