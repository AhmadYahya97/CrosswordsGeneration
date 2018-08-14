package application;

import java.util.ArrayList;

public class Value {

	ArrayList<String> listOfWords;

	int size;

	ArrayList<String>[] allCombinations;

	Value(int size) {
		this.size = size;
		listOfWords = new ArrayList<String>();
	}

	public void add(String word) {
		listOfWords.add(word.toLowerCase());
	}

	public ArrayList<String> get(char letter, int position) {
		if ((letter - 'a') * size + position < allCombinations.length && (letter - 'a') * size + position >= 0) {
			return allCombinations[(letter - 'a') * size + position];
		}
		System.out.println("ouch");
		return null;
	}

	@SuppressWarnings("unchecked")
	public void setCombinations() {

		allCombinations = new ArrayList[26 * size];	

		for (int i = 0; i < 26 * size; i++) {
			allCombinations[i] = new ArrayList<String>();
		}

		for (int i = 0; i < listOfWords.size(); i++) {
			String str = listOfWords.get(i);
			for (int j = 0; j < str.length(); j++) {
				int characterIndex = str.charAt(j) - 'a';
				try {
					allCombinations[characterIndex * size + j].add(str);
				} catch (Exception ex) {

				}
			}
		}

	}

	public String toString() {
		return listOfWords.toString();
	}

}
