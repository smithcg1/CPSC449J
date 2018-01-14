import java.util.ArrayList;

public class Node {
	boolean open = true;
	int penalty = 0;
	Node parrent = null;
	ArrayList<Node> children = new ArrayList<Node>();
	
	Node(Node above){
		parrent = above;
	}
	
	public void addChildNode(Node child){
		children.add(child);
	}
}
