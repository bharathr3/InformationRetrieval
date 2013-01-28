package ir.assignments.one.b;

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
 * Counts the total number of words and their frequencies in a text file.
 */
public final class WordFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private WordFrequencyCounter() {}

	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique word in the original list. The frequency of each word
	 * is equal to the number of times that word occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied words sorted
	 * alphabetically.
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["this", "sentence", "repeats", "the", "word", "sentence"]
	 * 
	 * The output list of frequencies should be 
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of word frequencies, ordered by decreasing frequency.
	 */
	public static List<Frequency> computeWordFrequencies(List<String> words) 
	{
		try
		{
			HashMap<String,Integer> hash=new HashMap<String,Integer>();
			List<Frequency> frequencies=new ArrayList<Frequency>();;
			if(words==null)
				return null;
			else
			{
				for(int i=0;i<words.size();i++)
				{
					String temp=words.get(i);
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
	 * Runs the word frequency counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		try{
			File file = new File(args[0]);
			List<String> words = Utilities.tokenizeFile(file);
			List<Frequency> frequencies = computeWordFrequencies(words);
			Utilities.printFrequencies(frequencies);
			}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
