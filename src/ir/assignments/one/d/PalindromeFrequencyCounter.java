package ir.assignments.one.d;

import ir.assignments.one.a.Frequency;
import ir.assignments.one.a.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PalindromeFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private PalindromeFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique palindrome found in the original list. The frequency of each palindrome
	 * is equal to the number of times that palindrome occurs in the original list.
	 * 
	 * Palindromes can span sequential words in the input list.
	 * 
	 * The returned list is ordered by decreasing size, with tied palindromes sorted
	 * by frequency and further tied palindromes sorted alphabetically. 
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["do", "geese", "see", "god", "abba", "bat", "tab"]
	 * 
	 * The output list of palindromes should be 
	 * ["do geese see god:1", "bat tab:1", "abba:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of palindrome frequencies, ordered by decreasing frequency.
	 */
	private static List<Frequency> computePalindromeFrequencies(ArrayList<String> words) {
		try
		{
			int cnt=words.size();
			HashMap<String,Integer>hash=new HashMap<String, Integer>();
			List<Frequency> frequencies=new ArrayList<Frequency>();;
			for(int i=0;i<cnt;i++)
			{
				for(int j=i+1;j<=cnt;j++)
					if(isPalindrome((words.subList(i, j)).toString()))
					{
						Integer count=hash.get((words.subList(i, j)).toString());
						if(count!=null)
							hash.put((words.subList(i, j)).toString(), ++count);
						else
							hash.put((words.subList(i, j)).toString(),1);
					}
			}
			
			Iterator<Entry<String, Integer>> it = hash.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry<String,Integer> pair =(Map.Entry<String,Integer>)it.next();
				String key = (String)pair.getKey();
				key=key.replaceAll(",","");
				key=key.replaceAll("\\[","");
				key=key.replaceAll("\\]","");
				Integer value = (Integer)pair.getValue();
				frequencies.add(new Frequency(key, value));
			}
			
			Collections.sort(frequencies, new Comparator<Frequency>() {
				@Override
				public int compare(final Frequency record1, final Frequency record2) {
					int c;					
					if(record1.getText().length()>record2.getText().length())
						c=-1;
					else if(record1.getText().length()<record2.getText().length())
						c=1;
					else
						c=0;
					
					if(c==0)
					{
						if(record1.getFrequency()>record2.getFrequency())
							c=-1;
						else if(record1.getFrequency()<record2.getFrequency())
							c=1;
						else
							c=0;
						
						if(c==0)
							c = record1.getText().compareTo(record2.getText());	
						else
							return c;
					}
					return c;
				}});
			return frequencies;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static boolean isPalindrome(String word) {
		word=word.replaceAll(",\\s","");
		int left  = 1;
	    int right = word.length() - 2;
	  
	    while (left < right) 
	    {
	    	if (word.charAt(left) != word.charAt(right)) 
	    	{
	            return false;
	        }
	        left++;
	        right--;
	    }
	    return true;
	}
	/**
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computePalindromeFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
