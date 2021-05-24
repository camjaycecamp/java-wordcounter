/*
 * cameron campbell
 * advanced java
 * occc spring 2021
 * word counter
 */

/*
 * 'the quick brown fox jumped over the lazy dog.'
 */

import java.util.*;
import java.io.*;

/*
 * a generally modified version of Listing 23.9, which the homework prompt suggested,
 * that reads a file in its entirety into a string instead of using a predetermined
 * string or directly prompted string from the user
 */
public class WordCounter 
{
	/*
	 * main string executes the entirety of the class. it starts by prompting
	 * the user for a file name (either through a command line argument or through
	 * a scanner during runtime), then uses a try/catch block to read the file
	 * into a string, which is then filtered with a split call into an array
	 * of the words in the text file. each word in the array is made all lower
	 * case and tested against the entries in the map object. if it doesn't exist
	 * yet in the map, it's added as a new entry and given a value of 1; else, it's
	 * incremented into the existing entry. once the array is fully read through, 
	 * the map entries are read into a set and finally displayed to the user
	 * 
	 */
	public static void main(String[] args) throws FileNotFoundException 
	{
		// instantiate method-relevant objects and fields
		String fileName, txt = "";
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> map = new TreeMap<String, Integer>();
		
		// command line argument or user input to get file name
		if(args.length > 0) 
		{
			fileName = args[0];
		}
		else 
		{
			System.out.println("Enter the name of a file you want to read: ");
			fileName = sc.nextLine();
		}
		
		// try to read the entirety of the file into a string
		try 
		{
			sc = new Scanner(new File(fileName));
			txt = sc.useDelimiter("\\Z").next();
			sc.close();
		}
		catch (FileNotFoundException ex) 
		{
			ex.printStackTrace();
		}
		
		// filter words into an array, made all lower case
		String[] words = txt.split("[ \n\t\r.,;:!?(){}]");
		for(int i = 0; i < words.length; i++) 
		{
			String key = words[i].toLowerCase();
			
			/*
			 * each word is either newly added to the map object
			 * or incremented into the part of the map that already
			 * contains that word
			 */
			if(key.length() > 0) 
			{
				if (!map.containsKey(key)) 
				{
					map.put(key, 1);
				}
				else 
				{
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		
		// map is turned to a set now that all entries are unique
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		
		// set is read out, showing each word and the number of times it shows up
		for(Map.Entry<String, Integer> entry: entrySet) 
		{
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}
}
