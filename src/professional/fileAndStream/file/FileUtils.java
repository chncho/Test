package professional.fileAndStream.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import org.apache.log4j.chainsaw.Main;

/**
 * 文件操作代码
 * 
 */
public class FileUtils {
	
	public static void main(String[] args) throws IOException {
		System.out.println("begin");
		copyAllFile2OneFlatFolder("E:\\WORK_LEFU\\合规&检查\\续展相关\\金控\\第二次导入-合集-新\\回执\\大POS\\ALL\\source",
				"E:\\WORK_LEFU\\合规&检查\\续展相关\\金控\\第二次导入-合集-新\\回执\\大POS\\ALL\\target");
		System.out.println("end");
	}
	/**
	 * 将所有文件copy到一个文件夹，没有目录结构
	 * @param path
	 * @throws IOException 
	 */
	public static void copyAllFile2OneFlatFolder(String source , String target) throws IOException {
		File fileTarget = new File (target);
		if(fileTarget.exists()){
			if(fileTarget.isDirectory()){
				//TODO clear it 
			}else{
				fileTarget.delete();
			}
		}
		fileTarget.mkdirs();
		exeCopyAllFile2OneFlatFolder(source, fileTarget,"");
    }
	private static void exeCopyAllFile2OneFlatFolder(String source , File targetFile,String fileNameAppender) throws IOException {
        File file = new File(source);
        if (!file.exists()) {
        	System.out.println(source+" 不存在");
        	return;
        }
        
        File[] files = file.listFiles();
        if (files.length == 0) {
            System.out.println("文件夹是空的!");
            return;
        } else {
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    exeCopyAllFile2OneFlatFolder(file2.getAbsolutePath(),targetFile,fileNameAppender+"_"+file2.getName());
                } else {
                    System.out.println("文件:" + file2.getAbsolutePath());
                    copyFile(file2,targetFile,fileNameAppender+"_"+file2.getName());
                }
            }
        }
    }
    private static void copyFile(File source, File targetDir, String targetFileName) throws IOException {
		File targetFile = new File( targetDir.getAbsolutePath()+File.separator+targetFileName);
		nioTransferCopy(source, targetFile);
	}
    private static void nioTransferCopy(File source, File target) throws IOException {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if(null!=inStream){
        		inStream.close();
        	}
        	if(null!=in){
        		in.close();
        	}
        	if(null!=outStream){
        		outStream.close();
        	}
        	if(null!=out){
        		out.close();
        	}
        }
    }
	/**
     * 将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    /**
     * 读取文本文件内容
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileUtils.readToBuffer(sb, filePath);
        return sb.toString();
    }
	public static String returnAFileName(String name) {
		return name.replaceAll("[ \\\\\\/\\:\\*\\?\\\"\\<\\>\\|]", "");
	}
}
