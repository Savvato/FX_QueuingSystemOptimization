package system;

public class Request implements Comparable<Request>
{
    private long startQueueTime;

    private long endQueueTime;

    private long startServiceTime;

    private long finishServiceTime;

    public long getStartQueueTime() {
        return startQueueTime;
    }

    public void setStartQueueTime(long startQueueTime) {
        this.startQueueTime = startQueueTime;
    }

    public long getEndQueueTime() {
        return endQueueTime;
    }

    public void setEndQueueTime(long endQueueTime) {
        this.endQueueTime = endQueueTime;
    }

    public long getStartServiceTime() {
        return startServiceTime;
    }

    public void setStartServiceTime(long startServiceTime) {
        this.startServiceTime = startServiceTime;
    }

    public long getFinishServiceTime() {
        return finishServiceTime;
    }

    public void setFinishServiceTime(long finishServiceTime) {
        this.finishServiceTime = finishServiceTime;
    }

    @Override
    public int compareTo(Request r) {
        if (this.getFinishServiceTime() < r.getFinishServiceTime()) {
            return -1;
        }
        if (this.getFinishServiceTime() > r.getFinishServiceTime()) {
            return 1;
        }
        return 0;
    }

    public long getTimeInSystem() {
        return this.finishServiceTime - this.startQueueTime;
    }

    public void log() {
        String string = ("SQ: " + this.startQueueTime) +
                " SS: " + this.startServiceTime +
                " FS: " + this.finishServiceTime;
        System.out.println(string);
    }
}
