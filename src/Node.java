
/**
 * 
 * @author Daniel Cosgrove
 *
 *	This is a class to represent the nodes in a binary tree, 
 *	Each node points to it's unique parent, and each node can have only two children
 *	The tree should only be built using methods of the nodes, which prevent 
 *	a non-binary tree from being constructed
 *	(specifically: we will get a binary, acyclic Directed graph)
 *
 */
public class Node {

	int nChildren;
	Node parent;
	
	public Node(){
		nChildren = 0;
		parent = null;
	}

	
	public boolean addParent(Node parent){
		if(parent != null){
			this.parent = parent;
			parent.nChildren++;
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean addChild(Node child){
		if(child != null){
			child.parent = this;
			this.nChildren++;
			return true;
		}
		else{
			return false;
		}
	}
	
}
