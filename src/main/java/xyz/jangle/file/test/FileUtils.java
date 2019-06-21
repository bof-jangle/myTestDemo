package xyz.jangle.file.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年6月14日 上午9:52:55 
* 类说明 
*/
public class FileUtils {
	
	/**
	 * 将文件输出到指定目录
	 * @param filename 文件名称（包括拓展名）
	 * @param file base64编码的文件
	 * @param path	输出目录的文件夹全路径
	 */
	public static void writeFileToPath(String filename,String file,String path) {
		File outPath = new File(path);
		if(!outPath.exists()) {
			System.out.println("不存在路径"+path+"，开始创建路径目录");
			outPath.mkdirs();
		}
		File outFile = new File(path+"/"+filename);
		FileOutputStream out;
		try {
			out = new FileOutputStream(outFile);
			byte[] decoded = Base64.getDecoder().decode(file);
			out.write(decoded);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
