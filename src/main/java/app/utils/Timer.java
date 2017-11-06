package app.utils;

public class Timer {
    private long queryStartTime;
    private long queryDoneTime;
    private long duration;
    private boolean timerStarted = false;

    public void startTimer() {
        duration = 0L;
        timerStarted = true;
        queryStartTime = System.nanoTime();
    }

    public void stopTimer() {
        if (timerStarted) {
            queryDoneTime = System.nanoTime();
            duration = (queryDoneTime - queryStartTime) / 1000000;
        }
        resetTimer();
    }

    public long stopTimerAndGetQueryTimeInMiliseconds() {
        if (!timerStarted) return duration;
        else {
            stopTimer();
            return duration;
        }
    }

    public long getDuration() {
        return duration;
    }

    private void resetTimer() {
        queryStartTime = 0L;
        queryStartTime = 0L;
        timerStarted = false;
    }
}
