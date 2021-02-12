package xyz.jangle.javabase.n2.b1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 2.1.4、文本的读入与输出
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2021年2月12日 下午
 * 
 */
public class M4 {

	public static void main(String[] args) throws IOException {
		// 1、写入文本到文件中
		PrintWriter writer = new PrintWriter(Files.newOutputStream(Paths.get(M3.pathFileStr)));
		writer.println("zhonghuarenm国家");
		writer.flush();
		writer.close();
		// 2、像文件中添加文本内容
		PrintWriter writer2 = new PrintWriter(new FileOutputStream(M3.testPathFile, true)
				,true,StandardCharsets.UTF_8);
		writer2.write(" gongheg中华");
		writer2.close();
		
		// 3、读入文件中的文本内容
		try(InputStreamReader reader = new InputStreamReader(Files.newInputStream(Paths.get(M3.pathFileStr)),StandardCharsets.UTF_8);
				BufferedReader bufferedReader = new BufferedReader(reader);){
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
		}
		// 4、读入文件中的文本内容
		try(var scanner = new Scanner(Files.newInputStream(Paths.get(M3.pathFileStr)))){
			while (scanner.hasNext()) {
				String nextStr = scanner.next();
				System.out.println(nextStr);
			}
		}

	}

}
