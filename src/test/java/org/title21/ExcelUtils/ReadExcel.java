/*package org.title21.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	static XSSFRow row1;
	static XSSFCell Cell;
	
	public int getSheetNumber(String Name)
	{
		int SheetNumber=-1;
		
		 for (int i = 0; i < wb.getNumberOfSheets(); i++) {
	
           if (Name.equalsIgnoreCase(wb.getSheetName(i)))
           {
           	SheetNumber = i;
           	break;
           }
        }
		return SheetNumber;
	}
	
	public ReadExcel(String excelPath)
	{
		
		try {
			//System.out.println(excelPath);
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public String getData(String Name, int row, int column)
	{
		int SheetNumber=getSheetNumber(Name);
		DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
		
		sheet1 = wb.getSheetAt(SheetNumber);
		row1  = sheet1.getRow(row);
		Cell cell= row1.getCell(column, row1.RETURN_BLANK_AS_NULL);		
		
		String data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
		//System.out.println(data);
		return data;
	}
	
	public int getRowCount(String Name){
		int SheetNumber=getSheetNumber(Name);
		int row =(wb.getSheetAt(SheetNumber).getPhysicalNumberOfRows())+1;
		int i=0;
		int temp=0;
		sheet1 = wb.getSheetAt(SheetNumber);
		DataFormatter formatter = new DataFormatter();
		for(i=0;i<row;i++)
		{
			//System.out.println(i);
  			try
  			{
  				row1  = sheet1.getRow(i);
  				Cell cell= row1.getCell(0, row1.RETURN_BLANK_AS_NULL);
  				String data = formatter.formatCellValue(cell).trim();
					//System.out.println(data+"data"+data.length());
  				if (cell == null) 
  				{
  					
  					if(data.length()>0)
	  					temp =i;
	  					break;
  				}
  				else
  				{
  					//System.out.println(cell.toString());
  					temp=i;
  				}
  					
  			}
			catch(Exception e)
  			{
				temp=i;
				break;
  			}
  			
		}
		
		return temp;
	}
	
	
	public int findRow(String Name, String cellContent) throws Exception {
		
		int SheetNumber=getSheetNumber(Name);
		
		sheet1 = wb.getSheetAt(SheetNumber);
		System.out.println(SheetNumber);

		int patchColumn = -1;
		for (Row row : sheet1) {
	        for (Cell cell : row) {
	            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
	            	if (cell.getRichStringCellValue().getString().trim().equals(cellContent)||cell.getRichStringCellValue().getString().trim().contains(cellContent)) {
	                	//System.out.print(cell.getRowIndex()+"  "+cell.getColumnIndex());
	                    return cell.getRowIndex();  
	                }
	            }
	        }
	    }               
	
		
		return patchColumn;
	}
	
	public int findCol(String Name, String cellContent) throws Exception {
		
		int SheetNumber=getSheetNumber(Name);
		int col=0;
		sheet1 = wb.getSheetAt(SheetNumber);
		//System.out.println(SheetNumber);

		int patchColumn = -1;
		for (Row row : sheet1) {
	        for (Cell cell : row) {
	            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
	                if (cell.getRichStringCellValue().getString().trim().equals(cellContent)||cell.getRichStringCellValue().getString().trim().contains(cellContent)) {
	                	col =Integer.parseInt((String)Integer.toString(cell.getColumnIndex()).substring(0));
	                    return col;
	                }
	            }
	        }
	    }               
	
		
		return patchColumn;
	}
	
	
	public void DeleteInfile(String Name,int row,int lastRow,String Path)
	{
		int SheetNumber=getSheetNumber(Name);
		sheet1 = wb.getSheetAt(SheetNumber);
		int n=lastRow-1;
		System.out.println(n);
		if(n>1)
		{
			try
			{
				for(int i=row;row<lastRow;i++)
				{
					row1= sheet1.getRow(i);
					if(row1 == null)
						break;
					else
						sheet1.removeRow(row1);
				}
				FileOutputStream fileOut = new FileOutputStream(Path);
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
			
			}
			catch(Exception e)
			{
	
				System.out.println(e);
	
			}
		}
		else
		{
			System.out.println("Nothing To Delete");
		}
	}
	
	public void WriteInfile(String Name,int row,int col,String content,String Path)
	{
		int SheetNumber=getSheetNumber(Name);
		
		sheet1 = wb.getSheetAt(SheetNumber);
		
		try{
			 
  			row1  = sheet1.getRow(row);
  			try
  			{
  				if (row1==null)
  				{
  					row1 = sheet1.createRow(row);
  				}
  				Cell = row1.getCell(col, row1.RETURN_BLANK_AS_NULL);
  				if (Cell == null) 
  				{

  					Cell = row1.createCell(col);

  					Cell.setCellType(Cell.CELL_TYPE_STRING);
  					Cell.setCellValue((String)content);

  				} else 
  				{

  					Cell.setCellValue(content);

  				}
  			}
			catch(Exception e)
  			{
				Cell = row1.createCell(col);

				Cell.setCellValue(content);

  			}
			

  			FileOutputStream fileOut = new FileOutputStream(Path);

			wb.write(fileOut);

			fileOut.flush();

				fileOut.close();

			}catch(Exception e){

				System.out.println(e);

			}
	}
	public void testCase_information(String Path,String sheetToStorIn,String StringTofindTheRow, int col, String testResult) throws Exception
	{
		System.out.println(sheetToStorIn+" "+StringTofindTheRow);
		ReadExcel r1 = new ReadExcel(Path);
		int row =(r1.findRow(sheetToStorIn, StringTofindTheRow));
		r1.WriteInfile(sheetToStorIn, row, col, testResult,Path);
	}
	
	public static void main(String args[]) throws Exception
	{
		ReadExcel r1 = new ReadExcel("./src/testData/BalSRSheet.xlsx");
		
		//r1.DeleteInfile("TestCase", 1, 20, "./src/testData/BalSRSheet.xlsx");
	}
		//JST a EXample
		try {
			//demo = [LIST OF STRING that to be WRITen in File ];
			ReadExcel r1 = new ReadExcel("C:\\WORKBOOK.xlsx");
			for(int i=0;i<demo.length;i++){
				int row = i+1;
				for(int j=0;j<demo[i].length;j++){
					System.out.print(demo[i][j]);
					
					int col = j+1;
				r1.WriteInfile("TestCase", row,col , demo[i][j],"C:\\WORKBOOKNAmE.xlsx");
				}
			
			}
			//JST a EXample
			r1.WriteInfile(SHEETNAmE, ROW,COL , "writing","C:\\WORKBOOKNAmE.xlsx");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			
		}
		
			
		}
	}

	
	
*/