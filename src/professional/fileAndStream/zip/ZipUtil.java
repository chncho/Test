package professional.fileAndStream.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

//这里注意不能采用java下面的类包，不然没有设置编码的方法

import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ZipUtil {

//	public static final String  = ""; // 需要解压缩的文件名
//	public static final String ZIP_DIR = ""; // 需要压缩的文件夹
//	public static final String UN_ZIP_DIR = ""; // 要解压的文件目录
	public static final int BUFFER = 1024; // 缓存大小

	public static void main(String[] args) {
		try {
			zipFile("C:/tmp/testZip", "C:/tmp/testZipF/testZip7.zip");
			
//			upZipFile("C:/tmp5/t", "C:/tmp5/testZip3.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void zipFile(String baseDir, String fileName)
			throws Exception {
		List<File> fileList = getSubFiles(new File(baseDir));
		System.out.println(fileList);

		File f0 = new File(fileName);
		if (!f0.getParentFile().exists()) {
			f0.getParentFile().mkdirs();
		}

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(f0));
		ZipEntry ze = null;
		byte[] buf = new byte[BUFFER];
		int readLen = 0;
		Iterator<File> it = fileList.iterator();
		while (it.hasNext()) {
			File f = it.next();
			ze = new ZipEntry(getAbsFileName(baseDir, f));
			ze.setSize(f.length());

			ze.setTime(f.lastModified());
			zos.putNextEntry(ze);
			System.out.println("zipFile()..."+f + "isfile..." + f.isFile() + ",ifdir..."
					+ f.isDirectory());
			if(f.isDirectory()){
				f.mkdirs();//写文件夹
				continue;
			};
			InputStream is = new BufferedInputStream(new FileInputStream(f));
			while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
				zos.write(buf, 0, readLen);
			}
			is.close();
		}
		zos.setEncoding("gbk");
		zos.close();
	}

	private static String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null)
				break;
			if (real.equals(base))
				break;
			else
			{
				ret = real.getName() + "/" + ret;
			}
		}
		return ret;
	}

	private static List<File> getSubFiles(File baseDir) {
		List<File> ret = new ArrayList();
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			File f = tmp[i];
			System.out.println("getSubFiles()..."+f + "isfile..." + f.isFile() + ",ifdir..."
					+ f.isDirectory());
			if (f.isFile()) {
				ret.add(f);
			}
			if (f.isDirectory()) {
				//ret.add(f);
				ret.addAll(getSubFiles(f));
			}
		}
		return ret;
	}

	public static void upZipFile(String ZIP_DIR,String ZIP_FILENAME) throws Exception {
		File f0 = new File(ZIP_DIR);
		if(!f0.exists()){
			f0.mkdirs();
		}
		ZipFile zfile = new ZipFile(ZIP_FILENAME);
		Enumeration zList = zfile.getEntries();
		ZipEntry ze = null;
		byte[] buf = new byte[1024];
		while (zList.hasMoreElements()) {
			ze = (ZipEntry) zList.nextElement();
			if (ze.isDirectory()) {
				File f = new File(ZIP_DIR + ze.getName());
				f.mkdir();
				continue;
			}
			System.out.println(ze.getName());
			File f_ = new File(f0,ze.getName());
			if(!f_.getParentFile().exists()){
				f_.getParentFile().mkdirs();
			}
			
			OutputStream os = new BufferedOutputStream(new FileOutputStream(
					f_));
			InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
			int readLen = 0;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
		}
		zfile.close();
	}

	public static File getRealFileName(String baseDir, String absFileName) {
		String[] dirs = absFileName.split("/");
		File ret = new File(baseDir);
		if (dirs.length > 1) {
			for (int i = 0; i < dirs.length - 1; i++) {
				ret = new File(ret, dirs[i]);
			}
			if (!ret.exists())
				ret.mkdirs();
			ret = new File(ret, dirs[dirs.length - 1]);
			return ret;
		}
		return ret;
	}

	public static void deleteDirFile(String path) {
		File file = new File(path);
		if (file.isDirectory()) { // 如果是目录，先递归删除
			String[] list = file.list();
			for (int i = 0; i < list.length; i++) {
				deleteDirFile(path + "\\" + list); // 先删除目录下的文件
			}
		}
		file.delete();
	}

	public static String newFolder(String dir) {
		java.io.File myFilePath = new java.io.File(dir);
		if (myFilePath.isDirectory()) {
		} else {
			myFilePath.mkdirs();
		}
		return dir;
	}

	public static String getFileNames(String path) {

		File file = new File(path); // get file list where the path has
		File[] array = file.listFiles(); // 获得文件列表
		String pdfNames = "";

		for (int i = 0; i < array.length; i++) {
			File f = array[i];
			if (f.isFile()) {
				if (f.getName().endsWith(".pdf")) { // 获得pdf文件名称
					pdfNames += f.getName().substring(0,
							f.getName().length() - 4)
							+ ",";
				}
			}
		}
		if (pdfNames.length() > 0) {
			pdfNames.substring(0, pdfNames.length() - 1);
		}
		return pdfNames;
	}

	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("copy file error!");
			e.printStackTrace();
		}
	}

	public static boolean fileExist(String fileNames, String pdfName) {
		boolean flag = false;
		if (!"".equals(fileNames)) {
			String[] nameArr = fileNames.split(",");
			for (int i = 0; i < nameArr.length; i++) {
				if (pdfName.equals(nameArr)) { // 如果文件名相同则执行拷贝操作(拷贝到zip目录准备打包)
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	public static void deleteFileAndDir(String path) {
		File file = new File(path);
		File[] array = file.listFiles();
		for (int i = 0; i < array.length; i++) {
			File f = array[i];
			if (f.isFile()) {
				f.delete();
			} else if (f.isDirectory()) {
				deleteDirFile(f.getPath());
			}
		}
	}

}