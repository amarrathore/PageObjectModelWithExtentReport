package pageObjectModel.automation.utills;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutionException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
	public FileOutputStream fileOut = null;
	public String path;
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	public Excel_Reader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
				}
			}
			row = sheet.getRow(rowNum - 1);
			XSSFCell cell = row.getCell(col_Num);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public String getCellData(String sheetName, int colName, int rowNum) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			row = sheet.getRow(rowNum - 1);
			XSSFCell cell = row.getCell(colName);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public int getRowCount(String sheetName) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getColumnCount(String sheetName) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(0);
				return row.getLastCellNum();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// returns true if data is set successfully else false
		public boolean setCellData(String sheetName, String colName, int rowNum,
				String data) {
			try {
				fis = new FileInputStream(path);
				workbook = new XSSFWorkbook(fis);

				if (rowNum <= 0)
					return false;

				int index = workbook.getSheetIndex(sheetName);
				int colNum = -1;
				if (index == -1)
					return false;

				sheet = workbook.getSheetAt(index);

				row = sheet.getRow(0);
				for (int i = 0; i < row.getLastCellNum(); i++) {
					// System.out.println(row.getCell(i).getStringCellValue().trim());
					if (row.getCell(i).getStringCellValue().trim().equals(colName))
						colNum = i;
				}
				if (colNum == -1)
					return false;

				sheet.autoSizeColumn(colNum);
				row = sheet.getRow(rowNum - 1);
				if (row == null)
					row = sheet.createRow(rowNum - 1);

				cell = row.getCell(colNum);
				if (cell == null)
					cell = row.createCell(colNum);

				// cell style
				// CellStyle cs = workbook.createCellStyle();
				// cs.setWrapText(true);
				// cell.setCellStyle(cs);
				cell.setCellValue(data);

				fileOut = new FileOutputStream(path);

				workbook.write(fileOut);

				fileOut.close();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "//src//test//resources//Login.xlsx";
		Excel_Reader obj = new Excel_Reader(path);
		// System.out.println(obj.getCellData("Login", "UserName", 4));

		System.out.println(obj.getRowCount("Login"));
		
		System.out.println(obj.getColumnCount("Login"));
		
		System.out.println(obj.getCellData("Login", 2, 3));
	}

}
