package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		File tsvFile = new File("mountains.tsv");
		Scanner dbReader = new Scanner(tsvFile, "UTF-8");
		boolean isHeader = true;
		while(dbReader.hasNextLine()) {
			if (isHeader) {
				System.out.println(dbReader.nextLine());
				isHeader = false;
				continue;
			}
			String row = dbReader.nextLine();
			String[] record = row.split("\t");
			Scanner elevParser = new Scanner(record[4]);
			double elev = elevParser.nextDouble();
			if (record[4].endsWith("ft")) {
				elev = elev * 0.3048;
				System.out.printf("%s: %d m [!]\n", record[1], (int)elev);
			} else {
				System.out.printf("%s: %d m\n", record[1], (int)elev);
			}
			elevParser.close();
		}
		dbReader.close();
	}
}
