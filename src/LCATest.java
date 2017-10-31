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
 *			 their lowest common ancestor
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
	 */
	
	//TODO: enumerate cases you wish to test, write tests for them 
	
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
	public void testMaximallyConnectedPyramidalDAG(){
		//Create Data
		Node a = new Node(),b = new Node(),c = new Node(), d = new Node();
		a.addChild(c);					//	 		
		a.addChild(b);					//		a		
		b.addParent(c);					//    >   <
		c.addChild(d);					//	 /     \
		d.addParent(b);					//	b---->	c
										//	 <-- 	 <
										//	    \---- d 
		//Tests
		
	}
	
	
	@Test
	public void testLargeDAG(){
		//Create data
		Node[] nodes = new Node[5];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		nodes[0].addChild(nodes[1]);	//		0 
		nodes[0].addChild(nodes[2]);	// 	1-		-2
		nodes[1].addChild(nodes[3]);	//   \ / \  /   
		nodes[1].addChild(nodes[4]);	//    3   4       
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
	
}
