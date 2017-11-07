import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Daniel Cosgrove
 * 
 * 	TODO:
 * 	Question: should a node be defined as its own descendant?
 *
 */

public class NodeTest {

	@Test
	public void testAddParent(){
		//Create data
		Node[] nodes = new Node[5];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		
		nodes[1].addParent(nodes[0]);
		assertTrue(nodes[1].parents.get(0) == nodes[0]);
		assertTrue(nodes[0].parents.isEmpty());
		assertTrue(nodes[0].isRoot());
		assertFalse(nodes[1].isRoot());
		
		nodes[2].addParent(nodes[0]);
		assertTrue(nodes[2].parents.contains(nodes[0]));
		assertTrue(nodes[2].parents.size() == 1);
		assertFalse(nodes[2].parents.contains(nodes[1]));
		assertTrue(nodes[0].parents.isEmpty());
		assertTrue(nodes[1].parents.contains(nodes[0]));
		assertTrue(nodes[1].parents.size() == 1);
		assertFalse(nodes[1].parents.contains(nodes[2]));
		
		nodes[3].addParent(nodes[2]);
		nodes[4].addParent(nodes[2]);
		assertTrue(nodes[2].parents.contains(nodes[0]));
		assertTrue(nodes[2].parents.size() == 1);
		assertFalse(nodes[2].parents.contains(nodes[1]));
		assertTrue(nodes[0].parents.isEmpty());
		assertTrue(nodes[1].parents.contains(nodes[0]));
		assertTrue(nodes[1].parents.size() == 1);
		assertFalse(nodes[1].parents.contains(nodes[2]));
		
		assertTrue(nodes[3].parents.contains(nodes[2]));
		assertTrue(nodes[3].parents.size() == 1);
		assertFalse(nodes[3].parents.contains(nodes[4]));
		assertFalse(nodes[3].parents.contains(nodes[0]));
		assertTrue(nodes[4].parents.contains(nodes[2]));
		assertTrue(nodes[4].parents.size() == 1);
		assertFalse(nodes[4].parents.contains(nodes[3]));
		assertFalse(nodes[4].parents.contains(nodes[0]));
		
		//make & test DAG
		nodes[3].addParent(nodes[1]);
		assertTrue(nodes[3].parents.contains(nodes[1]));
		assertTrue(nodes[3].parents.contains(nodes[2]));
		assertFalse(nodes[3].parents.contains(nodes[0]));
		
	}
	
	@Test
	public void testAddChild(){
		//Create data
		Node[] nodes = new Node[5];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		
		//TODO
		nodes[0].addChild(nodes[1]);
		assertTrue(nodes[1].parents.get(0) == nodes[0]);
		assertTrue(nodes[0].parents.isEmpty());
		assertTrue(nodes[0].isRoot());
		assertFalse(nodes[1].isRoot());
		
		nodes[0].addChild(nodes[2]);
		assertTrue(nodes[2].parents.contains(nodes[0]));
		assertTrue(nodes[2].parents.size() == 1);
		assertFalse(nodes[2].parents.contains(nodes[1]));
		assertTrue(nodes[0].parents.isEmpty());
		assertTrue(nodes[1].parents.contains(nodes[0]));
		assertTrue(nodes[1].parents.size() == 1);
		assertFalse(nodes[1].parents.contains(nodes[2]));
		
		nodes[2].addChild(nodes[3]);
		nodes[2].addChild(nodes[4]);
		assertTrue(nodes[2].parents.contains(nodes[0]));
		assertTrue(nodes[2].parents.size() == 1);
		assertFalse(nodes[2].parents.contains(nodes[1]));
		assertTrue(nodes[0].parents.isEmpty());
		assertTrue(nodes[1].parents.contains(nodes[0]));
		assertTrue(nodes[1].parents.size() == 1);
		assertFalse(nodes[1].parents.contains(nodes[2]));
		
		assertTrue(nodes[3].parents.contains(nodes[2]));
		assertTrue(nodes[3].parents.size() == 1);
		assertFalse(nodes[3].parents.contains(nodes[4]));
		assertFalse(nodes[3].parents.contains(nodes[0]));
		assertTrue(nodes[4].parents.contains(nodes[2]));
		assertTrue(nodes[4].parents.size() == 1);
		assertFalse(nodes[4].parents.contains(nodes[3]));
		assertFalse(nodes[4].parents.contains(nodes[0]));
		
		//make & test DAG
		nodes[1].addChild(nodes[3]);
		assertTrue(nodes[3].parents.contains(nodes[1]));
		assertTrue(nodes[3].parents.contains(nodes[2]));
		assertFalse(nodes[3].parents.contains(nodes[0]));
		
	}
	
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
		assertTrue(nodes[0].isDescendant(nodes[1]));
		assertTrue(nodes[0].isDescendant(nodes[2]));
		assertTrue(nodes[0].isDescendant(nodes[5]));
		assertTrue(nodes[6].isDescendant(nodes[1]));
		assertTrue(nodes[0].isDescendant(nodes[2]));
		assertTrue(nodes[0].isDescendant(nodes[5]));
		assertTrue(nodes[3].isDescendant(nodes[2]));
		assertTrue(nodes[2].isDescendant(nodes[5]));
		assertTrue(nodes[0].isDescendant(nodes[0]));
		
