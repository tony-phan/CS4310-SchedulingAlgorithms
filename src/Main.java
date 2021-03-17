import java.util.*;
import java.io.*;

public class Main {
    final static int[] JOB_SIZES = {5, 10, 15, 20, 25, 30};
    final static String FILE_PATH = "C:\\Users\\tonyp\\Documents\\CS 4310\\Project1\\src\\jobs.txt";

    public static void main(String[] args) throws Exception {
        List<Job> jobList = new ArrayList<Job>();
        readJobFile(FILE_PATH, jobList);

        //fcfs(new ArrayList<Job>(jobList));
        //sjf(new ArrayList<Job>(jobList));
        //roundRobin(2, new ArrayList<Job>(jobList));
        roundRobin(5, new ArrayList<Job>(jobList));
    }

    private static void fcfs(List<Job> jobs) {
        System.out.println("Performing First Come First Serve Scheduling...");
        Queue<Job> queue = new LinkedList<Job>(jobs);
        int numJobs = queue.size();

        float totalTime = 0;
        int timer = 0;

        for(int i = 0; i < numJobs; ++i) {
            Job currentJob = queue.poll();
            currentJob.setStartTime(timer);
            timer += currentJob.getBurstTime();
            currentJob.setEndTime(timer);
            totalTime += currentJob.getEndTime();
        }

        printScheduleTable(jobs, totalTime / (float)numJobs);
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

    private static void roundRobin(int timeSlice, List<Job> jobs) {
        System.out.println("Performing Round-Robin (Time Slice: " + timeSlice + ") Scheduling...");
        Queue<Job> queue = new LinkedList<Job>(jobs);
        int numJobs = queue.size();
        jobs.clear();

        float totalTime = 0;
        int timer = 0;
        while(!queue.isEmpty()) {
            Job currentJob = queue.poll();
            if(currentJob.getBurstTime() - timeSlice < 0) {
                timer += currentJob.getBurstTime();
                currentJob.setEndTime(timer);
                totalTime += currentJob.getEndTime();
            }
            else if(currentJob.getBurstTime() - timeSlice == 0) {
                timer += timeSlice;
                currentJob.setEndTime(timer);
                totalTime += currentJob.getEndTime();
            }
            else {
                timer += timeSlice;
                currentJob.decrementBurstTime(timeSlice);
                currentJob.setEndTime(timer);
                queue.add(currentJob);
            }
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
        System.out.println("Average turn around time is: " + averageTurnAroundTime);
    }
}