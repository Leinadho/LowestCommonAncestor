import java.util.ArrayList;

/**
 * 
 * @author Daniel Cosgrove
 *
 *	This class is an implementation of the LCA API as specified by the LCATest class. 
 *	
 */

public class LowestCommonAncestor {
	
	//TODO: IMPLEMENT
	//Return all paths from n to root, with each path as type ArrayList<Node>
	public ArrayList<ArrayList<Node>> getAncestorLines(Node n){}

	//TODO: IMPLEMENT
	//return ArrayList of the nodes which are in all paths
	public ArrayList<Node> getIntersection(ArrayList<ArrayList<Node>> paths){}
	
	/*
	 *  To find LSCA of u & v: 
	 *  generate list of all paths from v-root
	 *  get intersection of nodes in these paths (Vn)
	 *  generate list of all paths from w-root
	 *  get intersection of nodes in these paths (Wn)
	 *  get intersection (N) of these lists (x | e Vn & e Wn)
	 *  LCSA is the node in N which has no children in N
	 */
	public Node LCA(Node x, Node y){
		
		//generate list of paths
		ArrayList<Node> xn = getIntersection(getAncestorLines(x));
		ArrayList<Node> yn = getIntersection(getAncestorLines(y));
		
		//generate list of nodes in both xn and yn(Lowest common ancestor candidates)
		ArrayList<ArrayList<Node>> temp = new ArrayList<ArrayList<Node>>();
		temp.add(xn);
		temp.add(yn);
		ArrayList<Node> commonAncestors = getIntersection(temp);
		
		//Find LSCA in list of common ancestors
		boolean foundChild;
		for(Node n : commonAncestors){
			foundChild = false;
			for(Node i : commonAncestors){
				//if n has no children in this list, return n
				if(i.isChild(n)){
					foundChild = true;
					break;
				}
			}
			if(foundChild) return n;
		}
		return null;
		
		/*
		Node v = x;
		ArrayList<Node> vToRoot = new ArrayList<Node>();
		//build a list of the nodes in x's ancestor line (from x to the root)
		while(v != null){
			path.add(v);
			v = v.parent;
		}
		Node w = y;		//w is a node in the ancestry line of y
		
		//find the intersection (if any) of x and y's ancestor lines/paths
		while(w != null){
			//check if root2 (node in ancestry line of y) is in  x's ancestor path
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
	*/
	}
}