		assertFalse(nodes[0].isDescendant(nodes[7]));
		assertFalse(nodes[0].isDescendant(nodes[3]));
		assertFalse(nodes[0].isDescendant(nodes[6]));
		assertFalse(nodes[3].isDescendant(nodes[7]));
		assertFalse(nodes[3].isDescendant(nodes[4]));
		assertFalse(nodes[3].isDescendant(nodes[1]));
		
		assertTrue(nodes[5].isDescendant(nodes[5]));
		assertTrue(nodes[2].isDescendant(nodes[5]));
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
		assertTrue(nodes[0].isDescendant(nodes[2]));
		assertTrue(nodes[1].isDescendant(nodes[2]));
		assertTrue(nodes[2].isDescendant(nodes[2]));
		assertTrue(nodes[1].isDescendant(nodes[1]));
		
		assertFalse(nodes[2].isDescendant(nodes[1]));
		assertFalse(nodes[0].isDescendant(nodes[1]));
		
	}
	
	
	@Test
	public void testUniTree(){
		//Create data
		Node node = new Node();			// 0
		
		//Tests
		assertTrue(node.isDescendant(node));
		
		assertFalse(node.isDescendant(null));
	}
	
	@Test
	public void testSeparatedNodes(){
		//Create data
		Node w = new Node();			//	w		v
		Node v = new Node();
		
		//Tests
		assertTrue(v.isDescendant(v));
		
		assertFalse(v.isDescendant(w));
		assertFalse(w.isDescendant(v));
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
	
	//TODO: IMPLEMENT THESE TESTS, RUN THESE TESTS, FIX ANY BUGS
	
	@Test
	public void testTriangularDAG(){
		//Create Data
		Node a = new Node(),b = new Node(),c = new Node();
		b.addParent(c);					//	 /--------->		
		b.addParent(a);					//	b--> c --> a	
		c.addParent(a);
		
		//Tests
		assertTrue(c.isDescendant(a));	//c is a desendant of itself and a
		assertTrue(c.isDescendant(c));
		assertTrue(b.isDescendant(c));	//b is a descendant of every node
		assertTrue(b.isDescendant(a));
		assertTrue(b.isDescendant(b));	
		assertTrue(a.isDescendant(a));	
		
		assertFalse(a.isDescendant(c));
		assertFalse(a.isDescendant(b));
		assertFalse(c.isDescendant(b));
	}
	
	@Test
	public void testQuadDAG(){
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
		assertTrue(d.isDescendant(c));
		assertTrue(d.isDescendant(a));
		assertTrue(d.isDescendant(d));
		assertTrue(c.isDescendant(a));
		assertTrue(b.isDescendant(a));
		assertTrue(b.isDescendant(c));
		assertTrue(a.isDescendant(a));
		
		assertFalse(d.isDescendant(b));
		assertFalse(c.isDescendant(b));
		assertFalse(c.isDescendant(d));
		assertFalse(b.isDescendant(d));
		assertFalse(a.isDescendant(b));
		assertFalse(a.isDescendant(c));
		assertFalse(a.isDescendant(d));
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
		assertTrue(d.isDescendant(c));
		assertTrue(d.isDescendant(a));
		assertTrue(d.isDescendant(b));
		assertTrue(d.isDescendant(d));
		assertTrue(c.isDescendant(a));
		assertTrue(c.isDescendant(c));
		assertTrue(b.isDescendant(c));
		assertTrue(b.isDescendant(a));
		assertTrue(a.isDescendant(a));
		
		assertFalse(c.isDescendant(d));
		assertFalse(c.isDescendant(b));
		assertFalse(a.isDescendant(b));
		assertFalse(a.isDescendant(c));
		assertFalse(a.isDescendant(d));
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
		assertTrue(nodes[0].isDescendant(nodes[0]));
		assertTrue(nodes[1].isDescendant(nodes[0]));
		assertTrue(nodes[1].isDescendant(nodes[1]));
		assertTrue(nodes[2].isDescendant(nodes[0]));
		assertTrue(nodes[2].isDescendant(nodes[2]));
		assertTrue(nodes[3].isDescendant(nodes[0]));
		assertTrue(nodes[3].isDescendant(nodes[1]));
		assertTrue(nodes[3].isDescendant(nodes[2]));
		assertTrue(nodes[4].isDescendant(nodes[0]));
		assertTrue(nodes[4].isDescendant(nodes[1]));
		assertTrue(nodes[4].isDescendant(nodes[2]));
		
		assertFalse(nodes[0].isDescendant(nodes[1]));
		assertFalse(nodes[0].isDescendant(nodes[2]));
		assertFalse(nodes[0].isDescendant(nodes[3]));
		assertFalse(nodes[0].isDescendant(nodes[4]));
		assertFalse(nodes[1].isDescendant(nodes[2]));
		assertFalse(nodes[1].isDescendant(nodes[3]));
		assertFalse(nodes[2].isDescendant(nodes[1]));
		assertFalse(nodes[2].isDescendant(nodes[3]));
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
		nodes[1].addChild(nodes[3]);	//	   \		  /		|
		nodes[2].addChild(nodes[4]);	//		3<-----\ /		| ("time" axis)
		nodes[3].addChild(nodes[4]);	//		|		4		|
		nodes[3].addChild(nodes[5]);	//		|	/	  \ 	|
		nodes[4].addChild(nodes[5]);	//		5		   6	|
		nodes[4].addChild(nodes[6]);	//				
		
		//Tests
		
		assertTrue(nodes[0].isDescendant(nodes[0]));
		assertTrue(nodes[1].isDescendant(nodes[1]));
		assertTrue(nodes[1].isDescendant(nodes[0]));
		assertTrue(nodes[2].isDescendant(nodes[2]));
		assertTrue(nodes[3].isDescendant(nodes[3]));
		assertTrue(nodes[3].isDescendant(nodes[1]));
		assertTrue(nodes[4].isDescendant(nodes[4]));
		assertTrue(nodes[4].isDescendant(nodes[3]));
		assertTrue(nodes[4].isDescendant(nodes[1]));
		assertTrue(nodes[4].isDescendant(nodes[0]));
		assertTrue(nodes[4].isDescendant(nodes[2]));
		assertTrue(nodes[5].isDescendant(nodes[4]));
		assertTrue(nodes[5].isDescendant(nodes[2]));
		assertTrue(nodes[5].isDescendant(nodes[0]));
		assertTrue(nodes[5].isDescendant(nodes[3]));
		assertTrue(nodes[5].isDescendant(nodes[1]));
		assertTrue(nodes[6].isDescendant(nodes[4]));
		assertTrue(nodes[6].isDescendant(nodes[2]));
		assertTrue(nodes[6].isDescendant(nodes[0]));
		assertTrue(nodes[6].isDescendant(nodes[3]));
		
	//			0			A 
	//	   /		 \		|
	//	  1 	      2		|
	//	   \		  /		|
	//		3<-----\ /		| ("time" axis)
	//		|		4		|
	//		|	/	  \ 	|
	//		5		   6	|
	//				
	
		assertFalse(nodes[0].isDescendant(nodes[1]));
		assertFalse(nodes[0].isDescendant(nodes[2]));
		assertFalse(nodes[0].isDescendant(nodes[3]));
		assertFalse(nodes[0].isDescendant(nodes[4]));
		assertFalse(nodes[0].isDescendant(nodes[5]));
		assertFalse(nodes[0].isDescendant(nodes[6]));
		assertFalse(nodes[1].isDescendant(nodes[2]));
		assertFalse(nodes[1].isDescendant(nodes[3]));
		assertFalse(nodes[1].isDescendant(nodes[4]));
		assertFalse(nodes[1].isDescendant(nodes[5]));
		assertFalse(nodes[1].isDescendant(nodes[6]));
		assertFalse(nodes[4].isDescendant(nodes[5]));
		assertFalse(nodes[4].isDescendant(nodes[6]));
		assertFalse(nodes[3].isDescendant(nodes[4]));
		assertFalse(nodes[3].isDescendant(nodes[5]));
		assertFalse(nodes[3].isDescendant(nodes[6]));
		assertFalse(nodes[5].isDescendant(nodes[6]));
		assertFalse(nodes[6].isDescendant(nodes[5]));
		
	}

	//TODO: IMPLEMENT TESTS FOR GRAPHS WITH MULTIPLE ROOTS
	
}
