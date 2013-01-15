package ir.assignments.one.b;

import ir.assignments.one.a.Frequency;
import ir.assignments.one.a.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

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
			SortedMap<Integer,List<String>> sorted_map=new TreeMap<Integer,List<String>>();
			HashMap<String,Integer> hash=new HashMap<String,Integer>();
			List<Frequency> frequencies = null;
			if(words==null)
				return null;
			else
			{
				for(int i=0;i<words.size();i++)
				{
					int count=hash.get(words.get(i));
					if(count>0)
						hash.put(words.get(i), count++);
					else
						hash.put(words.get(i),1);
				}
				
			}

			Iterator<Entry<String, Integer>> it = hash.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>)it.next();
				List<String> temp = new ArrayList<String>();
				if(sorted_map.containsKey(pairs.getValue()))
					temp=sorted_map.get(pairs.getValue());
				temp.add(pairs.getKey());
				Collections.sort(temp);
				sorted_map.put(pairs.getValue(), temp);
			}
			
			//Set<Entry<Integer, List<String>>> s=sorted_map.entrySet(); 
	        Iterator<Entry<Integer, List<String>>> i=sorted_map.entrySet().iterator();
	        while(i.hasNext())
	        {
	            Map.Entry<Integer,List<String>> pair =(Map.Entry<Integer,List<String>>)i.next();
	            int key = (Integer)pair.getKey();
	            List<String> value=(List<String>)pair.getValue();
	            for(int j=0;j<value.size();j++)
	            {
	            	Frequency word = new Frequency(value.get(j), key);
	            	frequencies.add(word);
	            }
	        }
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
