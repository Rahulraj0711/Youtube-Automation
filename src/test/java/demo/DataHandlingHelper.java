package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataHandlingHelper {
    
    @DataProvider(name = "ytTestData")
    public static Object[][] dataProviderMethod() throws IOException {
        Object[][] data=null;
        File file=new File(System.getProperty("user.dir")+"/src/test/resources/YTSearchData.xlsx");
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        XSSFSheet sheet=workbook.getSheet("Sheet1");

        int rows=sheet.getLastRowNum();
        int cols=sheet.getRow(0).getPhysicalNumberOfCells();
        data=new Object[rows][cols];

        for(int i=0;i<rows;i++) {
            XSSFRow row=sheet.getRow(i+1);
            for(int j=0;j<cols;j++) {
                XSSFCell cell=row.getCell(j);
                if(cell.getCellType()==CellType.STRING) {
                    data[i][j]=cell.getStringCellValue();
                }
            }
        }

        fis.close();
        workbook.close();
        
        return data;
    }
}
