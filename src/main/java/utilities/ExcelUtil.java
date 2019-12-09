package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import pageObjects.BasePage;

public class ExcelUtil extends BasePage {
	XSSFWorkbook wb;

	@DataProvider
	public Object[][] getData() throws IOException {
		File file = new File(projectPath + "\\src\\test\\java\\resources\\LoginTestData.xlsx");

		FileInputStream fis = new FileInputStream(file);

		wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		wb.close();
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> datamap = new HashMap<>();
			for (int j = 0; j < lastCellNum; j++) {
				XSSFCell key = sheet.getRow(0).getCell(j);
				XSSFCell value = sheet.getRow(i + 1).getCell(j);
				if (value.getCellTypeEnum() == CellType.NUMERIC) {
					datamap.put(key.getStringCellValue(), new DataFormatter().formatCellValue(value));
				} else {
					datamap.put(key.getStringCellValue(), value.getStringCellValue());
				}

			}
			obj[i][0] = datamap;

		}
		return obj;
	}
}