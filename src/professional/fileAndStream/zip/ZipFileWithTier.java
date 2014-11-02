package professional.fileAndStream.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipInputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.junit.Test;

public class ZipFileWithTier {

	private static final String ZIP_ENCODE = "GBK";

	@Test
	public void zipFile() {
		String zipPath = "C:/tmp/testZipF/testZip8.zip";
		String srcFiles = "C:/tmp/testZip";
		File file = new File(zipPath);
		if (file.exists())
			file.delete();
		zipFileWithTier(srcFiles, zipPath);
	}

	@Test
	public void unZipFile() throws Exception {
		String zipPath = "C:/tmp/testZipF/testZip8.zip";
		String unzipPath = "C:/tmp/testZipF/testZip8__/";
		try {
//			unzipFilesWithTier(readFileByte(zipPath), unzipPath
//					+ File.separator);
			unZipFile(unzipPath, zipPath);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*
	 * Compress the specify file that contains sub-folders and store them to a
	 * zipfile.
	 * 
	 * @param srcFiles: the file will be compressed
	 * 
	 * @param zipPath: the location which stores the zipfile.
	 */
	public static void zipFileWithTier(String srcFiles, String zipPath) {
		try {

			FileOutputStream zipFile = new FileOutputStream(zipPath);
			BufferedOutputStream buffer = new BufferedOutputStream(zipFile);
			ZipOutputStream out = new ZipOutputStream(buffer);
			out.setEncoding(ZIP_ENCODE);
			zipFiles(srcFiles, out, "");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Recursive the specify file also contains the folder which may don't
	 * include any file.
	 * 
	 * @param filePath: compress file
	 * 
	 * @param ZipOutputStream: the zipfile's outputStream.
	 * 
	 * @param prefix: the prefix indicates the parent folder name of the file
	 * that makes the tier relation.
	 */
	public static void zipFiles(String filePath, ZipOutputStream out,
			String prefix) throws IOException {
		File file = new File(filePath);
		if (file.isDirectory()) {
			if (file.listFiles().length == 0) {
				ZipEntry zipEntry = new ZipEntry(prefix + file.getName() + "/");
				out.putNextEntry(zipEntry);
				out.closeEntry();
			} else {
				prefix += file.getName() + File.separator;
				for (File f : file.listFiles())
					zipFiles(f.getAbsolutePath(), out, prefix);
			}
		} else {
			FileInputStream in = new FileInputStream(file);
			ZipEntry zipEntry = new ZipEntry(prefix + file.getName());
			out.putNextEntry(zipEntry);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.closeEntry();
			in.close();
		}

	}

	/*
	 * Unzip the file also contains the folder which the listFiles's length is
	 * 0.
	 * 
	 * @param bytes: the content of the zipfile by byte array. *
	 * 
	 * @param prefix: the prefix is the root of the store path.
	 * 
	 * @IOExcetion: the ioexception during unzipFiles.
	 */
//	public static void unzipFilesWithTier(byte[] bytes, String prefix)
//			throws IOException {
//
//		InputStream bais = new ByteArrayInputStream(bytes);
//		ZipInputStream zin = new ZipInputStream(bais);
//		ZipEntry ze;
//		while ((ze =  new ZipEntry(zin.getNextEntry())) != null) {
//			if (ze.isDirectory()) {
//				File file = new File(prefix + ze.getName());
//				if (!file.exists())
//					file.mkdirs();
//				continue;
//			}
//			File file = new File(prefix + ze.getName());
//			if (!file.getParentFile().exists())
//				file.getParentFile().mkdirs();
//			ByteArrayOutputStream toScan = new ByteArrayOutputStream();
//			byte[] buf = new byte[1024];
//			int len;
//			while ((len = zin.read(buf)) > 0) {
//				toScan.write(buf, 0, len);
//			}
//			byte[] fileOut = toScan.toByteArray();
//			toScan.close();
//			writeByteFile(fileOut, new File(prefix + ze.getName()));
//		}
//		zin.close();
//		bais.close();
//	}
	public static void unZipFile(String ZIP_DIR,String ZIP_FILENAME) throws Exception {
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
	public static byte[] readFileByte(String filename) throws IOException {

		if (filename == null || filename.equals("")) {
			throw new NullPointerException("File is not exist!");
		}
		File file = new File(filename);
		long len = file.length();
		byte[] bytes = new byte[(int) len];

		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(file));
		int r = bufferedInputStream.read(bytes);
		if (r != len)
			throw new IOException("Read file failure!");
		bufferedInputStream.close();

		return bytes;

	}

	public static String writeByteFile(byte[] bytes, File file) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "success";
	}

}