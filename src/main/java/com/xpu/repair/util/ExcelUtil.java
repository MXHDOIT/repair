package com.xpu.repair.util;

import javax.servlet.http.HttpServletResponse;

import com.xpu.repair.pojo.ExcelData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
;
import java.awt.Color;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;


public class ExcelUtil {
    public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");

        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));

        //把内容写入Excel表
        exportExcel(data, response.getOutputStream());
    }


    /**
     * 把内容写入Excel表
     * @param data 传入要写的内容，此处以一个ExcelData分装类内容为例
     * @param out 把输出流怼到要写入的Excel上，然后往里面写数据
     * @author lemon
     * @since 2019/11/2 0002
     */
    public static void exportExcel(ExcelData data, OutputStream out) throws Exception {

        /**
         　　　　使用的是JAVA POI实现的导出Excel表；
         　　　　POI 提供了对2003版本的Excel的支持 ---- HSSFWorkbook
         　　　　POI 提供了对2007版本以及更高版本的支持 ---- XSSFWorkbook
         */

        //创建Excel工作簿
        XSSFWorkbook wb = new XSSFWorkbook();

        try {
            //设置工作表名称 为空默名认为Sheet1
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }

            //创建Excel工作表
            XSSFSheet sheet = wb.createSheet(sheetName);


            //参数：  工作簿、工作表、数据
            writeExcel(wb, sheet, data);
            wb.write(out);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            //此处需要关闭 wb 变量
            out.close();
        }
    }


    private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {

        int rowIndex = 0;

        //写标题内容
        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());

        //填充和颜色设置
        writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);

        //自动根据长度调整单元格长度
        autoSizeColumns(sheet, data.getTitles().size() + 1);

    }


    //写标题内容
    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {

        //行数
        int rowIndex = 0;
        //列数
        int colIndex = 0;


        //字体类
        Font titleFont = wb.createFont();

        titleFont.setFontName("simsun");
        //titleFont.setBoldweight(Short.MAX_VALUE);
        // titleFont.setFontHeightInPoints((short) 14);
        titleFont.setColor(IndexedColors.BLACK.index);
        //创建单元格样式
        XSSFCellStyle titleStyle = wb.createCellStyle();
        //titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

        //自定义颜色
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        //titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        //设置字体样式
        titleStyle.setFont(titleFont);
        //设置边框
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        //创建Excel工作表的行
        Row titleRow = sheet.createRow(rowIndex);
        // titleRow.setHeightInPoints(25);

        colIndex = 0;

        for (String field : titles) {
            //创建Excel工作表指定行的单元格
            Cell cell = titleRow.createCell(colIndex);

            //单元格内容
            cell.setCellValue(field);
            //自定义颜色
            cell.setCellStyle(titleStyle);
            colIndex++;
        }

        rowIndex++;
        return rowIndex;
    }


    //填充和颜色设置
    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex = 0;
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        // dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        //dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            // dataRow.setHeightInPoints(25);
            colIndex = 0;

            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }

                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    //自动根据长度调整单元格长度
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {

        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            //自动根据长度调整单元格长度
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    //设置边框
    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        //上边框
        style.setBorderTop(border);
        //左边框
        style.setBorderLeft(border);
        //右边框
        style.setBorderRight(border);
        //下边框
        style.setBorderBottom(border);

        //边框样式
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }
}