package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for (int n = 1000; n <= 128000; n *= 2) {
            ns.addLast(n);
            SLList<Integer> currentList = generateSLList(n);
            Stopwatch s = new Stopwatch();
            int ops = countGetLastOps(currentList);
            double elapsedTime = s.elapsedTime();
            times.addLast(elapsedTime);
            opCounts.addLast(ops);
        }
        printTimingTable(ns, times, opCounts);
    }

    public static SLList<Integer> generateSLList(int n) {
        SLList<Integer> sLList = new SLList<>();
        for (int i = 0; i < n; i++) {
            sLList.addLast(i);
        }
        return sLList;
    }

    public static int countGetLastOps(SLList<Integer> slList) {
        int operations = 0;
        for (int i = 0; i < 10000; i++) {
            slList.getLast();
            operations++;
        }
        return operations;
    }

}
