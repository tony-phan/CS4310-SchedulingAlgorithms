public class Job implements Comparable<Job> {
    
    private String name;
    private int burstTime;
    private int startTime;
    private int endTime;
    
    public Job(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    @Override
    public String toString() {
        return name + "," + burstTime;
    }

    //compare the burst time of the job with another job
    @Override
    public int compareTo(Job job) {
        // TODO Auto-generated method stub
        if(this.burstTime < job.burstTime) {
            return -1;
        }
        else if(this.burstTime == job.burstTime) {
            return 0;
        }
        else {
            return 1;
        }
    }
}