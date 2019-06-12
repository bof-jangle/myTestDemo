package xyz.jangle.dom4j.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/** 
 * 使用dom4j解析DOM结构的XML数据
* @author jangle E-mail: jangle@jangle.xyz
* @version 2019年6月12日 下午2:32:29 
* 类说明 
*/
public class SXFileTest {

	public static void main(String[] args) {
		String s = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?>\r\n" + 
				"<list>\r\n" + 
				"    <FSGWEntitys>\r\n" + 
				"        <FSGWEntity>\r\n" + 
				"            <bt>市人力社保局党意见建议的函</bt>\r\n" + 
				"            <createDate>2019-01-03</createDate>\r\n" + 
				"            <qfrq>2019-01-03   09:19:49</qfrq>\r\n" + 
				"            <fwdw>市人力社保局</fwdw>\r\n" + 
				"            <wh></wh>\r\n" + 
				"            <jzcd>普通</jzcd>\r\n" + 
				"            <wjtj>1776970</wjtj>\r\n" + 
				"        </FSGWEntity>\r\n" + 
				"        <FSGWEntity>\r\n" + 
				"            <bt>关于启用新章的通知</bt>\r\n" + 
				"            <createDate>2019-01-08</createDate>\r\n" + 
				"            <qfrq>2019-01-08   15:17:10</qfrq>\r\n" + 
				"            <fwdw>文化广电旅游局</fwdw>\r\n" + 
				"            <wh></wh>\r\n" + 
				"            <jzcd>普通</jzcd>\r\n" + 
				"            <wjtj>1786079</wjtj>\r\n" + 
				"        </FSGWEntity>\r\n" + 
				"        <FSGWEntity>\r\n" + 
				"            <bt>xxx关于启用新印章的通知</bt>\r\n" + 
				"            <createDate>2019-01-02</createDate>\r\n" + 
				"            <qfrq>2019-01-02   11:17:19</qfrq>\r\n" + 
				"            <fwdw>市金融工作办公室</fwdw>\r\n" + 
				"            <wh></wh>\r\n" + 
				"            <jzcd>普通</jzcd>\r\n" + 
				"            <wjtj>1775332</wjtj>\r\n" + 
				"        </FSGWEntity>\r\n" + 
				"        <FSGWEntity>\r\n" + 
				"            <bt>关于启用新印章及办公地址调整的函</bt>\r\n" + 
				"            <createDate>2019-01-02</createDate>\r\n" + 
				"            <qfrq>2019-01-02   16:14:35</qfrq>\r\n" + 
				"            <fwdw>市编委办</fwdw>\r\n" + 
				"            <wh></wh>\r\n" + 
				"            <jzcd>普通</jzcd>\r\n" + 
				"            <wjtj>1776180</wjtj>\r\n" + 
				"        </FSGWEntity>\r\n" + 
				"        <FSGWEntity>\r\n" + 
				"            <bt>xxxx生活会意见建议的函</bt>\r\n" + 
				"            <createDate>2019-01-09</createDate>\r\n" + 
				"            <qfrq>2019-01-09   16:36:35</qfrq>\r\n" + 
				"            <fwdw>红十字会</fwdw>\r\n" + 
				"            <wh></wh>\r\n" + 
				"            <jzcd>普通</jzcd>\r\n" + 
				"            <wjtj>1789233</wjtj>\r\n" + 
				"        </FSGWEntity>\r\n" + 
				"    </FSGWEntitys>\r\n" + 
				"    <msgid>success</msgid>\r\n" + 
				"</list>";
		
		List<Map<String, Object>> dsgwList = getDsgw(s);
		for(Map<String, Object> gw:dsgwList) {
			for(String key :gw.keySet()) {
				System.out.println(key+":"+gw.get(key));
			}
		}

	}
	
	/**
	 * 将xml格式的数据，转化为map（map可以改造成模型对象）结果类型的List
	 * @param s	字符串格式的XML数据内容
	 * @return	List形式的结果集  （Map可以用自定义Model对象代替）
	 */
	public static List<Map<String,Object>> getDsgw(String s) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			//创建xml文档对象
			Document document = DocumentHelper.parseText(s);
//			String asXML = document.asXML();
//			System.out.println(asXML);
			Element rootElement = document.getRootElement();
			//遍历节点
			Iterator<?> elementIterator = rootElement.elementIterator();
			while(elementIterator.hasNext()){
				Element element = (Element)elementIterator.next();
//				System.out.println(element.getName());
//				System.out.println(element.getText());
				if(element.getName() != null && element.getName().equals("FSGWEntitys")) {
					Iterator<?> FSGWEntitys = element.elementIterator();
					while(FSGWEntitys.hasNext()) {
						Element FSGWEntitysNext = (Element) FSGWEntitys.next();
//						System.out.println("  name："+FSGWEntitysNext.getName());
//						System.out.println("  text："+FSGWEntitysNext.getText());
						Iterator<?> FSGWEntity = FSGWEntitysNext.elementIterator();
						if(FSGWEntitysNext.getName() != null && FSGWEntitysNext.getName().equals("FSGWEntity")) {
							map = new HashMap<String, Object>();
							while(FSGWEntity.hasNext()) {
								Element FSGWEntityNext = (Element) FSGWEntity.next();
//								System.out.println(FSGWEntityNext.getName()+"："+FSGWEntityNext.getText());
								// 此处是属性的映射等逻辑。
								map.put(FSGWEntityNext.getName(), FSGWEntityNext.getText());
								list.add(map);
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
