package com.zhang.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZip {
    /**
     * 完成的结果文件--输出的压缩文件
     */
    File targetFile;

    public FileZip() {}

    public FileZip(File target) {
        targetFile = target;
        if (targetFile.exists())
            targetFile.delete();
    }

    /**
     * 压缩文件
     *
     * @param srcfile
     */
    public void zipFiles(File srcfile) {

        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(targetFile));

            if(srcfile.isFile()){
                zipFile(srcfile, out, "");
            } else{
                File[] list = srcfile.listFiles();
                for (int i = 0; i < list.length; i++) {
                    compress(list[i], out, "");
                }
            }

            System.out.println("压缩完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件夹里的文件
     * 起初不知道是文件还是文件夹--- 统一调用该方法
     * @param file
     * @param out
     * @param basedir
     */
    private void compress(File file, ZipOutputStream out, String basedir) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            this.zipDirectory(file, out, basedir);
        } else {
            this.zipFile(file, out, basedir);
        }
    }

    /**
     * 压缩单个文件
     *
     * @param srcfile
     */
    public void zipFile(File srcfile, ZipOutputStream out, String basedir) {
        if (!srcfile.exists())
            return;

        byte[] buf = new byte[1024];
        FileInputStream in = null;

        try {
            int len;
            in = new FileInputStream(srcfile);
            out.putNextEntry(new ZipEntry(basedir + srcfile.getName()));

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.closeEntry();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件夹
     * @param dir
     * @param out
     * @param basedir
     */
    public void zipDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists())
            return;

        File[] files = dir.listFiles();
        if(files.length>0){
            for (int i = 0; i < files.length; i++) {
            /* 递归 */
                compress(files[i], out, basedir + dir.getName() + "/");
            }
        }else {
            try {
                ZipEntry zipEntry = new ZipEntry(basedir + dir.getName() + "/");
                out.putNextEntry(zipEntry);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    /**
     * @param inputFileName 你要压缩的文件夹(整个完整路径)
     * @param zipFileName   压缩后的文件(整个完整路径)
     */
    public static void zip(String inputFileName, String zipFileName) throws Exception {
        zip(zipFileName, new File(inputFileName));
    }

    private static void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                zipFileName));
        zip(out, inputFile, "");
        out.flush();
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

    public static void main(String[] temp) {
        long start =System.currentTimeMillis(); //获取开始时间
        int i = 0;
        try {
            if(i==0){
                File f = new File("D:\\project\\ruiqinbus\\branches\\ruiqinbus_v2.1\\out\\ruiqinbus\\resources\\zipfile\\2018-06-28\\2018-06-28贷乾保200万12个月");
                new FileZip(new File( "D:\\project\\ruiqinbus\\branches\\ruiqinbus_v2.1\\out\\ruiqinbus\\resources\\zipfile\\2018-06-28",f.getName()+".zip")).zipFiles(f);
            }else{
                FileZip.zip("C:\\Users\\ruiqin\\Desktop\\photo", "C:\\Users\\ruiqin\\Desktop\\photo.zip");
            }
            long end =System.currentTimeMillis(); //获取开始时间
            System.out.println("程序运行时间： "+(end-start)+"ms");
//            zip("E:\\ftl", "E:\\test.zip");//你要压缩的文件夹      和  压缩后的文件
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
