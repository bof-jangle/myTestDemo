package xyz.jangle.map.test;

import java.util.HashMap;
import java.util.Map;

/** 
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年7月25日 下午4:34:52 
* 类说明 
*/
public class MapTest {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", "111");
		map.put("2", "222");
		map.put("3", "333");
		String toString = mapToString(map);
		System.out.println(toString);

	}
	
	/**
	 * 将Map内容转换成字符串，用于日志打印输出。
	 * @param map
	 * @return
	 */
	public static String mapToString(Map<String, Object> map) {
		if(map == null) {
			return "{}";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{ ");
		for(String s : map.keySet()) {
			sb.append("\""+s+"\":\""+map.get(s)+"\",");
		}
		sb.replace(sb.length()-1, sb.length(), "}");
		return sb.toString();
	}

}
