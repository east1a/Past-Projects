/* Junior Gurrola, Easton Anderson
 * Ryan Parsons
 * CS 240
 * May 15, 2018
 * Text Associator - 
 * 		This program uses a WordInfo to store words and all the words associated with those words.
 * 		It hashes each word to a spot in the table allowing for constant run time for almost all the 
 * 		functions except for if the load factor becomes too great and things arent hashed properly.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TextAssociator {
	private WordInfoSeparateChain[] table;
	private int size;

	/*
	 * INNER CLASS Represents a separate chain in your implementation of your
	 * hashing A WordInfoSeparateChain is a list of WordInfo objects that have all
	 * been hashed to the same index of the TextAssociator
	 */
	private class WordInfoSeparateChain {
		private List<WordInfo> chain;

		/*
		 * Creates an empty WordInfoSeparateChain without any WordInfo
		 */
		public WordInfoSeparateChain() {
			this.chain = new ArrayList<WordInfo>();
		}

		/*
		 * Adds a WordInfo object to the SeparateCahin Returns true if the WordInfo was
		 * successfully added, false otherwise
		 */
		public boolean add(WordInfo wi) {
			if (!chain.contains(wi)) {
				chain.add(wi);
				return true;
			}
			return false;
		}

		/*
		 * Removes the given WordInfo object from the separate chain Returns true if the
		 * WordInfo was successfully removed, false otherwise
		 */
		public boolean remove(WordInfo wi) {
			if (chain.contains(wi)) {
				chain.remove(wi);
				return true;
			}
			return false;
		}

		// Returns the size of this separate chain
		public int size() {
			return chain.size();
		}

		// Returns the String representation of this separate chain
		public String toString() {
			return chain.toString();
		}

		// Returns the list of WordInfo objects in this chain
		public List<WordInfo> getElements() {
			return chain;
		}

	}

	/*
	 * Creates a new TextAssociator without any associations
	 */
	public TextAssociator() {
		table = new WordInfoSeparateChain[100];
		size = 0;

	}

	/*
	 * Adds a word with no associations to the TextAssociator Returns False if this
	 * word is already contained in your TextAssociator , Returns True if this word
	 * is successfully added
	 */
	public boolean addNewWord(String word) {
		checkSize();
		int index = hash(word);
		if (table[index] == null) {
			table[index] = new WordInfoSeparateChain();
			size++;
			return table[index].add(new WordInfo(word));
		} else if (!containsAtChain(word, index)) {
			size++;
			return table[index].add(new WordInfo(word));
		} else {
			return false;
		}

	}

	/*
	 * Adds an association between the given words. Returns true if association
	 * correctly added, returns false if first parameter does not already exist in
	 * the SpellChecker or if the association between the two words already exists
	 */
	public boolean addAssociation(String word, String association) {
		int index = hash(word);
		if (table[index] != null) {
			WordInfo wi = getWordInfo(word, index);
			if (wi != null && !wi.getAssociations().contains(association)) {
				return wi.addAssociation(association);
			}
		}
		return false;
	}

	/*
	 * Remove the given word from the TextAssociator, returns false if word was not
	 * contained, returns true if the word was successfully removed. Note that only
	 * a source word can be removed by this method, not an association.
	 */
	public boolean remove(String word) {
		int index = hash(word);
		if (table[index] != null && containsAtChain(word, index)) {
			WordInfo wi = getWordInfo(word, index);
			if (wi != null) {
				size--;
				return table[index].remove(wi);
			}
		}
		return false;

	}

	/*
	 * Returns a set of all the words associated with the given String Returns null
	 * if the given String does not exist in the TextAssociator
	 */
	public Set<String> getAssociations(String word) {
		int index = hash(word);
		if (table[index] != null && containsAtChain(word, index)) {
			return getWordInfo(word, index).getAssociations();
		}
		return null;
	}

	/*
	 * Prints the current associations between words being stored to System.out
	 */
	public void prettyPrint() {
		System.out.println("Current number of elements : " + size);
		System.out.println("Current table size: " + table.length);

		// Walk through every possible index in the table
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];

				// For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					System.out.println("\tin table index, " + i + ": " + curr);
				}
			}
		}
		System.out.println();
	}

	// Prints the available genres (source words) available
	public void printGenre() {
		System.out.print("Genres available: ");
		List<WordInfo> genres = this.genreList();
		for (int i = 0; i < genres.size(); i++) {
			System.out.println(genres.get(i).getWord());
		}
		System.out.println("");
	}

	private int hash(String word) {
		return Math.abs(word.hashCode()) % table.length;
	}

	/*
	 * Checks if the load factor to see if the table needs to be resized. If it does
	 * then a separate resizing method is called. If not, does nothing
	 */
	private void checkSize() {
		if ((double) size / table.length >= .8) {
			resize();
		}
	}

	private void resize() {
		WordInfoSeparateChain[] resized = new WordInfoSeparateChain[table.length * 3];

		// For each table element
		for (int i = 0; i < table.length; i++) {
			// If our current table element (bucket) is not empty
			if (table[i] != null) {
				// create a bucket copy
				WordInfoSeparateChain bucket = table[i];
				// for each wordInfo in the bucket
				for (WordInfo wordInfo : bucket.getElements()) {
					// Get it's hash value index
					int index = hash(wordInfo.getWord());
					// if the has value index is empty
					// if(resized[index] == null){
					// resized[index] = new WordInfoSeparateChain();
					// resized[index].add(wordInfo);
					// //else if the hash index already has a bucket in it
					// } else{
					// resized[index].add(wordInfo);
					// }
					if (resized[index] == null) {
						resized[index] = new WordInfoSeparateChain();
					}
					resized[index].add(wordInfo);
				}
			}
			this.table = resized;
		}

	}

	/*
	 * Acquires the WordInfo of a given object and returns it
	 */
	private WordInfo getWordInfo(String word, int index) {
		WordInfo wi = null;
		List<WordInfo> chain = table[index].getElements();
		if (chain != null && chain.size() > 0) {
			for (WordInfo current : chain) {
				if (current.getWord().equals(word)) {
					wi = current;
				}
			}
		}
		return wi;
	}

	/*
	 * Checks the chaining at collisions to see if a given word is contained.
	 * returns true if it is contained, false if otherwise
	 */
	private boolean containsAtChain(String word, int index) {
		List<WordInfo> chain = table[index].getElements();
		for (WordInfo wi : chain) {
			if (wi.getWord().equals(word)) {
				return true;
			}
		}
		return false;
	}

	public int printTableSize() {
		return table.length;
	}

	// Returns a list of genres contained in the table
	public List<WordInfo> genreList() {
		List<WordInfo> genres = new ArrayList<>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				List<WordInfo> firstElement = table[i].getElements();
				genres.add(new WordInfo(firstElement.get(0).getWord()));
			}
		}
		return genres;
	}

	// Returns true if genre is present, false otherwise
	public boolean containsGenre(String word) {
		List<WordInfo> genre = this.genreList();
		return genre.contains(new WordInfo(word));
	}

}
