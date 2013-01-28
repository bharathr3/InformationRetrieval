package ir.assignments.one.c;

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

/**
 * Count the total number of 2-grams and their frequencies in a text file.
 */
public final class TwoGramFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private TwoGramFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique 2-gram in the original list. The frequency of each 2-grams
	 * is equal to the number of times that two-gram occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied 2-grams sorted
	 * alphabetically. 
	 * 
	 * 
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["you", "think", "you", "know", "how", "you", "think"]
	 * 
	 * The output list of 2-gram frequencies should be 
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of two gram frequencies, ordered by decreasing frequency.
	 */
	private static List<Frequency> computeTwoGramFrequencies(ArrayList<String> words) {
		try
		{
			HashMap<String,Integer> hash=new HashMap<String,Integer>();
			List<Frequency> frequencies=new ArrayList<Frequency>();;
			if(words==null)
				return null;
			else
			{
				for(int i=0;i<words.size()-1;i++)
				{
					String temp=words.get(i)+" "+words.get(i+1);
					Integer count=hash.get(temp);
					if(count!=null)
						hash.put(temp, ++count);
					else
						hash.put(temp,1);
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
					if(record1.getFrequency()>record2.getFrequency())
							c=-1;
						else if(record1.getFrequency()<record2.getFrequency())
							c=1;
						else
							c=0;
					if(c==0)
					{
						c = record1.getText().compareTo(record2.getText());
						return c;
					}
					else
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
	
	/**
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeTwoGramFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}
