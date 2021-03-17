import java.util.*;

import javax.print.attribute.standard.JobSheets;

import java.io.*;

public class Main {
    final static int[] JOB_SIZES = {5, 10, 15};
    public static void main(String[] args) throws Exception {
        List<Job> jobList = new ArrayList<Job>();
        for(int jobSize : JOB_SIZES) {
            generateRandomJobsInFile(jobSize);
            readJobFile(jobList);

            double firstComeFirstServed = fcfs(new ArrayList<Job>(jobList));
            double shorstJobFirst = sjf(new ArrayList<Job>(jobList));
            double roundRobin2 = roundRobin(2, new ArrayList<Job>(jobList));
            double roundRobin5 = roundRobin(5, new ArrayList<Job>(jobList));
            
            jobList.clear();
        }
    }

    private static double fcfs(List<Job> jobs) {
        double turnAroundTime = 0;

        return turnAroundTime;
    }

    private static double sjf(List<Job> jobs) {
        jobs = sortJobs(jobs);

        double turnAroundTime = 0;

        return turnAroundTime;
    }

    private static double roundRobin(int timeSlice, List<Job> jobs) {
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

    private static void readJobFile(List<Job> jobList) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("jobs.txt"));
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

    private static List<Job> sortJobs(List<Job> jobList) {
        Job[] jobArray = jobList.toArray(new Job[0]);
        mergeSort(jobArray, jobArray.length);

        return Arrays.asList(jobArray);
    }

    private static void mergeSort(Job[] jobList, int len) {
        if(len < 2) {
            return;
        }

        int mid = len / 2;
        Job[] left = new Job[mid];
        Job[] right = new Job[len - mid];
        for(int i = 0; i < mid; ++i) {
            left[i] = jobList[i];
        }
        for(int i = mid; i < len; ++i) {
            right[i - mid] = jobList[i];
        }

        mergeSort(left, mid);
        mergeSort(right, len - mid);

        merge(jobList, left, right, mid, len - mid);
    }
    private static void merge(Job[] jobList, Job[] leftSubarray, Job[] rightSubarray, int left, int right) {
        int i = 0, j = 0, k = 0;
        while(i < left && j < right) {
            if(leftSubarray[i].getBurstTime() <= rightSubarray[j].getBurstTime()) {
                jobList[k++] = leftSubarray[i++];
            }
            else {
                jobList[k++] = rightSubarray[j++];
            }
        }
        while(i < left) {
            jobList[k++] = leftSubarray[i++];
        }
        while(j < right) {
            jobList[k++] = rightSubarray[j++];
        }
    }
}