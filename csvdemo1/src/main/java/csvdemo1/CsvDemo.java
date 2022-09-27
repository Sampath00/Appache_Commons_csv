package csvdemo1;
import java.util.*;
import java.io.*;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
class student
{
	String ID="";
	String Name="";
	String Age="";
	student(String id,String name,String age)
	{
		ID=id;
		Name=name;
		Age=age;	
	}
}
public class CsvDemo {
	
	private static void PrintCSVFILE(String filepath) throws IOException
	{
		Reader filereader = new FileReader(filepath);
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("ID","Name","Age").parse(filereader);
		records.forEach(record->{
			System.out.println(record.toMap());
		});
	}
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		String filepath = "C:\\Users\\Maahesh Munna\\Documents\\data1.csv";
		System.out.println("Enter Number Of Operations");
		int n=sc.nextInt();
		List<Integer> operations = new ArrayList<>();
		System.out.println("1: Adding 2:Updating 3:Searching");
		for(int i=0;i<n;i++)
		{
			operations.add(sc.nextInt());
		}
		for(int i : operations)
		{
			if(i==1)
			{
				System.out.println("Enter Number of Records");
				int m=sc.nextInt();
				List<student> list = CSVtoList(filepath);
				for(int k=0;k<m;k++)
				{
					String id=sc.next();
					String name=sc.next();
					String age=sc.next();
					list.add(new student(id,name,age));
				}
				System.out.println("Before Adding");
				PrintCSVFILE(filepath);
				AddingCSVFILE(filepath,list);
				System.out.println("After Adding");
				PrintCSVFILE(filepath);
			}
			else if(i==2)
			{
				System.out.println("Enter the Updating Data");
				String actual = sc.next();
				String replace = sc.next();
				System.out.println("Before Updation");
				PrintCSVFILE(filepath);
				UpdateInCSVFILE(filepath,actual,replace);
				System.out.println("After Updation");
				PrintCSVFILE(filepath);
			}
			else
			{
				System.out.println("Enter name to Search");
				String str=sc.next();
				Searching(filepath,str);
			}
		}
	}
	private static void UpdateInCSVFILE(String filepath, String actual, String replace) throws IOException {
		// TODO Auto-generated method stub
		List<student> list = CSVtoList(filepath);
		PrintWriter writer = new PrintWriter(new File(filepath));
		try {
			@SuppressWarnings("deprecation")
			CSVPrinter csvprinter = new CSVPrinter(writer,CSVFormat.DEFAULT.withSkipHeaderRecord());
			for(student stu : list)
			{
				String s=stu.Name;
				List<String> data;
				if(s.equals(actual))
					data=(Arrays.asList(stu.ID,replace,stu.Age));
				else
					data = Arrays.asList(stu.ID,stu.Name,stu.Age);
				csvprinter.printRecord(data);
				csvprinter.flush();
			}
			csvprinter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void Searching(String filepath, String str) throws IOException {
		// TODO Auto-generated method stub
		Reader filereader = new FileReader(filepath);
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("ID","Name","Age").parse(filereader);
		for(CSVRecord record:records)
		{
			if(record.get("Name").equals(str))
				System.out.println(record.get(0)+" "+record.get(1)+" "+record.get(2));
		}
	}
	private static List<student> CSVtoList(String filepath) throws IOException {
		Reader filereader = new FileReader(filepath);
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withSkipHeaderRecord().parse(filereader);
		List<student> list=new ArrayList<>();
		for(CSVRecord record:records)
		{
			student s=new student(record.get(0),record.get(1),record.get(2));
			list.add(s);
		}
		return list;
	}
	private static void AddingCSVFILE(String filepath, List<student> list) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fileWriter = new FileWriter(filepath, true);
		PrintWriter writer = new PrintWriter(fileWriter);
		try {
			@SuppressWarnings("deprecation")
			CSVPrinter csvprinter = new CSVPrinter(writer,CSVFormat.DEFAULT.withSkipHeaderRecord());
			for(student stu : list)
			{
				List<String> data = Arrays.asList(stu.ID,stu.Name,stu.Age);
				csvprinter.printRecord(data);
				csvprinter.flush();
			}
			csvprinter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
