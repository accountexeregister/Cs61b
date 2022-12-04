package timingtest;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TimeSLListTest {

    @Test
    public void testGenerateSLListGetLastOps() {
        int expectedOps = 10000;
        SLList<Integer> list1 = TimeSLList.generateSLList(1000);
        int actualOps1 = TimeSLList.countGetLastOps(list1);
        assertEquals(expectedOps, actualOps1);
        SLList<Integer> list2 = TimeSLList.generateSLList(2000);
        int actualOps2 = TimeSLList.countGetLastOps(list2);
        assertEquals(expectedOps, actualOps2);
        SLList<Integer> list3 = TimeSLList.generateSLList(4000);
        int actualOps3 = TimeSLList.countGetLastOps(list3);
        assertEquals(expectedOps, actualOps3);
    }

    @Test
    public void countGetLastTime() {
        double maxTime = 30.0;
        Stopwatch s;
        SLList<Integer> list1 = TimeSLList.generateSLList(1000);
        s = new Stopwatch();
        TimeSLList.countGetLastOps(list1);
        assertTrue(s.elapsedTime() <= maxTime);
        SLList<Integer> list2 = TimeSLList.generateSLList(16000);
        s = new Stopwatch();
        TimeSLList.countGetLastOps(list2);
        assertTrue(s.elapsedTime() <= maxTime);
        SLList<Integer> list3 = TimeSLList.generateSLList(128000);
        s = new Stopwatch();
        TimeSLList.countGetLastOps(list3);
        assertTrue(s.elapsedTime() <= maxTime);
    }

    public static void main(String[] args) {

    }
}
