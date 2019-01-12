package com.it.common.uitl.files;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class FileCopyUtil {

//    public static void main(String[] args) throws IOException {
//
//    	   String url1="C:\\zip\\1\\01-33B27F224F874A8190674925055E50A1";
//    	    //目标文件夹
//    	      String url2="C:\\Users\\Administrator\\nets\\data\\nets\\2016061615\\7\\1\\1ia88tdjnh3m9g52m3m1mqb37l\\";
//        //创建目标文件夹
//    	      copyUtil(  url1,   url2);
//    }
  public static void copyUtil(String url1, String url2) throws IOException {
	  
	   //获取源文件夹当前下的文件或目录
      File[] file=(new File(url1)).listFiles();
      for (int i = 0; i < file.length; i++) {
       if(file[i].isFile()){
           //复制文件
           copyFile(file[i],new File(url2+file[i].getName()));
       }
       if(file[i].isDirectory()){
           //复制目录
           String sorceDir=url1+File.separator+file[i].getName();
           String targetDir=url2+File.separator+file[i].getName();
           copyDirectiory(sorceDir, targetDir);
       }
   }
  }

  public static void copyFile(File sourcefile,File targetFile) throws IOException{
        
        //新建文件输入流并对它进行缓冲
        FileInputStream input=new FileInputStream(sourcefile);
        BufferedInputStream inbuff=new BufferedInputStream(input);
        
        //新建文件输出流并对它进行缓冲
        FileOutputStream out=new FileOutputStream(targetFile);
        BufferedOutputStream outbuff=new BufferedOutputStream(out);
        
        //缓冲数组
        byte[] b=new byte[1024*5];
        int len=0;
        while((len=inbuff.read(b))!=-1){
            outbuff.write(b, 0, len);
        }
        
        //刷新此缓冲的输出流
        outbuff.flush();
        
        //关闭流
        inbuff.close();
        outbuff.close();
        out.close();
        input.close();
        
        
    }
    
    public static void copyDirectiory(String sourceDir,String targetDir) throws IOException{
        
        //新建目标目录
        
        (new File(targetDir)).mkdirs();
        
        //获取源文件夹当下的文件或目录
        File[] file=(new File(sourceDir)).listFiles();
        
        for (int i = 0; i < file.length; i++) {
            if(file[i].isFile()){
                //源文件
                File sourceFile=file[i];
                    //目标文件
                File targetFile=new File(new File(targetDir).getAbsolutePath()+File.separator+file[i].getName());
                
                copyFile(sourceFile, targetFile);
            
            }
            
            
            if(file[i].isDirectory()){
                //准备复制的源文件夹
                String dir1=sourceDir+file[i].getName();
                //准备复制的目标文件夹
                String dir2=targetDir+"/"+file[i].getName();
                
                copyDirectiory(dir1, dir2);
            }
        }
        
    }
 }
