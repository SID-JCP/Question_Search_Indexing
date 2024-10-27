import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Search 
{

	

	//tokenizer to be used for search and indexing  , returns list of string of 3grams 
	public static String[] tokenize(String text) 
	{
        // Return if the text is null or shorter than 3 characters
	    if (text == null || text.length() < 3) return null;
	    
	    // Initialize a StringBuilder to collect the output
	    StringBuilder outputCollector = new StringBuilder(text.length());
	    
	    short count = 0;
	    
	    char currentChar;
	 
	    
	    
	    for (short i = 0; i < text.length(); i++) 
	    { 
	        currentChar = text.charAt(i);
	            
	        if ( currentChar != '?' && currentChar != '!' && currentChar != ',' && currentChar != '.' && currentChar != ' ') 
	        {
	                
	            if (count >= 2) 
	            {
	                outputCollector.append(text.charAt(i - 2))
			                        .append(text.charAt(i - 1))
			                        .append(currentChar).append('\n');	                
	         
	            }
	            
	            count++;
	        } else {
	        	
	            count = 0; 
	        }
	    }
	        
	    return outputCollector.toString().split("\n");
	}

	
	//key -> hash of the 3gram string , value -> question Id from DB 
	public static void index(HashMap<Integer , HashSet<Integer>> indexMap , int questionId , String questionText) 
	{
		String[] tokens = tokenize(questionText);
		int strHash = 0;
		HashSet<Integer> tempSet;
		
		
		
		for(String str : tokens) 
		{
			
			
			strHash = str.hashCode();
						
			if(indexMap.containsKey(strHash))
			{
				tempSet = indexMap.get(strHash);
				tempSet.add(questionId);
				
				indexMap.put(strHash, tempSet);
				continue;
			}
			
			tempSet = new HashSet<Integer>();
			tempSet.add(questionId);
			indexMap.put(strHash, tempSet);
			
		}
	}
	
}


