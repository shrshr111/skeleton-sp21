package timingtest;
import edu.princeton.cs.algs4.Stopwatch;
import java.lang.Math;
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
        AList sizes = new AList();
        AList times = new AList();
        int j = 0;
        int n;
            while (j < 8) {
                n = (int) (1000 * (Math.pow(2, j)));
                AList L = new AList();
                int i = 0;
                Stopwatch sw = new Stopwatch();
                while (i < n) {
                    L.addLast(i);
                    i += 1;
                }
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
                sizes.addLast(L.size());
                j = j + 1;
            }
            TimeAList.printTimingTable(sizes, times, sizes);
    }
}
