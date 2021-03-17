public class Job {
    
    private String name;
    private int burstTime;
    
    public Job(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public String toString() {
        return name + "," + burstTime;
    }
}
