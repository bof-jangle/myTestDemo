package xyz.jangle.read.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FileReaderTest {

	public static void main(String[] args) {
		String s = readToString("C:/Users/dell/Documents/testTableToExcel.ktr");
//		System.out.println(s);
//		testVo vo = new testVo();
//		vo.setId(1);
//		vo.setContext(s);
//		System.out.println(vo.getContext());
		
		File fd = new File("D:/d");
		File f = new File("D:/d"+"/test.ktr");
		try {
			fd.mkdirs();//创建多级目录       mkdir创建单级目录
			f.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(f);	//创建文件输出流
			fileOutputStream.write(s.getBytes());							//将内存数组的内容写入到实体文件中
			fileOutputStream.flush();										//将剩余内容输出（保证没有多余字节未输出。）
			fileOutputStream.close();	//XXX 一般我们在finally块中关闭流。
			System.out.println("执行成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 将fileName的文件内容读取，并转换为String
	 * @param fileName 文件全路径+文件名
	 * @return 文件内容
	 */
	public static String readToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  //内存数组
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  								//读入内存
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            System.out.println("未找到指定文件："+fileName);
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  		//结果返回
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    } 
	
	public static class testVo{
		
		private int id ;
		
		private String context;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getContext() {
			return context;
		}
		public void setContext(String context) {
			this.context = context;
		}
		
		
	}

}
