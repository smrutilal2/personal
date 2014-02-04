package home.algo.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree {

	protected TreeNode root;

	public BinarySearchTree(int... data) {
		buildTree(data);
	}

	public TreeNode insert(TreeNode root, int data) {
		if (null == root) {
			this.root = new TreeNode(data);
			return this.root;
		} else
			return internalInsert(root, data);
	}

	private TreeNode internalInsert(TreeNode node, int data) {

		if (data == node.getData())
			return node;
		else if (data < node.getData()) {
			if (null == node.getLeft()) {
				TreeNode left = new TreeNode(data);
				node.setLeft(left);
				left.setParent(node);
				return left;
			} else
				return internalInsert(node.getLeft(), data);
		} else {
			if (null == node.getRight()) {
				TreeNode right = new TreeNode(data);
				node.setRight(right);
				right.setParent(node);
				return right;
			} else
				return internalInsert(node.getRight(), data);
		}
	}

	private void buildTree(int... data) {
		for (int i : data) {
			insert(root, i);
		}
	}

	public TreeNode search(int data) {
		TreeNode node = root;
		while (null != node) {
			int nodeData = node.getData();
			if (data == nodeData)
				return node;
			else if (data < nodeData)
				node = node.getLeft();
			else
				node = node.getRight();
		}
		return null;
	}

	public TreeNode search(TreeNode root, int data) {
		if (null == root)
			return null;
		else if (data == root.getData())
			return root;
		else if (data < root.getData())
			return search(root.getLeft(), data);
		else
			return search(root.getRight(), data);
	}

	public void delete(int data) {

		TreeNode node = search(root, data);
		if (node.hasBothChild()) {
			TreeNode successor = findSuccessor(node);
			replaceNode(node, successor);
			if (successor.isLeaf())
				deleteLeaf(successor);
			else {
				TreeNode right = successor.getRight();
				replaceNode(successor, right);
				deleteLeaf(right);
			}
		} else if (node.hasSingleChild()) {
			if (null != node.getLeft()) {
				TreeNode left = node.getLeft();
				replaceNode(node, left);
				deleteLeaf(left);
			} else {
				TreeNode right = node.getRight();
				replaceNode(node, right);
				deleteLeaf(right);
			}
		} else
			deleteLeaf(node);

	}

	private TreeNode findSuccessor(TreeNode node) {
		TreeNode min = node.getRight();
		while (null != min.getLeft())
			min = min.getLeft();
		return min;
	}

	private void replaceNode(TreeNode original, TreeNode newNode) {
		original.setData(newNode.getData());
	}

	private void deleteLeaf(TreeNode node) {
		TreeNode parent = node.getParent();
		if (node.getData() == parent.getRight().getData())
			parent.setRight(null);
		else
			parent.setLeft(null);
	}

	public void preOrderTraversal(TreeNode root) {
		if (null != root) {
			System.out.print(root + ", ");
			preOrderTraversal(root.getLeft());
			preOrderTraversal(root.getRight());
		}
	}

	public void inOrderTraversal(TreeNode root) {
		if (null != root) {
			inOrderTraversal(root.getLeft());
			System.out.print(root + ", ");
			inOrderTraversal(root.getRight());
		}
	}

	public void inOrderTraversal(TreeNode root, List<TreeNode> list) {
		if (null != root) {
			inOrderTraversal(root.getLeft(), list);
			list.add(root);
			inOrderTraversal(root.getRight(), list);
		}
	}

	public void postOrderTraversal(TreeNode root) {
		if (null != root) {
			postOrderTraversal(root.getLeft());
			postOrderTraversal(root.getRight());
			System.out.print(root + ", ");
		}
	}

	public TreeNode createMirror(TreeNode root) {
		TreeNode mirrorRoot = null;
		if (null != root) {
			mirrorRoot = new TreeNode(root.getData());
			mirrorRoot.setLeft(createMirror(root.getRight()));
			mirrorRoot.setRight(createMirror(root.getLeft()));
		}
		return mirrorRoot;
	}

	public boolean isMirrorRecursive(TreeNode root, TreeNode anotherRoot) {

		if (null != root && null != anotherRoot) {
			if (root.getData() != anotherRoot.getData())
				return false;
			if (root.isLeaf() && anotherRoot.isLeaf())
				return root.getData() == anotherRoot.getData();
			else
				return isMirrorRecursive(root.getLeft(), anotherRoot.getRight())
						&& isMirrorRecursive(root.getRight(),
								anotherRoot.getLeft());
		} else if (null == root && null == anotherRoot)
			return true;
		else
			return false;
	}

	public boolean isMirrorRecursive2(TreeNode root, TreeNode mirrorRoot) {
		if (null != root && null != mirrorRoot) {
			if (root.isLeaf() && mirrorRoot.isLeaf())
				return root.getData() == mirrorRoot.getData();
			else
				return root.getData() == mirrorRoot.getData()
						&& isMirrorRecursive2(root.getLeft(),
								mirrorRoot.getRight())
						&& isMirrorRecursive2(root.getRight(),
								mirrorRoot.getLeft());
		} else if (null == root && null == mirrorRoot)
			return true;
		else
			return false;
	}

	public boolean isMirror(TreeNode root, TreeNode anotherRoot) {

		List<TreeNode> list1 = new ArrayList<TreeNode>();
		List<TreeNode> list2 = new ArrayList<TreeNode>();

		inOrderTraversal(root, list1);
		inOrderTraversal(anotherRoot, list2);

		if (list1.size() != list2.size())
			return false;

		int p1 = 0;
		int p2 = list2.size() - 1;
		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(p1++).getData() != list2.get(p2--).getData())
				return false;
		}
		return true;
	}

	public void createDoubleTree(TreeNode root) {
		if (null != root) {
			createDoubleTree(root.getLeft());
			createDoubleTree(root.getRight());

			TreeNode newLeftNode = new TreeNode(root.getData());
			newLeftNode.setLeft(root.getLeft());
			root.setLeft(newLeftNode);
		}
	}

	public int getHeight(TreeNode root) {
		if (null == root)
			return 0;
		int leftHeight = getHeight(root.getLeft());
		int rightHeight = getHeight(root.getRight());

		return 1 + Math.max(leftHeight, rightHeight);
	}

	public boolean isBST1() {
		return isBST1(root);
	}

	private boolean isBST1(TreeNode rootNode) {
		if (null == rootNode)
			return true;
		if (null != rootNode.getLeft()
				&& max(rootNode.getLeft()).getData() > rootNode.getData())
			return false;
		if (null != rootNode.getRight()
				&& min(rootNode.getRight()).getData() <= rootNode.getData())
			return false;
		return isBST1(rootNode.getLeft()) && isBST1(rootNode.getRight());

	}

	public boolean isBST2() {
		return (isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	private boolean isBST2(TreeNode node, int min, int max) {
		if (node == null) {
			return (true);
		} else {
			// left should be in range min...node.data
			boolean leftOk = isBST2(node.getLeft(), min, node.getData());

			// if the left is not ok, bail out
			if (!leftOk)
				return (false);

			// right should be in range node.data+1..max
			boolean rightOk = isBST2(node.getRight(), node.getData() + 1, max);

			return (rightOk);
		}
	}

	private TreeNode min(TreeNode root) {

		TreeNode current = root;
		while (null != current.getLeft()) {
			current = current.getLeft();
		}
		return current;
	}

	private TreeNode max(TreeNode root) {
		TreeNode current = root;
		while (null != current.getRight()) {
			current = current.getRight();
		}
		return current;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void findPathFromRootToDepestLeaf(TreeNode root, List<TreeNode> list) {

		if (null == root)
			return;
		else {
			list.add(root);

			List<TreeNode> leftList = new ArrayList<TreeNode>();
			List<TreeNode> rightList = new ArrayList<TreeNode>();

			findPathFromRootToDepestLeaf(root.getLeft(), leftList);
			findPathFromRootToDepestLeaf(root.getRight(), rightList);

			if (leftList.size() > rightList.size())
				list.addAll(leftList);
			else
				list.addAll(rightList);
		}
	}

	public List<TreeNode> findPathFromRootToDepestLeaf(TreeNode root) {

		List<TreeNode> list = new ArrayList<TreeNode>();

		if (null == root)
			return list;

		TreeNode left = root.getLeft();
		TreeNode right = root.getRight();

		List<TreeNode> leftList = findPathFromRootToDepestLeaf(left);
		List<TreeNode> rightList = findPathFromRootToDepestLeaf(right);
		if (leftList.size() >= rightList.size()) {
			list.add(root);
			list.addAll(leftList);
		} else {
			list.add(root);
			list.addAll(rightList);
		}
		return list;
	}

	public List<List<TreeNode>> findAllPath(TreeNode root) {

		List<List<TreeNode>> list = new ArrayList<List<TreeNode>>();
		List<TreeNode> nodes = new ArrayList<TreeNode>();

		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (null != root) {
			root.setVisited(true);
			stack.push(root);
			nodes.add(root);
		}

		while (!stack.isEmpty()) {
			TreeNode node = stack.peek();
			if (node.isLeaf()) {
				TreeNode pop = stack.pop();
				list.add(nodes);
				nodes = new ArrayList<TreeNode>(nodes);
				nodes.remove(pop);

			} else {

				TreeNode left = node.getLeft();
				TreeNode right = node.getRight();
				if (null != left && !left.isVisited()) {
					left.setVisited(true);
					stack.push(left);
					nodes.add(left);
				}

				else if (null != right && !right.isVisited()) {
					right.setVisited(true);
					stack.push(right);
					nodes.add(right);
				}

				else {
					TreeNode pop = stack.pop();
					nodes.remove(pop);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		BinarySearchTree tree = new BinarySearchTree(new int[] { 8, 3, 10, 1,
				6, 14 });
		TreeNode root = tree.getRoot();

		BinaryTreePrinter.printNode(root);

		// tree.preOrderTraversal(root);
		// System.out.println();
		// tree.inOrderTraversal(root);
		// System.out.println();
		// tree.postOrderTraversal(root);
		// System.out.println();
		//
		// System.out.println(tree.search(10));
		// System.out.println(tree.search(11));
		// System.out.println(tree.search(root, 13));
		// System.out.println(tree.search(root, 3));

		// tree.delete(8);
		// tree.inOrderTraversal(root);
		// tree.postOrderTraversal(root);
		// System.out.println();
		// tree.preOrderTraversal(root);

		TreeNode mirror = tree.createMirror(root);
		tree.postOrderTraversal(mirror);

		// System.out.println();
		// BinaryTreePrinter.printNode(mirror);
		// System.out.println();
		// System.out.println(tree.isMirror(root, mirror));
		System.out.println(tree.isMirrorRecursive2(root, mirror));

		// BinarySearchTree another = new BinarySearchTree(new int[] { 8, 3, 10,
		// 1, 6, 14 });
		// TreeNode anotherRoot = new TreeNode(8);
		// TreeNode left = new TreeNode(10);
		// left.setLeft(new TreeNode(14));
		// anotherRoot.setLeft(left);
		//
		// TreeNode right = new TreeNode(3);
		// right.setLeft(new TreeNode(6));
		// right.setRight(new TreeNode(1));
		//
		// anotherRoot.setRight(right);
		// System.out.println(tree.isMirrorRecursive(tree.getRoot(),
		// anotherRoot));
		// System.out.println(tree.isMirror(tree.getRoot(), anotherRoot));
		// tree.inOrderTraversal(root);
		// System.out.println();
		tree.createDoubleTree(root);
		System.out.println();
		BinaryTreePrinter.printNode(root);
		System.out.println();

		// tree.preOrderTraversal(root);

		// System.out.println(tree.getHeight(root));
		// System.out.println();
		// tree.preOrderTraversal(root);
		// System.out.println();
		// tree.inOrderTraversal(root);
		// System.out.println(tree.isBST1());
		// System.out.println(tree.isBST2());
		// tree.search(6).setData(9);
		// tree.inOrderTraversal(root);
		// System.out.println(tree.isBST1());
		// System.out.println(tree.isBST2());

	}

}
