package csvdemo1;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class NewCsvDem {

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		String path = System.getProperty("user.dir");
		if(Validate(path))
		{
			path=path+"\\data.csv";
			System.out.println(path);
			int row = sc.nextInt();
			int col = sc.nextInt();
			String value = sc.next();
			Update(path,row,col,value);
		}
		else
		{
			//Nothing
		}

	}

	private static void Update(String path, int row, int col, String value) throws IOException 
	{
		ArrayList<ArrayList<String>> List = CsvToList(path);
		List.get(row).set(col,value);
		String newpath = System.getProperty("user.dir");
		if(Validate(newpath))
		{
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(newpath+"\\Data2.csv"));
			CSVPrinter print = new CSVPrinter(writer,CSVFormat.DEFAULT);
				for(ArrayList<String> l:List)
					print.printRecord(l);
				print.flush();
		}
	}

	private static ArrayList<ArrayList<String>> CsvToList(String path) throws IOException {
		Reader filereader = new FileReader(path);
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withSkipHeaderRecord().parse(filereader);
		ArrayList<ArrayList<String>> list=new ArrayList<>();
		for(CSVRecord record:records)
		{
			list.add(new ArrayList<>(Arrays.asList(record.get(0),record.get(1),record.get(2))));
		}
		System.out.println(list);
		return list;
	}

	private static boolean Validate(String path) {
		
		if(path==null)
		{
			System.out.println("Given path is not Valid");
			return false;
		}
		else
		{
			if(path.length()<=0)
			{
				System.out.println("Given path is empty");
				return false;
			}
			else
				return true;
		}
	}

}
