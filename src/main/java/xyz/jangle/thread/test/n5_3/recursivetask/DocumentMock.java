package xyz.jangle.thread.test.n5_3.recursivetask;

import java.util.Random;

/**
 * 	模拟文档的工厂
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年8月27日 下午6:14:07
 * 
 */
public class DocumentMock {

	private String words[] = { "the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class",
			"main" };

	/**
	 * 	生产一个N行M列的单词二维数组（模拟文档）
	 * @param numLines 行数
	 * @param numWords 单词数
	 * @param word     待查找的单词
	 * @return
	 */
	public String[][] generateDocument(int numLines, int numWords, String word) {
		int counter = 0;
		String[][] document = new String[numLines][numWords];
		Random random = new Random();
		for (int i = 0; i < numLines; i++) {
			for (int j = 0; j < numWords; j++) {
				int index = random.nextInt(words.length);
				document[i][j] = words[index];
				if (document[i][j].equals(word)) {
					counter++;
				}
			}
		}
		System.out.println("DocumentMock:匹配的单词数量："+counter);
		return document;

	}

}
