import java.io.*;
import java.util.*;

public class DataGenerator {
	
	static File testin = new File("testgen.in");
	
	
	public static void main(String[] args) {
		
		
		try
		{
		
		FileWriter writerFile = new FileWriter(testin, true);
		PrintWriter writer = new PrintWriter(writerFile);
		
		int additionRemovalMode;
		int searchMode = 0;
		int multisetSize;
		
		String[] elements = {"robot", "fortune", "macbook", "apple", "clerk", "buddha", "student", "xylophone", "microphone"};
		Random aRPicker = new Random(System.nanoTime());
		int aRModeOdds = 0;
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Select the multiset size: ");
		multisetSize = reader.nextInt();
		
		System.out.println("Select the ratio of removals to additions:\n"
				+ "1. Roughly equal\n"
				+ "2. More additions (growing list)\n"
				+ "3. More removals (shrinking list)");
		additionRemovalMode = reader.nextInt();
		
		switch (additionRemovalMode)
		{
			case 1:
				aRModeOdds = 2;
				break;
			case 2:
				aRModeOdds = 3;
				break;
			case 3:
				aRModeOdds = 3;
				break;
			default:
				break;
		}
		
		System.out.println("Select a search to addition/removal ratio: \n"
				+ "1. 1:1\n"
				+ "2. 1:2\n"
				+ "3. 2:1\n"
				+ "4. 1:10\n"
				+ "5. 10:1\n");
		searchMode = reader.nextInt();
		
		
		System.out.println("Select a maximum number of instructions: \n"
				+ "8\n"
				+ "10\n"
				+ "12\n"
				+ "14\n"
				+ "16\n");
		int max = reader.nextInt();
		
		
		
		int index = 0;
		while (index < multisetSize)
		{
			add(elements, writer);
			index++;
		}
		int numAdds = 0;
		for (int i = 0; i < max; i++)
		{
			if (aRPicker.nextInt(aRModeOdds) < 1 && aRModeOdds != 3)
			{
				add(elements, writer);
				numAdds++;
			}
			else
			{
				remove(elements, writer);
				numAdds++;
			}
			switch (searchMode)
			{
			case 1:
				search(elements, writer);
				
				break;
			case 2:
				if (numAdds%2 == 0)
				{
					search(elements, writer);
				}
				break;
			case 3:
				search(elements, writer);
				search(elements, writer);
				break;
			case 4:
				if (numAdds%10 == 0)
				{
					search(elements, writer);
				}
				break;
			case 5:
				for (int j=0; j < 11; j++)
				{
					search(elements, writer);
				}
				break;
			default:
				break;
			}
		}
		
		writer.write("P");
		writer.write("Q");
		writer.close();
		writerFile.close();
		
		} catch (Exception E)
		{
			
		}
	}
	
	private static void search(String[] elements, PrintWriter writer) {
		
		String command = "S";
		printToFile(command, writer, elements);
		
		
	}

	private static void remove(String[] elements, PrintWriter writer) {
		Random oneOrAll = new Random(System.nanoTime());
		
		String command;
		if (oneOrAll.nextInt(2) == 1)
		{
			command = "RO";
		}
		else
		{
			command = "RA";
		}
		printToFile(command, writer, elements);
		
		
	}

	public static void add(String[] elements, PrintWriter writer)
	{
		String command = "A";
		printToFile(command, writer, elements);
	}
	
	public static void printToFile(String command, PrintWriter writer, String[] elements)
	{
		Random elementPicker = new Random(System.nanoTime());
		int elementIndex = elementPicker.nextInt(elements.length);
		
		writer.println(command + " " + elements[elementIndex]);
		
		
	}

}
