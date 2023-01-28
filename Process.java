public class Process implements Comparable<Process>{
    private int id;
    private int priority;
    private int duration;
    private int arrivalTime;
    private int runTimeLeft;
    private int waitTime;

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getRunTimeLeft() {
        return runTimeLeft;
    }

    public void setRunTimeLeft(int runTimeLeft) {
        this.runTimeLeft = runTimeLeft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Process(){

    }
    public Process(int id, int priority, int duration, int arrivalTime){
        this.setId(id);
        this.setPriority(priority);
        this.setDuration(duration);
        this.setArrivalTime(arrivalTime);
    }

    public Process(int id, int priority, int duration, int arrivalTime, int runTimeLeft, int waitTime){
        this.setId(id);
        this.setPriority(priority);
        this.setDuration(duration);
        this.setArrivalTime(arrivalTime);
        this.setRunTimeLeft(runTimeLeft);
        this.setWaitTime(waitTime);
    }


    // override the compareTo method based on arrival time
    @Override
    public int compareTo(Process compareProc){
        int compareArrivalTime = ((Process) compareProc).getArrivalTime();
        // sort in ascending order
        return this.arrivalTime - compareArrivalTime;
    }

    public String toString() {
        String s =
                "Id = " + getId() + ", " +
                        "priority = " + getPriority() + ", " +
                        "duration = " + getDuration() + ", " +
                        "arrival time = " + getArrivalTime();
        return s;
    }


}
