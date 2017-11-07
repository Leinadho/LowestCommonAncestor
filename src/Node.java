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
		if(parent != null){
			this.parents.add(parent);
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
			child.parents.add(this);
			child.nParents++;
			nChildren++;
			return true;
		}
		else{ 
			return false;
		}
	}
	
	public boolean isRoot(){
		if(this.parents.isEmpty()) return true;
		return false;
	}
	
	//TODO: IMPLEMENT
	//Returns true if this node is a descendant of p (ie: p can be reached from this)
	public boolean isDescendant(Node p){
		//Breadth first search of this node's ancestors, to check if p is in that set
		if(p == this) return true;	//nodes are their own ancestors...seems they should be their own descendants
		for(Node x : this.parents){
			if(BFS(p, this))return true;
		}
		return false;
	}
	
	public boolean BFS(Node target, Node curr){
		if(curr == null | curr.parents == null){
			return false;
		}
		else if(curr.isRoot()){
			return false;
		}
		//else if(curr.parents.isEmpty()) return false;
		for(Node x : curr.parents){
			if(x == target) return true;
		}
		for(Node x : curr.parents){
			if(BFS(target, x)) return true;
		}
		return false;
	}
}
