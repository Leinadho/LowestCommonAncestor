import java.util.ArrayList;

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
	int nParents;
	ArrayList<Node> parents = new ArrayList<Node>();
	
	public Node(){
		nChildren = 0;
		nParents = 0;
	}

	
	public boolean addParent(Node parent){
		if(parent == null){
			parents.add(parent);
			nParents++;
			parent.nChildren++;
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean addChild(Node child){
		if(child != null){
			child.parents.add(child);
			child.nParents++;
			nChildren++;
			return true;
		}
		else{ 
			return false;
		}
	}
	
	//TODO: IMPLEMENT
	//Returns true if this node is a child of p (ie: p can be reached from this)
	public boolean isChild(Node p){};
}
