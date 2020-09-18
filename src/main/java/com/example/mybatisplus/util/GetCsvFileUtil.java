package com.example.mybatisplus.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 导出CSV文件工具
 *
 * @author LIFULIN
 * @className GetCsvFileUtil
 * @description TODO
 * @date 2020/6/5-15:35
 */
@Slf4j
public class GetCsvFileUtil {

    /**
     * 生成csv文件
     *
     * @param map 数据
     * @return csv文件名称
     */
    public static String createCsvFile(Map<String, Object> map) {
        //自定义文件保存位置
        String filePath = "C:\\";
        //文件名字
        String csvName = "csv";
        //文件生成时间
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String timeFileName = csvName + "_" + time + ".csv";
        //将坐标信息填入表格
        boolean b = csvInformation(map, timeFileName, filePath);
        if (b) {
            //生成文件
            log.info("生成文件[{}]成功!", timeFileName);
            return timeFileName;
        }
        log.info("报告生成失败！");
        return null;
    }


    /**
     * 写入数据
     *
     * @param map          数据
     * @param timeFileName 文件名称
     * @param filePath     自定义文件保存位置
     */
    private static boolean csvInformation(Map<String, Object> map, String timeFileName, String filePath) {
        //创建文件
        try (BufferedWriter csvWriter = fillVariable(timeFileName, filePath);) {

            List<Object> headList = (List<Object>) map.get("headList");
            // 写入文件头部
            writeHeadRow(headList, csvWriter);

            List<List<Object>> data = (List<List<Object>>) map.get("data");
            // 写入文件内容
            for (List<Object> list : data) {
                writeRow(list, csvWriter);
            }
            if (csvWriter != null) {
                //刷新流
                csvWriter.flush();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写入表格头
     *
     * @param headList 表格头数据
     */
    private static void writeHeadRow(List<Object> headList, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        writeRow(headList, csvWriter);
    }

    /**
     * 写入一行数据
     *
     * @param row 数据列表
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        int i = 1;
        for (Object data : row) {
            String rr;
            String rowStr = String.valueOf(data);
            String dou = ",";
            if (row.size() > i) {
                rr = rowStr + dou;
            } else {
                rr = rowStr;
            }
            csvWriter.write(rr);
            i++;
        }
        csvWriter.newLine();
    }

    /**
     * 创建csv文件
     *
     * @param timeFileName csv文件名
     * @param filePath     自定义文件保存位置
     */
    private static BufferedWriter fillVariable(String timeFileName, String filePath) {
        try {
            File csvFile = new File(filePath + timeFileName);
            File parent = csvFile.getParentFile();
            //文件不存在则创建文件
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            //自动创建此抽象路径名的新文件
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            BufferedWriter csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);

            log.info("创建csv文件");
            return csvWriter;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
