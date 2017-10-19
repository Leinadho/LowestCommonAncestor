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

	@Test
	public void testLargeTree() {
		
		Node[] nodes = new Node[6];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		nodes[0].addParent(nodes[1]);	//	
		nodes[1].addParent(nodes[2]);	//				5
		nodes[2].addParent(nodes[5]);	//			2-
		nodes[2].addChild(nodes[3]);	//		1-		-3
		nodes[3].addChild(nodes[4]);	//	0-				-4
		
		assertEquals(LCA(nodes[0],nodes[4]),nodes[2]);
		assertEquals(LCA(nodes[1],nodes[4]),nodes[2]);
		assertEquals(LCA(nodes[2],nodes[4]),nodes[2]);	//since nodes are desendants of themselves, this should output true
		
		assertFalse(LCA(nodes[0],nodes[4]) == nodes[5]);	//node 5 has only one child
		assertFalse(LCA(nodes[0],nodes[3]) == nodes[1]);
		
	}
	
	@Test
	public void testBiNodal(){
		Node[] nodes = new Node[2];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		nodes[0].addParent(nodes[1]);	//		1
										//	0-
		
		assertEquals(LCA(nodes[0], nodes[1]), nodes[1]);
		assertFalse(LCA(nodes[0], nodes[1]), nodes[0]);
	}
	
	@Test
	public void testPyramidalTree(){
		Node[] nodes = new Node[3];
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = new Node();
		}
		nodes[0].addParent(nodes[2]);	//		2
		nodes[2].addChild(nodes[1]);	//	0-		-1
		
		assertEquals(LCA(nodes[0],nodes[1]), nodes[2]);
		assertEquals(LCA(nodes[0],nodes[2]), nodes[2]);
		
		assertFalse(LCA(nodes[0],nodes[1]) == nodes[0]);
		assertFalse(LCA(nodes[0], nodes[2])== nodes[1]);
	}
	
	@Test
	public void testUniTree(){
		Node node = new Node();
		
		assertEquals(LCA(node, node), node);	//nodes are their own desendants... therefore their own lowest common ancestor
	}
	
	
}
