package app.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TimerTests {

    private Timer timer;

    @Before
    public void before(){
        timer = new Timer();
    }

    @Test
    public void testCorrectDurationResult() throws InterruptedException {
            timer.startTimer();
            long durationBeforeTimerWasStopped = timer.getDuration();
            Thread.sleep(10);
            timer.stopTimer();

            Assert.assertEquals(0L, durationBeforeTimerWasStopped);
            Assert.assertTrue(timer.getDuration() >= 10000L);
            Assert.assertEquals(timer.getDuration(), timer.stopTimerAndGetQueryTimeInMiliseconds());
            Assert.assertEquals(timer.getDuration(), timer.stopTimerAndGetQueryTimeInMiliseconds());
    }

    @Test
    public void testDurationMeasurementError() throws InterruptedException {
            long totalExecutionTime = 0L;
            int iterations = 100;
            int sleepDurationMilis = 10;
            for (int i = 1; i <= iterations; i++) {
                timer.startTimer();
                Thread.sleep(sleepDurationMilis);
                totalExecutionTime += timer.stopTimerAndGetQueryTimeInMiliseconds();

            }

            long expectedMinError = 0L; //microseconds
            long expectedMaxError = 2000L; //microseconds

            long avgDuration = totalExecutionTime / iterations;
            long measurementError = avgDuration - (sleepDurationMilis * 1000);
            System.out.printf("Measurement error was: %d microseconds, expected to be between %d and %d microseconds.\n",
                    measurementError, expectedMinError, expectedMaxError);

            Assert.assertTrue(measurementError >= expectedMinError);
            Assert.assertTrue(measurementError <= expectedMaxError);

    }
}
