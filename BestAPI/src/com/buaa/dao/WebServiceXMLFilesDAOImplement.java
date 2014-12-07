package com.buaa.dao;

import java.io.File;
import java.util.List;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.buaa.model.WebService;
import com.buaa.model.WebServiceAttribute;

public class WebServiceXMLFilesDAOImplement implements WebServiceDAOInterface {

	private String directory;
	
	public WebServiceXMLFilesDAOImplement(String directory) {
		this.directory = directory;
	}
	
	public WebService getWebServiceByName(String name) throws Exception {
		WebService ws = new WebService();
		File target = new File(directory + name + ".xml");
		if(target.exists()) {
			SAXReader sr = new SAXReader();
			Document doc = sr.read(target);
			Element root = doc.getRootElement();
			WebServiceAttribute[] attributes = WebServiceAttribute.values();
			
			for(int i = 1; i < attributes.length; ++i)
				ws.setAttributeContent(attributes[i], root.elementText(attributes[i].getName()));
		}
		return ws;
	}
	
	public boolean addWebService(WebService ws) throws Exception {
		boolean flag = false;
		File target = new File(directory + ws.getAttributeContent(WebServiceAttribute.API_NAME) + ".xml");
		if(!target.exists()) {
			org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			WebServiceAttribute[] attributes = WebServiceAttribute.values();
			
			org.w3c.dom.Element root = doc.createElement("API");
			doc.appendChild(root);
			
			for(int i = 0; i < attributes.length; ++i) {
				org.w3c.dom.Element element = doc.createElement(attributes[i].getName());
				element.appendChild(doc.createTextNode(ws.getAttributeContent(attributes[i])));
				root.appendChild(element);
			}
			
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			trans.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	        StreamResult result = new StreamResult(target);
			trans.transform(source, result);
			flag = true;
		}
		return flag;
	}
	
	public List<WebService> getWebServicesByCategory(String category) throws Exception {
		return null;
	}
	
	public boolean deleteWebServiceByName(String name) throws Exception {
		boolean flag = false;
		File target = new File(directory + name + ".xml");
		if(target.exists()) {
			if(target.delete())
				flag = true;
		} else { flag = false; }
		return flag;
	}
	
	public boolean deleteWebService(WebService ws) throws Exception {
		return deleteWebServiceByName(ws.getName());
	}
	
	public List<WebService> fuzzySearch(String key) throws Exception {
		return null;
	}
	
	public boolean saveWebServicesFromDbToFile(String filePath) throws Exception {
		boolean flag = false;
		return flag;
	}
	
	public boolean saveWebServicesFromFileToDb(String filePath) throws Exception {
		boolean flag = false;
		return flag;
	}
}
