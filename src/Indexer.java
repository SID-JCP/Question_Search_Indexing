import java.util.LinkedList;
import java.util.List;

public class Indexer 
{
	
//	ignore these characters
//	static String itemToIgnore = "!@#$%^&*(),<>/?:;'{}[]()~`";
	

	static List<Character> threeGram = new LinkedList<>();
	
	//size for threeGram list , MAX = 3
	static short listSize = 0; 
	
	static String accumulator = "";
	
	static Character currentChar;
	
	static String[] tokens;

	
//	public static void index(String text) 
//	{
//		
//		if(text.length() < 3)return;
////		StringBuilder outputCollector = new StringBuilder(text.length());
//		
//		for(short i = 0; i < text.length(); i++) 
//		{
//			currentChar = text.charAt(i);
//			
//			if(listSize >= 3) 
//			{
////				threeGram.forEach(letter -> {
////					accumulator += letter;
////				});
//				
////				for(Character letter : threeGram) 
////				{
////					
////				}
//						
//				
////				outputCollector.append(threeGram.get(0)).
////								append(threeGram.get(1)).
////								append(threeGram.get(2));
//				
//				
//				//add to index
////				System.out.println(accumulator);
//				
//				
//				
//				threeGram.remove(0);
//				listSize--;
//				accumulator = "";
//			}
//			
//			if(currentChar != ' ') 
//			{
//				threeGram.add(currentChar);
//				listSize++;
////				continue;
//			}else 
//			{
//				//if we get a whitespace then it must be 2 or 1 or 0 character in length 
//				//so we skip 
//				
//				threeGram.removeAll(threeGram);
//				listSize = 0;
//			}
//			
//			
//			
//			
//			
//			
//			
//			
//		}
//		
//		
//		
//			
//			
//		
//	}
	
	public static String[] tokenize(String text) 
	{
        // Return if the text is null or shorter than 3 characters
	    if (text == null || text.length() < 3) return tokens;
	    
	        // Initialize a StringBuilder to collect the output
	    StringBuilder outputCollector = new StringBuilder(text.length());
	    
	        // Use a smaller datatype (short) for the count
	    short count = 0;
	    
	        // Variable for the current character
	    char currentChar;
	    
	        // Loop over all characters in the text
	    
	    
	    for (short i = 0; i < text.length(); i++) 
	    { // Use smaller datatype
	            // Get the current character in the text
	        currentChar = text.charAt(i);
	            // If the current character is not a space
	        if (currentChar != ' ') 
	        {
	                // If 2 characters have already been collected
	            if (count >= 2) 
	            {
	                // Append the last three characters to the outputCollector
	                outputCollector.append(text.charAt(i - 2))
	                        .append(text.charAt(i - 1))
	                        .append(currentChar).append('\n');	                
	         
	            }
	            
	            
	                // Increment the count
	            count++;
	        } else {
	        	
	            count = 0; // Reset count if space is encountered
	        }
	    }
	        // Output all at once
	    tokens = outputCollector.toString().split("/n");
	    
	    return tokens;
	}

	
}


