import java.io.*;
import java.util.*;

public class MarkovChainFileImprover {

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Filename: ");
		String fn = input.next();
		
		ArrayList<String> fileWords = Model.loadFile("files\\Languages\\" + fn);
		
		MarkovChain mc = new MarkovChain(fileWords);
		
		ArrayList<String> newWords = new ArrayList<String>();
		
		int choice = -1;
		
		while(choice != 2) { 
			
			String word =  mc.generateWord(0, false);
			
			System.out.print("\n" + word);
			
			System.out.print("\n0: Good, 1: Bad, 2: Exit\nChoice: ");
			choice = input.nextInt();
			
			if(choice == 0) {
				newWords.add(word);
			}
		}
		
		System.out.println("\nPrinting your new words to add to " + fn + ".");
		
		for(String word : newWords) {
			System.out.println(word);
		}
	}
	
}