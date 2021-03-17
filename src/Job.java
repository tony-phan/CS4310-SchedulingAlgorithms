public class Job implements Comparable<Job>, Cloneable {
    
    private String name;
    private int burstTime;
    private int startTime;
    private int endTime;
    
    public Job(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void decrementBurstTime(int timeSlice) {
        this.burstTime -= timeSlice;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
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