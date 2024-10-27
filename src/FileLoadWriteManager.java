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
	
	public static String textFromDB = "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains. On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.";
	public static Integer Id = 0;
	
	//Key -> Hash Value of text in sentence 
	//Values -> List Of QuestionId/Question Number(Index Values) in which the word appeared 
	
	static HashMap<Integer , HashSet<Integer>> indexMap = new HashMap<>();
	
	static HashMap<Integer , HashSet<Integer>> loadedIndexMap = null;
	
	static String[] tokens;
	

	public static void main(String[] args) 
	{
	
		

		long start = System.currentTimeMillis();
		
		//1200 characters in 1.4ms WoW!
		Search.index(indexMap, 0, textFromDB);
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		

		
		try {
			
			indexData("Questions/testQuestions.txt");
			
			
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		
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
		
//		try {
//			
//			loadFile("IndexedFile/indexedQuestion.index");
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
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
