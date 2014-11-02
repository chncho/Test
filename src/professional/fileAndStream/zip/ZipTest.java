package professional.fileAndStream.zip;

import java.util.zip.*;
import java.io.*;

public class ZipTest {
     public static void main(String[] args) {
    	 String[] srcFiles = new String[]{
    			 "C:/tmp/testZip/aaa_111.txt",
    			 "C:/tmp/testZip/aaa_111_1.txt",
    			 "C:/tmp/testZip/aaa_111_2.txt",
    			 "C:/tmp/testZip/aaa_111_3.txt",
    			 "C:/tmp/testZip/aaa_111_4.txt",
    			 "C:/tmp/testZip/aaa_111_5.txt"
    	 		}; 
    	 String desFile = "C:/tmp/testZip/zipfile/zip3.zip";

    	 ZipCompressor compressor = new ZipCompressor();
           boolean isSuccessful= compressor.zipCompress(srcFiles, desFile);
           if (isSuccessful) {
                System.out.println("文件压缩成功。");
           } else {
                System.out.println("文件压缩失败。");
           }
     }
}

class ZipCompressor {
     public ZipCompressor() {}

     /**@param srcFiles 需压缩的文件路径及文件名
     * @param desFile 保存的文件名及路径
     * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">@return</a>  如果压缩成功返回true
     */
     public boolean zipCompress(String[] srcFiles, String desFile) {
          boolean isSuccessful = false;

          String[] fileNames = new String[srcFiles.length-1];
          for (int i = 0; i < srcFiles.length-1; i++) {
               fileNames[i] = parse(srcFiles[i]);
          }

          try { 
        	  	File f = new File(desFile);
        	  	if(!f.getParentFile().exists()){
        	  		f.getParentFile().mkdirs();
        	  	}
        	  
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
                    ZipOutputStream zos = new ZipOutputStream(bos);
                    String entryName = null;
                    entryName = fileNames[0];
                    
                    for (int i = 0; i < fileNames.length; i++) {
                         entryName = fileNames[i];

                         // 创建Zip条目
                         ZipEntry entry = new ZipEntry(entryName);
                         zos.putNextEntry(entry);

                         BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFiles[i]));

                         byte[] b = new byte[1024];

                         int readLen = 0;
                         while ((readLen=bis.read(b, 0, 1024)) != -1) {
                              zos.write(b, 0, readLen);
                         }
                         bis.close();
                         zos.closeEntry();
                    }

                    zos.flush();
                    zos.close();
                    isSuccessful = true;
          } catch (IOException e) {
          }

          return isSuccessful;
     }


     // 解析文件名
     private String parse(String srcFile) {
          int location = srcFile.lastIndexOf("/");
          String fileName = srcFile.substring(location + 1);
          return fileName;
     }
}