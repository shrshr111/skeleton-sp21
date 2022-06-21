package randomizedtest;
import org.junit.Assert.*;
import org.junit.Test;
import timingtest.AList;

import javax.accessibility.AccessibleStateSet;

import java.sql.BatchUpdateException;

import static org.junit.Assert.*;

public class testThreeAddThreeRemove {

    @Test
    public void testThreeAdd(){
        AListNoResizing<Integer> L1 = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

            L1.addLast(4);
            L2.addLast(4);
            L1.addLast(5);
            L2.addLast(5);
            L1.addLast(6);
            L2.addLast(6);

        assertEquals(L1.getLast(), L2.getLast());
        }

    @Test
    public void testRemove() {
        AListNoResizing<Integer> L1 = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        L1.addLast(4);
        L2.addLast(4);
        L1.addLast(5);
        L2.addLast(5);
        L1.addLast(6);
        L2.addLast(6);
        assertEquals(L1.removeLast(), L2.removeLast());
        //assertEquals();
    }
}
