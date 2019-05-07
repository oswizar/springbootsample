package com.xiexing.springbootdemo.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    public static String exportMyExcel(XSSFWorkbook wb, String fileName, String excelPath) {
        String filePath = excelPath;
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File fileExc : files) {
                fileExc.delete();
            }
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        try {
            // 创建文件输出流 fileName代表当前
            fileOut = new FileOutputStream(filePath + fileName);
            // 将文件数据写入数据流，生成本地文件
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }

    /**
     * 导出Excel进行数据导出
     *
     * @param title    Excel进行标题设置
     * @param col      Excel进行列设置
     * @param fileName 文件名
     * @param lists    数据集
     * @return
     */
    public static String exportExcelUtils(String title, String col, String fileName, List<List<String>> lists, String excelPath) {
        String filePath = excelPath;
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File fileExc : files) {
                fileExc.delete();
            }
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            ExcelUtils.createExcelAndSetListData(title, filePath + fileName, col, lists);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static FileOutputStream createExcelAndSetListData(String title, String fileName, String col, List<List<String>> objList) throws IOException {

        List<List<String>> list = objListToList(objList, col);

        //创建一个EXCEL
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建一个SHEET
        Sheet sheet = wb.createSheet("sheet1");
        String[] titles = title.split(",");
        int i = 0;
        //创建一行
        Row row = sheet.createRow((short) 0);
        //填充标题
        for (String s : titles) {
            // 创建单元格
            Cell cell = row.createCell(i);
            // 将数据写入单元格
            cell.setCellValue(s);
            i++;
        }

        // 将数据写入Excel
        int index = 1;
        for (List<String> rows : list) {
            Row row2 = sheet.createRow(index);
            int col1 = 0;
            for (String param : rows) {
                Cell cell = row2.createCell(col1);
                cell.setCellValue(param);
                col1++;
            }
            index++;
        }

        // 创建文件输出流 fileName代表当前
        FileOutputStream fileOut = new FileOutputStream(fileName);
        // 将文件数据写入数据流，生成本地文件
        wb.write(fileOut);

        return fileOut;
    }

//    /**
//     * 创建excel并填充数据
//     * @param title         Excel第一行数据
//     * @param fileName      存储的文件路径及文件名
//     * @param objList       List数据
//     * @return
//     * @throws IOException
//     */
//    private static FileOutputStream createExcelAndSetObjData(String title, String fileName, List<? extends Object> objList,String col) throws IOException {
//        return createExcelAndSetListData(title,fileName,objListToList(objList,col),col);
//    }


    /**
     * 将集合对象转换成字符串集合
     *
     * @param objList
     * @return
     */
    private static List<List<String>> objListToList(List<? extends Object> objList, String col) {

        List<List<String>> lists = new ArrayList<>();

        for (Object obj : objList) {
            List<String> list = objToList(obj, col);
            lists.add(list);
        }
        return lists;
    }


    /**
     * 将对象转换成List集合
     *
     * @param obj
     * @return
     */
    private static List<String> objToList(Object obj, String col) {
        ArrayList<String> list = new ArrayList<>();
        if (obj == null) {
            return null;
        }
        Map map = (HashMap<String, String>) obj;
        String[] split = col.split(",");
        for (int i = 0; i < split.length; i++) {
            Object o = map.get(split[i]);
            if (o != null) {
                list.add(o.toString());
            } else {
                list.add("");
            }
        }
        return list;
    }


}

