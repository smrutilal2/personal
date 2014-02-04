package home.algo.tree;

public class TreeNode {

	private int data;

	private TreeNode left;

	private TreeNode right;

	private TreeNode parent;

	private Boolean visited;

	private int balanceFactor;

	public TreeNode(int data) {
		this.data = data;
		this.visited = false;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Boolean isVisited() {
		return visited;
	}

	public void setVisited(Boolean visited) {
		this.visited = visited;
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}

	public boolean isLeaf() {
		return null == left && null == right;
	}

	public boolean hasSingleChild() {
		return null != left || null != right;
	}

	public boolean hasBothChild() {
		return null != left && null != right;
	}

	@Override
	public String toString() {
		return String.valueOf(data);
	}

}
