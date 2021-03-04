import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TextAssociator {
	private WordInfoSeparateChain[] table;
	private int size;
	private static final int[] PRIMES = {11, 23, 47, 97, 197, 397, 797, 1579, 3187,
					                      6311, 12611, 25219, 50441, 100907, 201731,
					                      403483, 806917};
	private int primeNow;	

	private class WordInfoSeparateChain {

		private List<WordInfo> chain;


		public WordInfoSeparateChain() {
			this.chain = new ArrayList<WordInfo>();
		}


		public boolean add(WordInfo wi) {
			if (!chain.contains(wi)) {
				chain.add(wi);
				return true;
			} 
			return false;
		}

		public boolean remove(WordInfo wi) {
			if (chain.contains(wi)) {
				chain.remove(wi);
				return true;
			}
			return false;
		}

		// Returns the size of this separate chain.
		public int size() {
			return chain.size();
		}

		// Returns the String representation of this separate chain.
		public String toString() {
			return chain.toString();
		}

		// Returns the list of WordInfo objects in this chain.
		public List<WordInfo> getElements() {
			return chain;
		}
	}


	public TextAssociator() {
		table = new WordInfoSeparateChain[PRIMES[primeNow]];
		size = 0;
		primeNow = 0;
	}

	public boolean addNewWord(String word) {
		// check if the table needs to be resized.
		if ((double)(size / table.length) >= 0.75) {
			resize();
		}
		int index = hash(word) % table.length;
		if (table[index] == null) {
			table[index] = new WordInfoSeparateChain();
			size++;
			return table[index].add(new WordInfo(word));
		} else if (!containsInWisc(word, index)) {
			size++;
			return table[index].add(new WordInfo(word));
		} else {
			return false;
		}		
	}

	

	public boolean addAssociation(String word, String association) {
		int index = hash(word) % table.length;
		if (table[index] != null) {
			WordInfo curr = getWordInfo(word, index);
			if (curr != null && !curr.getAssociations().contains(association)) {
				return curr.addAssociation(association);
			}
		}
		return false;
	}

	
	public boolean remove(String word) {
		int index = hash(word) % table.length;
		if (table[index] != null && containsInWisc(word, index)) {
			WordInfo curr = getWordInfo(word, index);
			if (curr != null) {
				size--;
				return table[index].remove(curr);
			}
		}
		return false;
	}

	public Set<String> getAssociations(String word) {
		int index = hash(word) % table.length;
		if (table[index] != null && containsInWisc(word, index)) {
			return getWordInfo(word, index).getAssociations();
		} 
		return null;
	}


	public void prettyPrint() {
		System.out.println("Current number of elements : " + size);
		System.out.println("Current table size: " + table.length);
		// Walk through every possible index in the table.
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				// For each separate chain, grab each individual WordInfo.
				for (WordInfo curr : bucket.getElements()) {
					System.out.println("\tin table index, " + i + ": " + curr);
				}
			}
		}
		System.out.println();
	}

	private int hash(String word) {
		return Math.abs(word.hashCode());
	}

	private boolean containsInWisc(String word, int index) {
		List<WordInfo> list = table[index].getElements();
		for (WordInfo curr: list) {
			if (curr.getWord().equals(word)) {
				return true;
			}
		}
		return false;
	}

	private void resize() {
		primeNow++;
		WordInfoSeparateChain[] newTable = new WordInfoSeparateChain[PRIMES[primeNow]];
		for (WordInfoSeparateChain chain: table) {
			if (chain != null) {
				for (WordInfo curr: chain.getElements()) {
					int index = hash(curr.getWord()) % newTable.length;
					if (newTable[index] == null) {
						newTable[index] = new WordInfoSeparateChain();
					}
					newTable[index].add(curr);
				}
			}
		}
		table = newTable;
	}

	private WordInfo getWordInfo(String word, int index) {
		WordInfo wordInfo = null;
		List<WordInfo> chain = table[index].getElements();
		if (chain != null && chain.size() > 0) {
			for (WordInfo curr: chain) {
				if (curr.getWord().equals(word)) {
					wordInfo = curr;
				}
			}
		}
		return wordInfo;
	}
}