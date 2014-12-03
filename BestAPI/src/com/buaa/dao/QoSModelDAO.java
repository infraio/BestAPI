package com.buaa.dao;
import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.buaa.model.*;

public class QoSModelDAO {
	private final String dir = "/home/xiaohao/github/BestAPI/BestAPI/data";
	
	public QoSModel getByDomain(String domain) {
		QoSModel qosModel = new QoSModel(domain);
		File file = new File(dir + "/QoSModel_" + domain + ".xml");
		try {
			System.out.println(file.getPath());
			if (file.exists()) {
				System.out.println("2");
				SAXReader sr = new SAXReader();
				Document doc = sr.read(file);
				Element rootElement = doc.getRootElement();
				qosModel.setRoot(recurseRead(rootElement, 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qosModel;
	}
	
	public QoSAttribute recurseRead(Element rootElement, int level) {
		String name = rootElement.elementText("name");
		QoSAttribute attribute = new QoSAttribute(name, level);
		Element nodes = rootElement.element("QoSAttributes");
		if (nodes != null) {
			for(@SuppressWarnings("rawtypes")
			Iterator iter = nodes.elementIterator(); iter.hasNext(); ) {
				attribute.addAttribute(recurseRead((Element)iter.next(), level + 1));
			}
		}
		return attribute;
	}
}
