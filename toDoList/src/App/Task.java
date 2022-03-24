
package App;

public class Task {
    private String name;
    int priority;
    private boolean isDone;
    public static final int LOW_IMPORTANCE = 0;
    public static final int MEDIUM_IMPORTANCE = 1;
    public static final int HIGH_IMPORTANCE = 2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public int getLOW_IMPORTANCE() {
        return LOW_IMPORTANCE;
    }

    public int getMEDIUM_IMPORTANCE() {
        return MEDIUM_IMPORTANCE;
    }

    public int getHIGH_IMPORTANCE() {
        return HIGH_IMPORTANCE;
    }
    
    public Task(String name,int priority){
        this.name = name;
        this.priority = priority;
        this.isDone = false;
    }
}