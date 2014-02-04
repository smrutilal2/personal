/**
 * 
 */
package home.algo.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author smruti
 * 
 */
public class TestBinarySearchTree {

	private BinarySearchTree tree = new BinarySearchTree(8, 6, 15, 5, 7, 13,
			16, 11, 12);

	@Test
	public void findMaxDepth() {

		TreeNode root = tree.getRoot();
		BinaryTreePrinter.printNode(root);

		List<TreeNode> list = new ArrayList<TreeNode>();
		tree.findPathFromRootToDepestLeaf(root, list);

		for (TreeNode treeNode : list) {
			System.out.println(treeNode);
		}

		System.out.println("");

		list = tree.findPathFromRootToDepestLeaf(root);
		for (TreeNode treeNode : list) {
			System.out.println(treeNode);
		}
	}

	@Test
	public void findAllPath() {

		// TreeNode node = new TreeNode(1);
		// node.setVisited(true);

		TreeNode root = tree.getRoot();
		BinaryTreePrinter.printNode(root);

		List<List<TreeNode>> allPaths = tree.findAllPath(root);

		for (List<TreeNode> path : allPaths) {

			for (TreeNode treeNode : path) {
				System.out.print(treeNode + ", ");
			}
			System.out.println();
		}
	}

	@Test
	public void dubleTree() {

		TreeNode root = tree.getRoot();
		BinaryTreePrinter.printNode(root);
		tree.createDoubleTree(root);
		BinaryTreePrinter.printNode(root);
	}
}
