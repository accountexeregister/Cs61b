package timingtest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeSLListTest {

    @Test
    public void testGenerateSLListGetLastOps() {
        int expectedOps = 1000;
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

    public static void main(String[] args) {

    }
}
