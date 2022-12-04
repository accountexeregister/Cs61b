package timingtest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeAListTest {

    @Test
    public void testGenerateList() {
        int expectedOps1 = 100;
        int actualOps1 = TimeAList.generateList(100);
        int expectedOps2 = 1000;
        int actualOps2 = TimeAList.generateList(1000);
        int expectedOps3 = 10000;
        int actualOps3 = TimeAList.generateList(10000);
        assertEquals(expectedOps1, actualOps1);
        assertEquals(expectedOps2, actualOps2);
        assertEquals(expectedOps3, actualOps3);

    }

    public static void main(String[] args) {

    }
}
