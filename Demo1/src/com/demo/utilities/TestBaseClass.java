package com.demo.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.demo.pages.HomePage;

public class TestBaseClass {
	
	public static WebDriver driver = null;
	public static String chromePath = "./resources/drivers/chromedriver.exe";
	
	//@BeforeSuite
	public void launchDriver(){
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static WebElement getWebElement(By by){
		return driver.findElement(by);
	}
	
	public static List<WebElement> getWebElements(By by){
		return driver.findElements(by);
	}
	
	public static void isElementVisibleByLocator(By by, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public static void scroller(int x, int y){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy("+x+", "+y+")");
	}
	
	public static void mouseHover(By by){
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(by)).build().perform();
	}
	
	public static void readExcelData() throws Exception{
		FileInputStream file = 
				new FileInputStream(new File("./resources/files/1111.xlsx"));
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		while(rowIterator.hasNext()){
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				
				switch(cell.getCellType()){
				case STRING:
					System.out.print(cell.getStringCellValue());
					break;
					
				case NUMERIC:
					System.out.print(cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					break;
				}
			}
			System.out.println("");
		}
	}
	
	/*public static void readExcelData() throws Exception{
		FileInputStream file = 
				new FileInputStream(new File("./resources/files/1111.xlsx"));
		
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		
		XSSFSheet sheet = workBook.getSheetAt(0);
		
		Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext()){
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while(cellIterator.hasNext()){
				
				Cell cell = cellIterator.next();
				//System.out.println(cell.getCellType());
				switch(cell.getCellType()){
					case STRING: 
						System.out.print(cell.getStringCellValue() +"\t");
						break;
					case NUMERIC:
						System.out.print(cell.getNumericCellValue() +"\t");
						break;
					case BOOLEAN:
						System.out.print(cell.getBooleanCellValue() +"\t");
						break;
				}
			}
			System.out.println("");
		}
		file.close();
	}*/
	
	
	public static void writeExcelData() throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("Employee Data");
		
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] {"ID", "Name", "LastName"});
		data.put("2", new Object[] {1, "Amit", "Shah"});
		data.put("3", new Object[] {2, "Narendra", "Modi"});
		data.put("4", new Object[] {3, "Vijay", "Rupani"});
		
		Set<String> keySet = data.keySet();
		int rowNumber = 0;
		for(String key:keySet){
			Row row = sheet.createRow(rowNumber+1);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			
			for(Object obj: objArr){
				Cell cell = row.createCell(cellnum+1);
				if(obj instanceof String){
					cell.setCellValue((String)obj);
				} else if(obj instanceof Integer){
					cell.setCellValue((Integer)obj);
				}
			}
			
			 FileOutputStream out = new FileOutputStream(new File("./resources/files/1111.xlsx"));
			 workbook.write(out);
			 out.close();
			 System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		}
	}
	
	public static void writeExcelDataExample(){
		//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[] {1, "Amit", "Shukla"});
        data.put("3", new Object[] {2, "Lokesh", "Gupta"});
        data.put("4", new Object[] {3, "John", "Adwards"});
        data.put("5", new Object[] {4, "Brian", "Schultz"});
          
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("./resources/files/1111.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	//public static HomePage homePage;
	public static HomePage homePage(){
		return new HomePage();
	}

}
