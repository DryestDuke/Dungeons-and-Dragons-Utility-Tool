import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

final class TotalOccurrences {
	
	public char letter;
	public int totalOccurrences;
	
	public TotalOccurrences(char letter) {
		this.letter = letter;
		totalOccurrences = 0;
	}
	
	public void increment() {
		totalOccurrences++;
	}
}

final class ProbabilityPairing {
	
	public char[] pairing;
	public int occurrences;
	private TotalOccurrences to;
	public double probability;
	
	public ProbabilityPairing(char firstLetter, char secondLetter, TotalOccurrences to) {
		pairing = new char[] {firstLetter, secondLetter};
		occurrences = 0;
		this.to = to;
		probability = 0;
	}
	
	public void increment() {
		occurrences++;
		probability = (double) occurrences/(double) to.totalOccurrences;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(pairing) + ", " + occurrences + "/" + to.totalOccurrences + " = " + probability;
	}
}

final class ProbabilityRange {
	public double min;
	public double max;
	public ProbabilityPairing pp;
	
	public ProbabilityRange(double min, double max, ProbabilityPairing pp) {
		this.pp = pp;
		this.min = min;
		this.max = max;
	}
	
	public boolean isInRange(double someNumber) {
		return min <= someNumber && someNumber <=max;
	}
	
}

final class TotalOccurrencesRange {
	public int min;
	public int max;
	public TotalOccurrences to;
	
	public TotalOccurrencesRange(int min, int max, TotalOccurrences to) {
		this.to = to;
		this.min = min;
		this.max = max;
	}
	
	public boolean isInRange(int someNumber) {
		return min <= someNumber && someNumber <=max;
	}
	
}

public class MarkovChain {

	private ArrayList<ProbabilityPairing> pairings;
	private ArrayList<TotalOccurrences> occurrences;
	
	/**
	 * Constructor - creates the probability table.
	 * @param filename The filename of the file with all example words.
	 */
	public MarkovChain(String filename) {
		//parse file and record all words (one/line)
		ArrayList<String> words = Model.loadFile(filename);
		
		char[] letters = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		pairings = new ArrayList<ProbabilityPairing>();
		occurrences = new ArrayList<TotalOccurrences>();
		
		//for each letter...
		for(char firstLetter : letters) {
			occurrences.add(new TotalOccurrences(firstLetter));
			//for each letter...
			for(char secondLetter : letters) {
				pairings.add(new ProbabilityPairing(firstLetter, secondLetter, getTotalOccurrences(occurrences, firstLetter)));
			}
		}
		
		//for each word
		for(String word : words) {
			char[] charWord = word.toLowerCase().toCharArray();
			if(charWord.length <= 0) {
				continue;
			}
			//for each letter in that word
			for(int c=1;c<charWord.length;c++) {				
				//each time a firstletter - anysecondletter combination is seen, increment the total counter for all combination with that firstletter
				TotalOccurrences to = getTotalOccurrences(occurrences, charWord[c-1]);
				if(to != null) {
					to.increment();
				}else {
					
				}				
				//each time a firstletter - secondletter combination is seen, increment the # times seen counter for the combination
				ProbabilityPairing pp = getPairing(pairings, charWord[c-1], charWord[c]);
				if(pp != null) {
					pp.increment();
				}else {
					
				}
			}
		}
	}
	
	/**
	 * Given some list of total occurrences, it returns the reference to the TO in that list that corresponds to the first/second letter given.
	 * @return the given TotalOccurences, or null if it does not exist.
	 */
	public TotalOccurrences getTotalOccurrences(ArrayList<TotalOccurrences> list, char letter) {
		for(TotalOccurrences to : list) {
			if(to.letter == letter) {
				return to;
			}
		}
		return null;
	}
	
	/**
	 * Given some list of probability pairing, it returns the reference to the PP in that list that corresponds to the first/second letter given.
	 * @return the given ProbabilityPairing, or null if it does not exist.
	 */
	public ProbabilityPairing getPairing(ArrayList<ProbabilityPairing> list, char firstLetter, char secondLetter) {
		for(ProbabilityPairing pp : list) {
			if(pp.pairing[0] == firstLetter && pp.pairing[1] == secondLetter) {
				return pp;
			}
		}
		return null;
	}
	
	/**
	 * Given the probability table, this method generates & returns a word.
	 * @param length If this is less than 1 the length is randomly chosen.
	 * @param capitalize If this is set as true then the word will be capitalized.
	 */
	public String generateWord(int length, boolean capitalize) {
		Random rand = new Random();
		//given the table of pairings, randomly choose one of the pairings to start,
		//then based off of the last character continue until word is long enough
		String word = "";
		
		if(length <= 1) {
			//randomly choose a length from 2-10, weighted for around 5
			int[] lengths = new int[] {2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 9};
			
			int max = lengths.length-1;
			int min = 0;
			
			length = lengths[rand.nextInt(max - min + 1) + min];
		}
		
		double baseRange = 0;
		
		ArrayList<ProbabilityRange> ranges = new ArrayList<ProbabilityRange>(pairings.size());
		
		char lastChar = pairings.get(0).pairing[0];
		for(ProbabilityPairing pp : pairings) {
			//the range is 0-1 for every beggining letter (every TotalOccurrence, in essence)
			if(pp.pairing[0] != lastChar) {
				baseRange = 0;
			}
			ranges.add(new ProbabilityRange(baseRange, baseRange + pp.probability, pp));
			baseRange += pp.probability;
			lastChar = pp.pairing[0];
		}
		
		//choose a first letter
		word += Character.toString(pickFirstLetter());
		if(capitalize) {
			word = word.toUpperCase();
		}
		
		//finish the word
		while(word.length() <= length) {			
			double choice = rand.nextDouble();
			for(ProbabilityRange range : ranges) {				
				if(range.pp.pairing[0] == word.toLowerCase().charAt(word.length()-1) && range.isInRange(choice)) {
					word += Character.toString(range.pp.pairing[1]);
					break;
				}
			}
		}
		
		return word;
	}

	/**
	 * Uses the values in totalOccurences to establish ranges for each possible first letter (from a-z) and then returns one of them.
	 */
	private char pickFirstLetter() {
		ArrayList<TotalOccurrencesRange> ranges = new ArrayList<TotalOccurrencesRange>(pairings.size());

		char output = 0;

		int baseRange = 0;
		
		for(TotalOccurrences to : occurrences) {
			ranges.add(new TotalOccurrencesRange(baseRange, baseRange + to.totalOccurrences, to));
			baseRange += to.totalOccurrences;
		}
		
		int min = 0;
		int max = baseRange;
		
		int choice = new Random().nextInt((max - min) + 1) + min;
		
		for(TotalOccurrencesRange tor : ranges) {
			if(tor.isInRange(choice)) {
				output = tor.to.letter;
				break;
			}
		}
		
		return output;
	}
}
