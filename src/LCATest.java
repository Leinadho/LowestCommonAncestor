import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Daniel Cosgrove
 *	This is a JUnit testing class for the Lowest common ancestor problem. 
 *	
 *	The graph that we're querying must be a directed acyclic graph (DAG)
 *	
 *
 *	LCA API: takes 2 Node objects as parameters, and returns the node which is 
 *			 their lowest common SINGLE ancestor
 *			 If this node does not exist, a null value is returned. 	
 *
 */

public class LCATest {

	LowestCommonAncestor tool = new LowestCommonAncestor();
	
	@Test
	public void testLargeTree() {
		//Create data
		Node[] nodes = new Node[8];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		nodes[0].addParent(nodes[1]);	//	
		nodes[1].addParent(nodes[2]);	//			   -5
		nodes[2].addParent(nodes[5]);	//			2-
		nodes[2].addChild(nodes[3]);	//		1-		-3
		nodes[3].addChild(nodes[4]);	//	  0-  -6  7-  -4
		nodes[1].addChild(nodes[6]);
		nodes[3].addChild(nodes[7]);
		
		//Tests
		assertEquals("bottommost nodes", tool.LCA(nodes[0],nodes[4]),nodes[2]);
		assertEquals("LCA's child and lowest descendant", tool.LCA(nodes[1],nodes[4]),nodes[2]);
		assertEquals("Reflexive ancestry, 2 degrees", tool.LCA(nodes[2],nodes[4]),nodes[2]);	//since nodes are descendants of themselves, this should output true
		assertEquals("Reflexive ancestry case, 1 degrees", tool.LCA(nodes[0], nodes[1]), nodes[1]);
		assertEquals("Reflexive ancestry case, 0 degrees", tool.LCA(nodes[0], nodes[0]), nodes[0]);
		assertEquals("Reflexive ancestry case, 0 degrees", tool.LCA(nodes[2], nodes[2]), nodes[2]);
		
		assertFalse("ensure output not trivial (ie: always true)", tool.LCA(nodes[0],nodes[4]) == nodes[5]);	//node 5 has only one child
		assertFalse("ensure output not trivial (ie: always true)", tool.LCA(nodes[0],nodes[3]) == nodes[1]);
		assertFalse("triviality check: Reflexive ancestry case, 0 degrees", tool.LCA(nodes[1], nodes[1]) == nodes[5]);
		assertFalse("triviality check: Reflexive ancestry case, 0 degrees", tool.LCA(nodes[0], nodes[0]) == nodes[5]);
		
	}
	
	@Test
	public void testPyramidalTree(){
		//Create data
		Node[] nodes = new Node[3];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		nodes[0].addParent(nodes[2]);	//		2
		nodes[2].addChild(nodes[1]);	//	0-		-1
		
		//Tests
		assertEquals("", tool.LCA(nodes[0],nodes[1]), nodes[2]);
		assertEquals("", tool.LCA(nodes[0],nodes[2]), nodes[2]);
		
		assertFalse("", tool.LCA(nodes[0],nodes[1]) == nodes[0]);
		assertFalse("", tool.LCA(nodes[0], nodes[2])== nodes[1]);
	}
	
	@Test
	public void testBiNodal(){
		//Create data
		Node[] nodes = new Node[2];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		nodes[0].addParent(nodes[1]);	//		1
										//	0-
		
		//Tests
		assertEquals("",tool.LCA(nodes[0], nodes[1]), nodes[1]);
		
		assertFalse("",tool.LCA(nodes[0], nodes[1]) == nodes[0]);
	}
	
	
	@Test
	public void testUniTree(){
		//Create data
		Node node = new Node();			// 0
		
		//Tests
		assertEquals("Only one meaningful test for single node case", tool.LCA(node, node), node);	//nodes are their own descendants... therefore their own lowest common ancestor
	}
	
	@Test
	public void testSeparatedNodes(){
		//Create data
		Node w = new Node();			//	0		1
		Node v = new Node();
		
		//Tests
		assertEquals("Separated nodes. Shouldn't have a common ancestor", tool.LCA(v, w), null );
		assertEquals("Reflexive case", tool.LCA(v, v), v);
	}
	
	/**
	 * PART II: DAG TESTS
	 * 
	 * I choose to define the LCA of a Directed Acyclic Graph as the lowest
	 * single common ancestor, meaning that there can only be one or zero results of the function.
	 * 
	 * The definition of the lowest single common ancestor (LSCA) is:
	 * 
	 * the LSCA of two nodes u,v is the node L which lies on all root-u and root-v paths,
	 * but no descendant of L has this property.
	 * 
	 */
	
	
	@Test
	public void testPyramidalDAG(){
		//Create Data
		Node a = new Node(),b = new Node(),c = new Node();
		b.addParent(c);					//	 /--------->		
		b.addParent(a);					//	b--> c --> a	
		c.addParent(a);
		
		//Tests
		assertTrue("", tool.LCA(b,c) == c);	 //based on the self-descendant property
		assertFalse("", tool.LCA(b,c) == a); //c is a lower 'level' from the root than a, therefore a cannot be the lowest common ancestor
		assertTrue("", tool.LCA(b,a) == a);
		assertFalse("", tool.LCA(b,a) == c); // c is not an ancestor of a
		assertFalse("", tool.LCA(b,a) == b); // b is not an ancestor of a
	}
	
