package com.it.common.uitl.files;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileUtils {

    public static boolean createFileDirectory(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录  
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }

    }

    private static String encoding = "UTF-8";

    /**
     * 读取文件 全部内容
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) throws Exception {
        File fileObj = new File(filePath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileObj), encoding);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder context = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line != null) {
                context.append(line);
            } else {
                break;
            }
        }
        br.close();
        reader.close();
        return context.toString();
    }

    /**
     * 写入文件
     *
     * @param filePath
     * @param context
     * @throws Exception
     */
    public static void writFile(String filePath, String context) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
        osw.write(context);
        osw.flush();
        osw.close();
        fos.close();
    }


    /**
     * 追加写入文件内容
     * @param filePath
     * @param context
     * @throws Exception
     */
    public static void addWritFile(String filePath, String context) throws Exception {
        File f=new File(filePath);
        FileWriter fw = new FileWriter(f, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(context+"\r\n");
        pw.flush();
        try {
            pw.close();
            fw.close();
        } catch (IOException e) { }
    }

}
