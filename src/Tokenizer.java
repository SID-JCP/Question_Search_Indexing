import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Tokenizer {
	
	StringBuilder accumulator = new StringBuilder();
	
	List<Integer> tempList = null;

	String itemToIgnore = "";
	//Seperate with witespaces 
	//for decimal values keep if present in number else dont add
	
	//items to not add {, ? ! " ` }
	public void tokenize(String text ,Integer id ,  HashMap<Integer, List<Integer>> indexMap) 
	{

		if(text == "")return;
		
		for(int i = 0; i < text.length(); i++) 
		{
			if(text.charAt(i) == ' ') 
			{
				if(accumulator.isEmpty()) 
				{
					continue;
				}
				
				if(indexMap.containsKey(text.hashCode())) 
				{
					tempList = indexMap.get(text.hashCode());
					tempList.add(id);
					
					indexMap.put(text.hashCode(), tempList);
					continue;
					
				}
				
				tempList = new LinkedList<>();
				tempList.add(id);
				
				indexMap.put(text.hashCode(), tempList);
				
				
			}
			
			
			
			if( 
				text.charAt(i) == ',' ||
         		text.charAt(i) == '?' ||
				text.charAt(i) == '!' ||
				text.charAt(i) == '"' ||
				text.charAt(i) == '('				
			   )continue;
		}
		
	}
}
