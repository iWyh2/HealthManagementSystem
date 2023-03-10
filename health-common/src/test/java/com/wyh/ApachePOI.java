package com.wyh;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

public class ApachePOI {
    @Test
    public void readExcel_1() throws IOException {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook("D:\\TempFile\\poi-test.xlsx");
        //获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
        XSSFSheet sheet = workbook.getSheetAt(0);
        //获取当前工作表最后一行的行号，行号从0开始
        int lastRowNum = sheet.getLastRowNum();
        for(int i=0;i<=lastRowNum;i++){
            //根据行号获取行对象
            XSSFRow row = sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for(short j=0;j<lastCellNum;j++){
                //设置单元格数据类型为String，这样才可以读取单元格内的数字类型数据
                row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                String value = row.getCell(j).getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
    }

    @Test
    public void readExcel_2() throws IOException {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(Files.newInputStream(new File("D:\\TempFile\\poi.xlsx").toPath()));
        //获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
        XSSFSheet sheet = workbook.getSheetAt(0);
        //遍历工作表获得行对象
        for (Row row : sheet) {
            //遍历行对象获取单元格对象
            for (Cell cell : row) {
                //设置单元格数据类型为String，这样才可以读取单元格内的数字类型数据
                cell.setCellType(Cell.CELL_TYPE_STRING);
                //获得单元格中的值
                String value = cell.getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
    }

    @Test
    public void writeExcel() throws Exception {
        //在内存中创建一个Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表，指定工作表名称
        XSSFSheet sheet = workbook.createSheet("王钇欢-Apache-POI");

        //创建行，0表示第一行
        XSSFRow row = sheet.createRow(0);
        //创建单元格，0表示第一个单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("名称");
        row.createCell(2).setCellValue("年龄");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("小明");
        row1.createCell(2).setCellValue("10");

        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("2");
        row2.createCell(1).setCellValue("小王");
        row2.createCell(2).setCellValue("20");

        //通过输出流将workbook对象下载到磁盘
        FileOutputStream out = new FileOutputStream("D:\\TempFile\\poi-test.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();
    }
}
