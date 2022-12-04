package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for (int n = 1000; n <= 1000000; n *= 2) {
            ns.addLast(n);
            Stopwatch s = new Stopwatch();
            int ops = generateList(n);
            times.addLast(s.elapsedTime());
            opCounts.addLast(ops);
        }
        printTimingTable(ns, times, opCounts);
    }

    public static int generateList(int n) {
        int operations = 0;
        AList<Integer> aList = new AList<>();
        for (int i = 0; i < n; i++) {
            aList.addLast(i);
            operations++;
        }
        return operations;
    }

}
