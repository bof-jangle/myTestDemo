package xyz.jangle.file.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author huhj
 * @email  jangle@jangle.xyz
 * @time   2018年8月15日 上午9:48:17
 */
public class Base64String2byte {

	public static void main(String[] args) {
		
		try {
			testBase64Demo("中华2732@23423");
//			testReadFileToString();
//			testStringToByte();
		} catch (Exception e) {
			System.out.println("异常："+e);
			e.printStackTrace();
		}

	}
	
	// Base64编码
	public static void testBase64Demo(String str) throws Exception{
		 //Original byte[]
        byte[] bytes = str.getBytes("UTF-8");
         
        //Base64 Encoded
        String encoded = Base64.getEncoder().encodeToString(bytes);
        System.out.println("encoded: "+encoded);
        //Base64 Decoded
        byte[] decoded = Base64.getDecoder().decode(encoded);
        System.out.println("decoded: "+decoded);
        for(byte b:decoded){
        	System.out.print(b);
        }
        System.out.println();
        //Verify original content
        System.out.println( new String(decoded,"UTF-8") );
	}
	
	//(二进制)字符串与字节数组进行互转时，采用ISO_8859_1字符编码(单字节)。 UTF-8是多字节编码，互转时会出现内容与长度不一致的情况。
	public static void testReadFileToString() throws Exception{
		int read;		//缓冲区内容大小
		File f = new File("D:/d/test.txt");
		File outFile = new File("D:/d/testout.txt");
		File outFile2 = new File("D:/d/testout2ok.txt");
		InputStream is = new FileInputStream(f);
		FileOutputStream out = new FileOutputStream(outFile);	//转字符串后，再转回来，进行输出。字符串用于存库。输出用于读取、下载。
		FileOutputStream out2 = new FileOutputStream(outFile2);	//不编码输出
		String s;					//字符串
		byte[] buffer = new byte[is.available()];	//缓冲区
		if((read = is.read(buffer))==-1){
			is.close();
			out.close();
			out2.close();
			return;
		}
		is.close();
		testBase64(buffer);
		out2.write(buffer, 0, read);
		out2.flush();
		out2.close();
		s = new String(buffer);
		String ss = new String(buffer, "ISO_8859_1");		//用于保存在数据库中
		System.out.println("ss:"+ss);
		System.out.println("s："+s);
		out.write(ss.getBytes("ISO_8859_1"),0,read);
		out.flush();
		out.close();
	}
	
	//中文字符串转字节数组，再转回时，需要用UTF-8等多字符编码，因为单字符编码的ISO_8859_1的编码表中不包含中文
	public static void testStringToByte() throws Exception{
		byte[] src = "中华".getBytes("ISO_8859_1");
		System.out.println(new String(src,"ISO_8859_1"));
		byte[] src2 = "中华".getBytes("UTF-8");
		System.out.println(new String(src2,"UTF-8"));
	}
	
	public static void testBase64(byte[] bytes){
		//Base64 Encoded
        String encoded = Base64.getEncoder().encodeToString(bytes);
        System.out.println("encoded: "+encoded);
        //Base64 Decoded
        byte[] decoded = Base64.getDecoder().decode(encoded);
        System.out.println("decoded: "+decoded);
        //Verify original content
        System.out.println( new String(decoded) );
	}

}