	@Test
	public void testQuadrilateralDAG(){
		//Create Data
		Node a = new Node(),b = new Node(),c = new Node(), d = new Node();
		a.addChild(c);					//	 		
		a.addChild(b);					//		a  <---	
		b.addParent(c);					//    /   \    |
		c.addChild(d);					//	 /     \   | 
		d.addParent(a);					//	b---->	c  |
										//	  	     \ |
										//	    	  d| 
		//Tests
		
		//LCA of b and d:
		//while 'a' is in both paths b-root & d-root, 'a' is disqualified because 'c' is 
		//it's descendant and also satisfies this property
		assertEquals(tool.LCA(b, d), c);	
		assertFalse(tool.LCA(b, d) == b);		
		assertFalse(tool.LCA(b, d) == a);	
		
		//LCA of d & c
		//The expected result is c by the self-descendant property
		assertEquals(tool.LCA(d, c), c);	
		assertFalse(tool.LCA(d, c) == d);	
		assertFalse(tool.LCA(d, c) == a);	
		
	}
	
	@Test
	public void testMaximallyConnectedDAG(){
		//Create Data
		Node a = new Node(),b = new Node(),c = new Node(), d = new Node();
		a.addChild(c);					//	 		
		a.addChild(b);					//		a  <---	
		b.addParent(c);					//    >   <    | 
		c.addChild(d);					//	 /     \   |
		d.addParent(b);					//	b---->	c  |
		d.addParent(a);					//	 <-- 	 < |
										//	    \---- d| 
		//Tests
		
		//LCA of b and d:
		//b due to self-descendant rule
		assertEquals(tool.LCA(b, d), b);	
		assertFalse(tool.LCA(b, d) == c);		
		assertFalse(tool.LCA(b, d) == a);
				
		//LCA of d & c
		//The expected result is c by the self-descendant property
		assertEquals(tool.LCA(d, c), c);	
		assertFalse(tool.LCA(d, c) == d);	
		assertFalse(tool.LCA(d, c) == a);
		assertFalse(tool.LCA(d, c) == b);
		
		assertFalse(tool.LCA(d, d) == d);	//just in case...
		
		//LCA of d & a
		assertEquals(tool.LCA(d, a), a);	//bit of a limit case, with a being root 
		assertFalse(tool.LCA(d, a) == null );
		assertFalse(tool.LCA(d, a) == c );
	}
	
	
	@Test
	public void testMediumDAG(){
		//Create data
		Node[] nodes = new Node[5];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		nodes[0].addChild(nodes[1]);	//		0 			A
		nodes[0].addChild(nodes[2]);	// 	1-		-2		|
		nodes[1].addChild(nodes[3]);	//   \ / \  /   	|	("time" axis)
		nodes[1].addChild(nodes[4]);	//    3   4       	|
		nodes[2].addChild(nodes[3]);	
		nodes[2].addChild(nodes[4]);
		
		//Tests
		assertEquals(tool.LCA(nodes[1], nodes[2] ), nodes[0]);
		assertEquals(tool.LCA(nodes[1], nodes[3] ), nodes[0]);
		assertEquals(tool.LCA(nodes[3], nodes[4] ), nodes[0]);
		assertFalse(tool.LCA(nodes[3], nodes[4] ) == nodes[1]);
		assertFalse(tool.LCA(nodes[3], nodes[4] ) == nodes[2]);
		assertFalse(tool.LCA(nodes[3], nodes[4] ) == nodes[3]);
		
	}
	
	@Test
	public void testNodeOfAmbiguousLevel(){
		//Create data
		Node[] nodes = new Node[7];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}								//			0			A 
		nodes[0].addChild(nodes[1]);	//	   /		 \		|
		nodes[0].addChild(nodes[2]);	//	  1 	      2		|
		nodes[1].addChild(nodes[2]);	//	   \		  /		|
		nodes[2].addChild(nodes[4]);	//		3<-----\ /		| ("time" axis)
		nodes[3].addChild(nodes[4]);	//		|		4		|
		nodes[3].addChild(nodes[5]);	//		|	/	  \ 	|
		nodes[4].addChild(nodes[5]);	//		5		   6	|
		nodes[4].addChild(nodes[6]);	//				
		
		//Tests
		assertEquals(tool.LCA(nodes[5], nodes[6] ), nodes[4]);	//while 4's "level" (ie: distance from root) is ambiguous, it is the only node satisfying LSCA definition
		assertFalse(tool.LCA(nodes[5], nodes[6] ) == nodes[3]); //3 is not the LSCA
		assertEquals(tool.LCA(nodes[3], nodes[4] ), nodes[3]);	//3, by self descendant rule
		assertEquals(tool.LCA(nodes[4], nodes[5] ), nodes[4]);	//4, by self descendant rule
		assertEquals(tool.LCA(nodes[4], nodes[1] ), nodes[0]);	
	}
	
}
