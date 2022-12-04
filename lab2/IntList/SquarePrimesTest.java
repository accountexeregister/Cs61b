package IntList;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesNegative() {
        IntList lst = IntList.of(14, 15, 16, -17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> -17 -> 18", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesMoreThanOne1() {
        IntList lst = IntList.of(5, 14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("25 -> 14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesMoreThanOne2() {
        IntList lst = IntList.of(10, 5, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("10 -> 25 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

}
