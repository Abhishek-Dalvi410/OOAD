import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BST_DupTree_Test {

	static DupTree dtr;
	static List<Integer> al = new ArrayList<Integer>();
	static Random r = new Random();

	@BeforeAll
	public static void setup() {
		int x;
		for (int i = 0; i < 25; i++) {
            x=r.nextInt(25);
            if (null == dtr)
            	dtr = new DupTree(x);
            else
            	dtr.insert(x);
            al.add(x);
		}
		Collections.sort(al);
		
		System.out.println("DupTree created in Setup:");
		////////////////System.out.print(ts.containsKey());
		Iterator<?> it1=dtr.iterator();
		while (it1.hasNext()) {
            System.out.print(it1.next() + " ");
        }
        System.out.println();
        /////System.out.print(it1.next());
        
        System.out.println("Sorted ArrayList created in Setup:");
        ///////////////
        Iterator<?> it2=al.iterator();
        while (it2.hasNext()) {
        	System.out.print(it2.next() + " ");
        }
        System.out.println();
        ////System.out.print(it1.next());

        System.out.println("-------------------------");
	 	// code to be filled in by you
	}

	@AfterEach
	void check_invariant() {
		checker(dtr);

        System.out.println("DupTree invariant maintained");
        System.out.println("----------------------------");
     // code to be filled in by you
    }
		
	@Test
	//@Order(1)    
	void test_insert() {
		System.out.println("Testing DupTree insert ...");
        System.out.println("Creating ArrayList iterator and Comparing elements pair-wise ...");

        Iterator<Integer> dti = dtr.iterator();
        Iterator<Integer> ali = al.iterator();

        while (dti.hasNext() && ali.hasNext()) {
            assertTrue(dti.next().equals(ali.next()));
        }

        assertTrue(!dti.hasNext());
        assertTrue(!ali.hasNext());

        System.out.println("... DupTree insert test passed");
		// code to be filled in by you 
	}
	
	@Test
	void test_delete() {
		int rand= r.nextInt(25);

        dtr.insert(rand);
        int prev_count = get_count(dtr, rand);
        System.out.println("Testing DupTree delete: inserted value = "+rand+" with count ="+prev_count);

        dtr.delete(rand);
        int new_count = get_count(dtr, rand);
        System.out.println("After DupTree delete: value = "+rand+", count"+new_count);

        assertTrue(new_count == (prev_count - 1));

        System.out.println("DupTree delete test passed");
		// code to be filled in by you
	}		

	public int get_count(DupTree tr, int v) {
		Tree z = tr.find(v);
		if(z==null)
			return 0;
		else
			return z.get_count();
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