package professional.fileAndStream;

import java.io.File;

public class FileOper {

	/**
	 * 删除文件夹下所有文件
	 * @param path
	 * @return
	 * @author 陈超
	 * @since Dec 26, 2012 10:23:58 AM
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	private static boolean cleanDir(String path) {
		//删除指定文件夹下所有文件
		//param path 文件夹完整绝对路径
       boolean flag = false;
       File file = new File(path);
       if (!file.exists()) {
    	   System.out.println("文件夹不存在");
    	   return flag;
       }
       if (!file.isDirectory()) {
    	   System.out.println("不是一个文件夹");
         return flag;
       }
       String[] tempList = file.list();
       if(null!=tempList&&tempList.length==0){
    	   return true;
       }
       File temp = null;
       for (int i = 0; i < tempList.length; i++) {
          if (path.endsWith(File.separator)) {
             temp = new File(path + tempList[i]);
          } else {
              temp = new File(path + File.separator + tempList[i]);
          }
          if (temp.isFile()) {
             flag = temp.delete();
             System.out.println("删除文件成功...tempList[i]"+tempList[i]
                       +"...File.separator + tempList[i]..."+File.separator + tempList[i]);
          }
       }
       return flag;
	}
	
	public static void main(String[] args) {
		String path = "fileFolder";
		System.out.println(cleanDir(path));
	}
}
