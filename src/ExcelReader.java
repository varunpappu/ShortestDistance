
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import graph.Graph;

/**
 * This example demonstrates opening a workbook and reading its elements
 */
public class ExcelReader {
	public static void main(String[] args) throws IOException {
		FileInputStream fileIn = null;
		FileOutputStream fileOut = null;
		HSSFWorkbook wb = null;
		try {
			fileIn = new FileInputStream("FilePath");
			POIFSFileSystem fs = new POIFSFileSystem(fileIn);
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			// System.out.println("Last row number" + sheet.getLastRowNum());

			int sizeOfArray = sheet.getLastRowNum();
			Graph graph = new Graph(sizeOfArray + 1);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				// System.out.println();
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {
					HSSFRow row0 = sheet.getRow(i);
					double d = row0.getCell(j).getNumericCellValue();
					// System.out.print((int)d + " ");
					graph.addEdge(graph, i, (int) d);
				}
			}

			// graph.printGraph(graph);
			Scanner in = new Scanner(System.in);
			int temples = 0;
			try {
				do {
					System.out.println("Enter the total temples you want to visit:");
					temples = in.nextInt();
				} while (temples > 114 || temples < 1);
				graph.readLinkedList(8, graph, temples);

			} catch (Exception e) {
				System.out.println("Invalid value");
			}
		} finally {
			// if (wb != null)
			// wb.close();
			if (fileOut != null)
				fileOut.close();
			if (fileIn != null)
				fileIn.close();
		}
	}
}
