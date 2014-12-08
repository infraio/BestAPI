package com.buaa.dao;
import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.buaa.model.*;
import com.buaa.system.DomainFactory;

public class QoSModelDAO {
	private final String dir = "/home/xiaohao/github/BestAPI/BestAPI/data";
	
	public QoSModel getByDomain(String domainName) {
		QoSModel qosModel = new QoSModel(DomainFactory.getInstance().getDomain(domainName));
		qosModel.setRoot(getRoot(dir + "/QoSModel_" + domainName + ".xml"));
		return qosModel;
	}
	
	private QoSAttribute getRoot(String fileName) {
		File file = new File(fileName);
		QoSAttribute root = null;
		try {
//			System.out.println(file.getPath());
			if (file.exists()) {
				SAXReader sr = new SAXReader();
				Document doc = sr.read(file);
				Element rootElement = doc.getRootElement();
				root = recurseRead(rootElement, 1);
			} else {
				root = getRoot(dir + "/QoSModel_Base.xml");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
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
