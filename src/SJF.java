import java.util.*;
import java.io.*;

public class SJF {
    final static int[] JOB_SIZES = {5, 10, 15, 20, 25, 30};
    final static String FILE_PATH = "C:\\Users\\tonyp\\Documents\\CS 4310\\Project1\\src\\jobs.txt";

    public static void main(String[] args) throws Exception {
        List<Job> jobList = new ArrayList<Job>();
        readJobFile(FILE_PATH, jobList);

        sjf(jobList);
    }

    private static void sjf(List<Job> jobs) {
        System.out.println("Performing Shortest Job First Scheduling...");
        PriorityQueue<Job> priorityQueue = new PriorityQueue<Job>(jobs);
        jobs.clear();
        int numJobs = priorityQueue.size();

        float totalTime = 0;
        int timer = 0;

        for(int i = 0; i < numJobs; ++i) {
            Job currentJob = priorityQueue.poll();
            currentJob.setStartTime(timer);
            timer += currentJob.getBurstTime();
            currentJob.setEndTime(timer);
            totalTime += currentJob.getEndTime();
            jobs.add(currentJob);
        }

        printScheduleTable(jobs, totalTime / (float)numJobs);
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

    private static void printScheduleTable(List<Job> jobList, float averageTurnAroundTime) {
        System.out.println("Job Name\tStart Time\tEnd Time\tJob Status");
        for(Job j : jobList) {
            System.out.printf("%s\t\t%d\t\t%d\t\t%s completed at time %d\n", j.getName(), j.getStartTime(), j.getEndTime(), j.getName(), j.getEndTime());
        }
        System.out.println("Average turn around time is: " + averageTurnAroundTime);
    }
}
