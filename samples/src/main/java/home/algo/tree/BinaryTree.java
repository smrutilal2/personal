package home.algo.tree;

public class BinaryTree {

	private TreeNode root;

	public BinaryTree(int data) {
		root = new TreeNode(data);
	}

	// public void addNode(int data, TreeNode parent, Direction direction)
	// {
	//
	// }

	public TreeNode getRoot() {
		return root;
	}

}

enum Direction {
	LEFT, RIGHT
}
