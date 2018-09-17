package xyz.jangle.file.test;

import java.io.File;

import org.apache.log4j.Logger;

public class FileTest {
	private static Logger logger = Logger.getLogger(FileTest.class);

	public static void main(String[] args) {
		String path = "d:/";
		File file = new File(path);
		File[] tempArray = file.listFiles();
		System.out.println("该目录下对象个数：" + tempArray.length);
		for (int i = 0; i < tempArray.length; i++) {
			if (tempArray[i].isFile()) {
				System.out.println("文     件：" + tempArray[i]);
				System.out.println("扩展名："+ getExtName(tempArray[i]));
			}
			if (tempArray[i].isDirectory()) {
//				System.out.println("文件夹：" + tempArray[i]);
				logger.debug("文件夹：" + tempArray[i]);
			}
		}
		
	}
	/**
	 * 获取文件扩展名
	 * @param file 文件对象
	 * @return 返回扩展名，若没有扩展名则返回空字符串
	 */
	public static String getExtName(File file){
		String name = file.getName();
		return name.lastIndexOf(".") == -1 ? "":name.substring(name.lastIndexOf(".")+1);
		
	}

}
