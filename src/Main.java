import java.util.*;
import java.io.*;

public class Main {
    final static int[] JOB_SIZES = {5, 10, 15, 20, 25, 30};
    final static String FILE_PATH = "C:\\Users\\tonyp\\Documents\\CS 4310\\Project1\\src\\jobs.txt";

    public static void main(String[] args) throws Exception {
        List<Job> jobList = new ArrayList<Job>();
        readJobFile(FILE_PATH, jobList);

        double firstComeFirstServed = fcfs(new ArrayList<Job>(jobList));
        double shorstJobFirst = sjf(new ArrayList<Job>(jobList));
        double roundRobin2 = roundRobin(2, new ArrayList<Job>(jobList));
        double roundRobin5 = roundRobin(5, new ArrayList<Job>(jobList));
    }

    private static double fcfs(List<Job> jobs) {
        System.out.println("Performing First Come First Serve Scheduling...");
        Queue<Job> queue = new LinkedList<Job>(jobs);

        double turnAroundTime = 0;

        return turnAroundTime;
    }

    private static double sjf(List<Job> jobs) {
        System.out.println("Performing Shortest Job First Scheduling...");
        PriorityQueue<Job> priorityQueue = new PriorityQueue<Job>(jobs);

        double turnAroundTime = 0;

        return turnAroundTime;
    }

    private static double roundRobin(int timeSlice, List<Job> jobs) {
        System.out.println("Performing Round Robin (Time Slice: " + timeSlice + ") Scheduling...");
        double turnAroundTime = 0;

        return turnAroundTime;
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

    private static void readJobFile(String file, List<Job> jobList) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line != null) {
                String name = line;
                //read next line
                line = reader.readLine();
                int burstTime = Integer.parseInt(line);
                jobList.add(new Job(name, burstTime));

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
}