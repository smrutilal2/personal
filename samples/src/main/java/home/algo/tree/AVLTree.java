package home.algo.tree;

/**
 * @author smruti
 * 
 */
public class AVLTree extends BinarySearchTree {

	public AVLTree(int... data) {
		super(data);
	}

	@Override
	public TreeNode insert(TreeNode root, int data) {

		TreeNode node = super.insert(root, data);
		calibrate(node);

		// if (!isBalanced(node.getParent())) {
		// System.out.println("rotate : " + node);
		// }
		return node;
	}

	private void calibrate(TreeNode node) {

		if (null == node)
			return;
		if (node.isLeaf())
			calibrate(node.getParent());
		else {
			TreeNode left = node.getLeft();
			TreeNode right = node.getRight();
			int leftBalanceFactor = null != left ? Math.abs(left
					.getBalanceFactor()) : -1;
			int rightBalanceFactor = null != right ? Math.abs(right
					.getBalanceFactor()) : -1;
			int balanceFactor = leftBalanceFactor - rightBalanceFactor;
			node.setBalanceFactor(balanceFactor);

			if (2 <= balanceFactor || -2 >= balanceFactor) {
				rotaate(node);
				calibrate(node);
			} else {
				calibrate(node.getParent());
			}
		}

		// TreeNode parent = node.getParent();
		// if (null != parent) {
		// int balanceFactor = parent.getBalanceFactor();
		// int newBalanceFactor = 0;
		// if (parent.getLeft().equals(node)) {
		// newBalanceFactor = balanceFactor + 1;
		// } else
		// newBalanceFactor = balanceFactor - 1;
		//
		// parent.setBalanceFactor(newBalanceFactor);
		//
		// if (2 <= newBalanceFactor || -2 >= newBalanceFactor) {
		// rotaate(parent);
		// }
		// calibrate(parent);
		// }

	}

	private void rotaate(TreeNode unbalancedNode) {
		AVLRotation.getRotation(unbalancedNode).rotate(this, unbalancedNode);
	}

	// private boolean isBalanced(TreeNode root) {
	// if (null == root)
	// return true;
	// int heightOfLeft = getHeight(root.getLeft());
	// int heightOfRight = getHeight(root.getRight());
	// int balanceFactor = heightOfLeft - heightOfRight;
	// if (2 <= balanceFactor || -2 >= balanceFactor)
	// return false;
	// else
	// return isBalanced(root.getParent());
	// }

	public static void main(String[] args) {
		AVLTree tree = new AVLTree(8, 3, 10, 1, 6, 15, 16);
		BinaryTreePrinter.printNode(tree.root);
		tree.postOrderTraversal(tree.root);
		System.out.println();
		tree.inOrderTraversal(tree.root);
	}

}

enum AVLRotation {
	LL {
		@Override
		public void rotate(AVLTree avlTree, TreeNode node) {

			TreeNode left = node.getLeft();

			node.setRight(left.getRight());
			node.setLeft(null);
			node.setParent(left);

			TreeNode parent = node.getParent();
			left.setRight(node);
			left.setParent(parent);

			if (null != parent) {
				if (parent.getLeft().equals(node))
					parent.setLeft(left);
				else
					parent.setRight(left);
				left.setParent(node.getParent());
			}

			if (avlTree.getRoot().equals(node))
				avlTree.root = left;
		}
	},
	LR {
		@Override
		public void rotate(AVLTree avlTree, TreeNode node) {
			// TODO Auto-generated method stub

		}
	},
	RL {
		@Override
		public void rotate(AVLTree avlTree, TreeNode node) {
			// TODO Auto-generated method stub

		}
	},
	RR {
		@Override
		public void rotate(AVLTree avlTree, TreeNode node) {
			// TODO Auto-generated method stub

		}
	};

	public static AVLRotation getRotation(TreeNode node) {
		int balanceFactor = node.getBalanceFactor();
		if (2 == balanceFactor) {
			TreeNode left = node.getLeft();
			if (1 == left.getBalanceFactor())
				return LL;
			else
				return LR;
		} else {
			TreeNode right = node.getRight();
			if (1 == right.getBalanceFactor())
				return RL;
			else
				return RR;
		}

	}

	public abstract void rotate(AVLTree avlTree, TreeNode node);
}
