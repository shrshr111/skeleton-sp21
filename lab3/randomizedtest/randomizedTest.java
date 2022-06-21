package randomizedtest;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class randomizedTest {
@Test
    public void randomizedTest()

    {

        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                //System.out.println("addLastL(" + randVal + ")");
                L2.addLast(randVal);
                //System.out.println("addLastL2(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("sizeL: " + size);



                int size2 = L2.size();
                System.out.println("sizeL2: " + size2);

                assertEquals(size,size2);

                    if (L.size() > 0 && L2.size()>0) {
                    assertEquals(L.getLast(),L2.getLast());
                    assertEquals(L.removeLast(),L2.removeLast());
                }

            }
        }
    }
}