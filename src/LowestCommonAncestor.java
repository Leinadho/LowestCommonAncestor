import java.util.ArrayList;

/**
 * 
 * @author Daniel Cosgrove
 *
 *	This class is an implementation of the LCA API as specified by the LCATest class. 
 *	
 */

public class LowestCommonAncestor {
	
	public LowestCommonAncestor(){}
	
	public Node LCA(Node x, Node y){
		Node v = x;
		ArrayList<Node> path = new ArrayList<Node>();
		//build a list of the nodes in x's ancestor line (from x to the root)
		while(v != null){
			path.add(v);
			v = v.parent;
		}
		Node w = y;		//w is a node in the ancestry line of y
		
		//find the intersection (if any) of x and y's ancestor lines/paths
		while(w != null){
			//check if root2 (node in ance stry line of y) is in  x's ancestor path
			for(Node vertex : path){
				if(w == vertex){
					return vertex;
				}
			}
			//advance root2 to next node in line, then check again whether it intersects with x's line
			w = w.parent;
		}
		return null;
	}
}
