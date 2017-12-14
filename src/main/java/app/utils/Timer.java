package app.utils;


import java.math.BigDecimal;

public class Timer {
    private long queryStartTime;
    private long queryDoneTime;
    private long duration;
    private boolean timerStarted = false;
    private static final TimestampProvider tsProvider = new TimestampProvider();


    public void startTimer() {
        duration = 0L;
        timerStarted = true;
        queryStartTime = tsProvider.nowMicro();
    }

    public void stopTimer() {
        if (timerStarted) {
            queryDoneTime = tsProvider.nowMicro();
            duration = (queryDoneTime - queryStartTime);
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
        queryDoneTime = 0L;
        timerStarted = false;
    }


    private static class TimestampProvider {
        private static final long offset;

        static {
            final int count = 10000;
            BigDecimal offsetSum = BigDecimal.ZERO;
            for (int i = 0; i < count; i++) {
                offsetSum = offsetSum.add(BigDecimal.valueOf(calculateOffset()));
            }
            offset = (offsetSum.divide(BigDecimal.valueOf(count))).longValue();
        }

        private static long calculateOffset() {
            long nano = System.nanoTime();
            long nanoFromMilli = System.currentTimeMillis() * 1_000_000;
            return nanoFromMilli - nano;
        }

        private long nowMicro() {
            return (offset + System.nanoTime()) / 1000;
        }
    }
}