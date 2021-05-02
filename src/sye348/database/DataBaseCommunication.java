package sye348.database;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class DataBaseCommunication 
{
	
	private static FileWriter writer;
	private static Scanner reader;
	
	static 
	{
		try 
		{
			File file = new File("/storage/userdata.txt");
			file.createNewFile(); //only creates if it doesn't already exist
			writer = new FileWriter(file);
			reader = new Scanner(file);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the user data based on the previously determined format.
	 * @return The 
	 */
	public static String[] getUserData()
	{
		int numLines = reader.nextInt();
		String[] lines = new String[numLines];
		for (int i = 0; i < numLines; i++)
			lines[i] = reader.nextLine();
		//The data contained in lines is as follows [name]
		return lines;
	}
	
	/**
	 * @param data The actual data
	 * @return If an error was thrown or not
	 */
	public static boolean saveUserData(String[] data)
	{
		for (String line : data)
		{
			try 
			{
				writer.write(line);
				writer.flush();
			}
			catch (IOException e) 
			{
				return true;
			}
			
		}
		return false;
	}
	
}
