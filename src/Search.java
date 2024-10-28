import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	        
	    return outputCollector.toString().toLowerCase().split("\n");
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
	
	
	public static void search(HashMap<Integer , HashSet<Integer>> indexMap , String searchText) 
	{
	
		String[] tokens = tokenize(searchText);		
		if(tokens == null)return;
		
		//hashSet for retrival and setup
		HashSet<Integer> idFromIndex = null;
		
		//list to use for putting question id with count in hashMap 
		LinkedList<Integer> questionIdOfSameCount = null;
		
		//list to combine hashSets into one 
		List<Integer> questonIdAccumulator = new LinkedList<>();
		
		//list of only count of questionID
		List<Integer> onlyCount = new LinkedList<>();
		
		//map the questionId to the count of those Id's to retrieve later for  match based result 
		HashMap<Integer , LinkedList<Integer>> questionIdOfCount = new HashMap<>();
		
		for(String str : tokens) 
		{
			idFromIndex = indexMap.get(str.hashCode());
			if(idFromIndex != null) 
			{
				
				questonIdAccumulator.addAll(idFromIndex);
				
			}
		}
		
		if(questonIdAccumulator.isEmpty()) {System.out.println("No matching elemtns in Index");}
		
		//sort accumulated questionID
		Collections.sort(questonIdAccumulator);
		
		
		int currentQuestionId = 0;
		int currentQuestionIdCount = 0;
		
		currentQuestionId = questonIdAccumulator.get(0);
		currentQuestionIdCount++;
		
		//count the ocuerrence of each questionId		
		for(int j  = 1; j < questonIdAccumulator.size(); j++) 
		{
			if(currentQuestionId == questonIdAccumulator.get(j)) 
			{
				
				currentQuestionIdCount++;
				continue;
			}
			
			/* After finding count put in map */
			
			//if id with same count already exist
			if(questionIdOfCount.containsKey(currentQuestionIdCount)) 
			{
				questionIdOfSameCount = questionIdOfCount.get(currentQuestionIdCount);
				questionIdOfSameCount.add(currentQuestionId);
				
				questionIdOfCount.replace(currentQuestionIdCount, questionIdOfSameCount);
				
				
				//get new questionID and reset count 
				currentQuestionId = questonIdAccumulator.get(j);
				currentQuestionIdCount = 1;
				continue;
			}
			
			//if new Id found put in new list and add
			questionIdOfSameCount = new LinkedList<>();			
			questionIdOfSameCount.add(currentQuestionId);
			
			questionIdOfCount.put(currentQuestionIdCount, questionIdOfSameCount);
			
			
			//add count of Id's for retrival in order
			onlyCount.add(currentQuestionIdCount);
			
			//get new questionID and reset count 
			currentQuestionId = questonIdAccumulator.get(j);
			currentQuestionIdCount = 1;
			
			
			
		}
		
		//for the count for last questionId 
		if(questionIdOfCount.containsKey(currentQuestionIdCount)) 
		{
			questionIdOfSameCount = questionIdOfCount.get(currentQuestionIdCount);
			questionIdOfSameCount.add(currentQuestionId);
			
			questionIdOfCount.replace(currentQuestionIdCount, questionIdOfSameCount);
			
			
			
		}else {
			questionIdOfSameCount = new LinkedList<>();			
			questionIdOfSameCount.add(currentQuestionId);
			
			questionIdOfCount.put(currentQuestionIdCount, questionIdOfSameCount);
		
			onlyCount.add(currentQuestionIdCount);
		}
		
		
		
				
		
		
		//sort keys of hashMap in descending order 		
		Collections.sort(onlyCount);		
		Collections.reverse(onlyCount);
		
		
		
		
		
		//returning max 10 questionId based on closest match
		short results = 1;
		List<Integer> questionId;
		
		for(int i : onlyCount) 
		{
			questionId = questionIdOfCount.get(i);
			
			for(int Id : questionId) 
			{
				System.out.println("Closest Match: " + Id);
				results++;
				if(results == 10)break;
			}
			
			if(results == 10)break;
		}
	}
	
	
	
}


