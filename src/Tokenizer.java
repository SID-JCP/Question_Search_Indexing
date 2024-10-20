import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Tokenizer {
	//Seprate with whitespaces 
	//for decimal values keep if present in number else dont add
		
	//items to not add {, ? ! " ` }
	
	
	static StringBuilder accumulator = new StringBuilder();
	
	static HashSet<Integer> tempList = null;

	static String itemToIgnore = "!@#$%^&*(),<>/?:;'{}[]()~`";
	
	public static void index(String text ,Integer id ,  HashMap<Integer, HashSet<Integer>> indexMap) 
	{

		if(text == "")return;
		
		//prevent last word from getting ignored as loop terminates 
		text = text + " ";
		
		
		for(int i = 0; i < text.length(); i++) 
		{
			if(text.charAt(i) == ' ') 
			{
				if(accumulator.isEmpty()) 
				{
					continue;
				}
				
				if(indexMap.containsKey(accumulator.toString().toLowerCase().hashCode())) 
				{
					tempList = indexMap.get(accumulator.toString().toLowerCase().hashCode());
					tempList.add(id);
					
					indexMap.put(accumulator.
							toString().
							toLowerCase().
							hashCode(), tempList);
					
					
					accumulator.setLength(0);					
					continue;
					
				}
				
				tempList = new HashSet<Integer>();
				tempList.add(id);
				
				indexMap.put(accumulator.
						toString().
						toLowerCase().
						hashCode(), tempList);
				
			
				accumulator.setLength(0);
				continue;
				
			}
			
			
			if(itemToIgnore.indexOf(text.charAt(i)) == -1) 
			{
				accumulator.append(text.charAt(i));
				
			}
						
		}
		
	}
}
