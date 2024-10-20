import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;




public class FileLoadWriteManager {
	
	public static String textFromDB = "";
	public static Integer Id = 0;
	
	//Key -> Hash Value of text in sentence 
	//Values -> List Of QuestionId/Question Number(Index Values) in which the word appeared 
	
	static HashMap<Integer , HashSet<Integer>> indexMap = new HashMap<>();
	
	static HashMap<Integer , HashSet<Integer>> loadedIndexMap = null;
	
	

	public static void main(String[] args) 
	{
		
		
//		Tokenizer.index(textFromDB, Id, indexMap);
//		
//		try {
//			
//			indexData("Questions/testQuestions.txt");
//			
//			
//		} catch (FileNotFoundException e) {
//			
//			e.printStackTrace();
//		}
//		
//		System.out.println("Tokens Indexed: " + indexMap.size());
//
//		
//		try {
//			writeFile(indexMap , "IndexedFile/indexedQuestion.index");
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
		
		try {
			
			loadFile("IndexedFile/indexedQuestion.index");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	private static void indexData(String path) throws FileNotFoundException 
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
		String currentLine = null;
		
		try {
			currentLine = br.readLine();
			
			while(currentLine != null) 
			{
				
				if(currentLine.isBlank()) 
				{
					Id++;
					currentLine = br.readLine();
					continue;
				}
				
				Tokenizer.index(currentLine, Id, indexMap);
				currentLine = br.readLine();
			}
			
			
						
		} catch (IOException e) {e.printStackTrace();}
		
		
		
	}
	
	private static void writeFile(HashMap<Integer , HashSet<Integer>> indexedValues , String name) throws IOException 
	{
		File indexFile = new File(name);
		
		FileOutputStream fos = new FileOutputStream(indexFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(indexedValues);
		oos.flush();
		oos.close();
		
		fos.close();
	}
	
	
	private static void loadFile(String path) throws IOException 
	{
	

		FileInputStream fis = new FileInputStream(path);		
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			loadedIndexMap = (HashMap<Integer, HashSet<Integer>>)ois.readObject();
			ois.close();
			fis.close();
			
			System.out.println(loadedIndexMap);
			
		} catch (ClassNotFoundException | IOException e) {
			
			
			e.printStackTrace();
		}
	}

}
