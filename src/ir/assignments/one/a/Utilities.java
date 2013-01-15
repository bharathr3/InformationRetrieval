package ir.assignments.one.a;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of utility methods for text processing.
 */
public class Utilities {
	/**
	 * Reads the input text file and splits it into alphanumeric tokens.
	 * Returns an ArrayList of these tokens, ordered according to their
	 * occurrence in the original text file.
	 * 
	 * Non-alphanumeric characters delineate tokens, and are discarded.
	 *
	 * Words are also normalized to lower case. 
	 * 
	 * Example:
	 * 
	 * Given this input string
	 * "An input string, this is! (or is it?)"
	 * 
	 * The output list of strings should be
	 * ["an", "input", "string", "this", "is", "or", "is", "it"]
	 * 
	 * @param input The file to read in and tokenize.
	 * @return The list of tokens (words) from the input file, ordered by occurrence.
	 */
	public static ArrayList<String> tokenizeFile(File input) 
	{
		char letter;
		StringBuffer word=new StringBuffer();
		ArrayList<String> Tokens=new ArrayList<String>();
		try
		{
			FileReader file=new FileReader(input);
			do
			{
				letter=(char) file.read();
				if(Character.isLetterOrDigit(letter))
					word.append(Character.toLowerCase(letter));
				else if(word.length()!=0)
				{
					Tokens.add(word.toString());
					word.delete(0,word.length());
				}
			}while(letter!='\0');
			file.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		return Tokens;
	}

	/**
	 * Takes a list of {@link Frequency}s and prints it to standard out. It also
	 * prints out the total number of items, and the total number of unique items.
	 * 
	 * Example one:
	 * 
	 * Given the input list of word frequencies
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total item count: 6
	 * Unique item count: 5
	 * 
	 * sentence	2
	 * the		1
	 * this		1
	 * repeats	1
	 * word		1
	 * 
	 * 
	 * Example two:
	 * 
	 * Given the input list of 2-gram frequencies
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total 2-gram count: 6
	 * Unique 2-gram count: 5
	 * 
	 * you think	2
	 * how you		1
	 * know how		1
	 * think you	1
	 * you know		1
	 * 
	 * @param frequencies A list of frequencies.
	 */
	public static void printFrequencies(List<Frequency> frequencies) 
	{
		int sum=0;
		for(int i=0;i<frequencies.size();i++)
			sum=sum+frequencies.get(i).getFrequency();
		System.out.print("Total item count :"+sum);
		System.out.println("Unique item count :"+frequencies.size());
		for(int i=0;i<frequencies.size();i++)
		{
			System.out.println(frequencies.get(i).getText());
			System.out.print('\t'+frequencies.get(i).getFrequency());
		}
	}
}