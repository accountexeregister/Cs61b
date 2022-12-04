package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        for (int i = 0; i < 3; i++) {
            correctList.addLast(i);
            buggyAList.addLast(i);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(correctList.removeLast(), buggyAList.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggyL = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggyL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int lSize = L.size();
                int buggyLSize = buggyL.size();
                assertEquals(lSize, buggyLSize);
            } else {
                if (L.size() == 0 || buggyL.size() == 0) {
                    continue;
                }
                if (operationNumber == 2) {
                    int lLast = L.getLast();
                    int buggyLLast = buggyL.getLast();
                    assertEquals(buggyLLast, lLast);
                } else {
                    int lLastRemoved = L.removeLast();
                    int buggyLLastRemoved = buggyL.removeLast();
                    assertEquals(lLastRemoved, buggyLLastRemoved);
                }
            }
        }
    }
}
