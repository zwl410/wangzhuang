package com.zhang.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


/**
 * 下载文件
 * 创建人：WW 创建时间：2017年11月24日
 */
public class FileDownload {

    /**
     * @param response
     * @param filePath //文件完整路径(包括文件名和扩展名)
     * @param fileName //下载后看到的文件名
     * @return 文件名
     */
    public static void fileDownload(final HttpServletRequest request, final HttpServletResponse response, String filePath, String fileName) throws Exception {
        byte[] data = FileUtil.toByteArray2(filePath);
        // 中文文件名支持
        String encodedFileName = null;
        // 替换空格，否则firefox下有空格文件名会被截断,其他浏览器会将空格替换成+号
        encodedFileName = fileName.trim().replaceAll(" ", "_");

        String agent = request.getHeader("User-Agent");
        boolean isMSIE = ((agent != null) && (agent.toUpperCase().indexOf(
                "MSIE") != -1));
        if (isMSIE) {
            encodedFileName = URLEncoder.encode(encodedFileName, "UTF-8");
        } else {
            encodedFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
        response.flushBuffer();
    }

    /**
     * 流形式文件 下载
     *
     * @param fileName
     * @param tmpFileName
     * @throws IOException
     */
    public static void fileStreamDownload(HttpServletRequest request, HttpServletResponse response, String fileName, String tmpFileName) throws IOException {
        if (StringUtils.isEmpty(fileName) && StringUtils.isEmpty(tmpFileName)) {
            return;
        }
        File file = new File(tmpFileName);
        if (!file.exists()) {
            return;
        }
        String postfix = tmpFileName.substring(tmpFileName.lastIndexOf("."));
        response.reset();
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "utf-8") + postfix + "\"");
        } else {
            fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1"); // 下载的文件名显示编码处理
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + postfix + "\"");
        }
        response.setContentType("application/x-msdownload;charset=UTF-8");
        FileInputStream fis = new FileInputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[2048];
        int readlength = 0;
        while ((readlength = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, readlength);
        }
        try {
            fis.close();
        } catch (IOException e) {
        }
        try {
            bos.flush();
            bos.close();
        } catch (IOException e) {
        }
    }
}
