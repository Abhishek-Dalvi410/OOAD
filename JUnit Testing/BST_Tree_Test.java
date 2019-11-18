import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BST_Tree_Test {

	static Tree tr;
	static TreeSet<Integer> ts = new TreeSet<Integer>();
	static Random r = new Random();

	@BeforeAll
	public static void setup() {
		int x;
		for(int i = 0; i < 25; i++) {
            x = r.nextInt(25);
            if (null == tr)
            	tr = new Tree(x);
            else 
                tr.insert(x);
            ts.add(x);
		}
		System.out.println("Tree created in Setup:");
		////////////////System.out.print(ts.containsKey());
		Iterator<?> it1=tr.iterator();
		while (it1.hasNext()) {
            System.out.print(it1.next() + " ");
        }
        System.out.println();
        /////System.out.print(it1.next());
        
        System.out.println("TreeSet created in Setup:");
        ///////////////
        Iterator<?> it2=ts.iterator();
        while (it2.hasNext()) {
        	System.out.print(it2.next() + " ");
        }
        System.out.println();
        ////System.out.print(it1.next());

        System.out.println("-------------------------");
	}		 

	@AfterEach
	void check_invariant() {
		checker(tr);
		System.out.println("Tree invariant maintained");
        System.out.println("-------------------------");
	}
		
	@Test
	void test_insert() {
		System.out.println("Testing Tree insert ...");
        System.out.println("Creating TreeSet iterator and Comparing elements pair-wise ...");

        Iterator<Integer> tsi = ts.iterator();
        Iterator<Integer> ti = tr.iterator();
        
        while (ti.hasNext() && tsi.hasNext()) {
            assertTrue(ti.next().equals(tsi.next()));
        }
        
        assertTrue(!tsi.hasNext());
        assertTrue(!ti.hasNext());


        System.out.println("... Tree insert test passed");
		// code to be filled in by you	 
	}
		
	public boolean ordered(Tree tr) {
		return (tr.left == null || (tr.value > tr.left.max().value && ordered(tr.left))) &&
                (tr.right == null || (tr.value < tr.right.min().value && ordered(tr.right)));
		// code to be filled in by you	
	}
	
	public void checker(Tree tr) {
        assertTrue(ordered(tr));

        if (null != tr.left) 
            checker(tr.left);

        if (null != tr.right)
            checker(tr.right);
    }

}