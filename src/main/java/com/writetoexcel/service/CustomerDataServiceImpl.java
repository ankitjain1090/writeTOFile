package com.writetoexcel.service;

import com.writetoexcel.literal.Message;
import com.writetoexcel.model.CustomerData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {

    @Override
    public String writeDataToExcel(CustomerData customerData , File excelFile){
        String returnMessage;
        if(excelFile.exists()){
           returnMessage = appendDataToExistingFile(customerData,excelFile);
        }
        else {
            returnMessage = createNewFile(customerData,excelFile);
        }
        return returnMessage;
    }

    public String appendDataToExistingFile(CustomerData customerData , File excelFile){
        XSSFRow row;
        try{
            FileInputStream excelFileIPStream = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(excelFileIPStream);
            XSSFSheet spreadsheet = workbook.getSheet("Customer_Info");
            int lastRow = spreadsheet.getLastRowNum();
            row = spreadsheet.createRow(++lastRow);
            int rowId = 0;
            int cellId = 0;
            for (Object obj : customerData.getCustomerData()){
                Cell cell = row.createCell(cellId++);
                switch(obj.getClass().getSimpleName()){
                    case "String"   : cell.setCellValue((String)obj);
                                        break;
                    case "Date"     : cell.setCellValue((Date) obj);
                                        break;
                    case "Calendar" : cell.setCellValue((Calendar) obj);
                                        break;
                    case "Double"   : cell.setCellValue((Double) obj);
                                        break;
                    case "Integer"  : cell.setCellValue((Integer)obj);
                                        break;
                    case "Long"     : cell.setCellValue((Long)obj);
                                        break;
                    case "Float"    : cell.setCellValue((Float)obj);
                                        break;
                    case "Boolean"  : cell.setCellValue((Boolean) obj);
                                        break;
                }
            }
            return writeWorkBook(workbook,excelFile);
        }
        catch (Exception e){
            return excelFile.getName();
        }
    }

    public String createNewFile(CustomerData customerData , File excelFile){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Customer_Info");
        XSSFRow row;
        int rowId = 0;
        int cellId = 0;
        row = spreadsheet.createRow(rowId++);
        int headerCellid = 0 ;
        for(String str : customerData.getClassVariable()){
            Cell headerCell = row.createCell(headerCellid++);
            headerCell.setCellValue(str);
        }
        row = spreadsheet.createRow(rowId++);
        for (Object obj : customerData.getCustomerData()){
            Cell cell = row.createCell(cellId++);
            switch(obj.getClass().getSimpleName()){
                case "String"   : cell.setCellValue((String)obj);
                                  break;
                case "Date"     : cell.setCellValue((Date) obj);
                                  break;
                case "Calendar" : cell.setCellValue((Calendar) obj);
                                  break;
                case "Double"   : cell.setCellValue((Double) obj);
                                  break;
                case "Integer"  : cell.setCellValue((Integer)obj);
                                  break;
                case "Long"     : cell.setCellValue((Long)obj);
                                  break;
                case "Float"    : cell.setCellValue((Float)obj);
                                  break;
                case "Boolean"  : cell.setCellValue((Boolean) obj);
                                  break;
            }
        }
        return  writeWorkBook(workbook,excelFile);
    }

    private String writeWorkBook(XSSFWorkbook workbook , File excelFile){
        try{
            FileOutputStream out  = new FileOutputStream(excelFile);
            workbook.write(out);
            out.close();
            return Message.SUCCESS_MSG;
        }
        catch(FileNotFoundException e){
            return Message.FILE_IO_ERROR;
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
}
