/**
 * 
 */
package home.algo.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * @author smruti
 * 
 */
public class TestTrie {

	private Trie trie = new Trie("a", "hi", "hello", "by");

	@Test
	public void search() {
		assertFalse(trie.search("bat"));
		assertTrue(trie.search("hi"));
	}

	@Test
	public void insert() {
		trie.insert("bat");
		assertTrue(trie.search("bat"));
	}

	@Test
	public void findCompletions() {

		List<String> results = trie.findCompletions("h");
		assertTrue(2 == results.size());
		assertTrue(results.contains("hello"));
		assertTrue(results.contains("hi"));

		results = trie.findCompletions("a");
		assertTrue(1 == results.size());
		assertTrue(results.contains("a"));

		results = trie.findCompletions("b");
		assertTrue(1 == results.size());
		assertTrue(results.contains("by"));

		trie.insert("hell");
		results = trie.findCompletions("he");
		assertTrue(2 == results.size());
		assertTrue(results.contains("hell"));
		assertTrue(results.contains("hello"));

	}

}
