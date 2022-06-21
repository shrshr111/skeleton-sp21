package timingtest;
import edu.princeton.cs.algs4.Stopwatch;
import java.lang.Math;
/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(SLList<Integer> Ns, SLList<Double> times, SLList<Integer> opCounts) {
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
        SLList sizes = new SLList();
        SLList times = new SLList();
        SLList ops = new SLList();
        int j = 0;
        int n;
        int op = 10000;
        while (j < 8) {
            n = (int) (1000 * (Math.pow(2, j)));
            SLList L = new SLList();
            int i = 0;
            while (i < n) {
                L.addLast(i);
                i += 1;
            }
            Stopwatch sw = new Stopwatch();
            int M = 0;
            while (M < op) {
                L.getLast();
                M += 1;
            }
            double timeInSeconds = sw.elapsedTime();

            times.addLast(timeInSeconds);
            sizes.addLast(L.size());
            ops.addLast(op);

            j += 1;
        }
        TimeSLList.printTimingTable(sizes, times, ops);
    }

}
