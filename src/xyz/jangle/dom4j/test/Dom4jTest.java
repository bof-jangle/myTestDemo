package xyz.jangle.dom4j.test;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jTest {

	public static void main(String[] args) {
		
		String s = "<Element></Element>";               
		try {
			//创建xml文档对象
			Document document = DocumentHelper.parseText(s);
			//增加节点
			Element rootElement = document.getRootElement();
			Element e2 = rootElement.addElement("e2");
			Element e3 = rootElement.addElement("e3");
			e3.addAttribute("id", "2");
			//节点增加文本内容
			e2.addText("这里是文本内容Text");
			//节点增加属性
			e2.addAttribute("这里是属性名称", "这里是属性值Value");
			e2.addAttribute("id", "1");
			//xml转String
			String asXML = document.asXML();
			System.out.println(asXML);
			
			//遍历节点
			Iterator<?> elementIterator = rootElement.elementIterator();
			while(elementIterator.hasNext()){
				Element element = (Element)elementIterator.next();
				System.out.println(element.getName());
				System.out.println(element.getText());
			}
			@SuppressWarnings("unchecked")
			List<Element> list = rootElement.elements();
			for(Element e : list){
				System.out.println(e.getName());
				System.out.println(e.getText());
			}
			
			//遍历属性
			Iterator<?> attributeIterator = e2.attributeIterator();
			while(attributeIterator.hasNext()){
				Attribute a = (Attribute)attributeIterator.next();
				System.out.println(a.getName()+" "+a.getValue());
			}
			@SuppressWarnings("unchecked")
			List<Attribute> attributes = e2.attributes();
			for(Attribute a : attributes){
				System.out.println(a.getName());
				System.out.println(a.getValue());
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} 

	}

}
