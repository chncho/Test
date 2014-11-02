package professional.fileAndStream.zip;
import java.util.zip.*;
import java.io.*;

public class UnzipTest {
     public static void main(String[] args) {
    	 String destZip = "C:/tmp/testZip/zipfile/zip2.zip";
    	 String targetFolder = "C:/tmp/testZip/zipfile/z3";
    	 
               Unzip unzip = new Unzip();
               if (unzip.unzip(destZip,targetFolder)) {
                    System.out.println("文件解压成功。");
               } else {
                    System.out.println("文件解压失败。");
               }
     }
}

class Unzip {
     public Unzip() {}

     /*
     * @param srcZipFile 需解压的文件名
     * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">@return</a>  如果解压成功返回true
     */
     public boolean unzip(String srcZipFile,String targetFolder) {
          boolean isSuccessful = true;
          try {
               BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcZipFile));
               ZipInputStream zis = new ZipInputStream(bis);

               BufferedOutputStream bos = null;

               //byte[] b = new byte[1024];
               ZipEntry entry = null;
               while ((entry=zis.getNextEntry()) != null) {
                    String entryName = entry.getName();
                    File f = new File(targetFolder +"/"+ entryName);
                    if(!f.getParentFile().exists()){
            	  		f.getParentFile().mkdirs();
            	  	}
                    
                    bos = new BufferedOutputStream(new FileOutputStream(f));
                    int b = 0;
                    while ((b = zis.read()) != -1) {
                         bos.write(b);
                    }
                    bos.flush();
                    bos.close();
               }
               zis.close();
          } catch (IOException e) {
               isSuccessful = false;
          }
          return isSuccessful;
     }
}