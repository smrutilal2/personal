package home.algo.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author smruti
 * 
 */
public class Trie {

	private TrieNode root;

	public Trie(String... data) {
		buildTree(data);
	}

	public boolean search(String prefix) {

		TrieNode current = root;
		for (int i = 0; i < prefix.length(); i++) {
			TrieNode child = current.getChild(prefix.charAt(i));
			if (null == child)
				return false;
			else
				current = child;
		}
		return current.isValue();
	}

	public boolean searchPrefix(String prefix) {
		TrieNode matchedNode = searchPrefixRecursive(root, prefix);
		return (matchedNode != null);
	}

	private TrieNode searchPrefixRecursive(TrieNode node, String prefix) {
		if (prefix.isEmpty())
			return node;
		char firstChar = prefix.charAt(0);
		TrieNode child = node.getChild(firstChar);
		if (null == child)
			return null;
		return searchPrefixRecursive(child, prefix.substring(1));
	}

	public void insert(String data) {

		TrieNode currentNode = root;
		int i = 0;
		for (; i < data.length(); i++) {
			TrieNode child = currentNode.getChild(data.charAt(i));
			if (null != child)
				currentNode = child;
			else {
				TrieNode newNode = new TrieNode(data.charAt(i));
				currentNode.addChild(newNode);
				currentNode = newNode;
			}
		}
		if (i == data.length())
			currentNode.setValue(true);
	}

	public List<String> findCompletions(String prefix) {
		TrieNode matchedNode = searchPrefixRecursive(root, prefix);
		List<String> completions = new ArrayList<String>();
		findCompletionsRecursive(matchedNode, prefix, completions);
		return completions;
	}

	private void findCompletionsRecursive(TrieNode matchedNode, String prefix,
			List<String> completions) {
		if (null == matchedNode)
			return;
		if (matchedNode.isValue())
			completions.add(prefix);
		Collection<TrieNode> children = matchedNode.getChildren();
		for (TrieNode trieNode : children) {
			findCompletionsRecursive(trieNode,
					prefix + trieNode.getNodeValue(), completions);
		}

	}

	private void buildTree(String[] data) {
		root = new TrieNode(' ');
		for (int i = 0; i < data.length; i++) {
			insert(data[i]);
		}
	}

	public static void main(String[] args) {

		String[] data = new String[] { "a", "hi", "hello", "by" };
		Trie trie = new Trie(data);
		System.out.println(trie.search("bat"));
		System.out.println(trie.search("hi"));
		trie.insert("bat");

		print(trie.findCompletions("h"));
		System.out.println("*************");
		print(trie.findCompletions("a"));
		System.out.println("*************");
		print(trie.findCompletions("b"));

		trie.insert("hell");
		System.out.println("*************");
		System.out.println(trie.search("bat"));
		System.out.println("*************");
		print(trie.findCompletions("h"));
		System.out.println("*************");
		print(trie.findCompletions("he"));

	}

	private static void print(List<String> completions) {
		for (String string : completions) {
			System.out.println(string);
		}
	}
}

class TrieNode {

	private Character nodeValue;

	private boolean isValue;

	private Map<Character, TrieNode> children;

	public TrieNode(char key) {
		this.nodeValue = key;
		children = new HashMap<Character, TrieNode>(26);
	}

	public void addChild(TrieNode child) {
		children.put(child.getNodeValue(), child);
	}

	public TrieNode getChild(char key) {
		return children.get(key);
	}

	public Collection<TrieNode> getChildren() {
		return children.values();
	}

	public char getNodeValue() {
		return nodeValue;
	}

	public boolean isValue() {
		return isValue;
	}

	public void setValue(boolean isValue) {
		this.isValue = isValue;
	}

}
