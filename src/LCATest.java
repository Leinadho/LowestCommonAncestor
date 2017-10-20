import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Daniel Cosgrove
 *	This is a JUnit testing class for the Lowest common ancestor problem. 
 *	We assume that the trees under testing are binary trees. 
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
	
	
}
