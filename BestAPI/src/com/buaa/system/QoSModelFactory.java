package com.buaa.system;

import java.util.HashMap;
import java.util.List;

import com.buaa.dao.QoSModelDAO;
import com.buaa.model.Domain;
import com.buaa.model.QoSModel;

public class QoSModelFactory {
	
	private static QoSModelFactory instance = null;
	private HashMap<Domain, QoSModel> map;
	private QoSModelDAO dao;
	
	private QoSModelFactory() {
		this.map = new HashMap<Domain, QoSModel>();
		this.dao = new QoSModelDAO();	
		initialize();
	}
	
	private void initialize() {
		List<Domain> domains = DomainFactory.getInstance().getAllDomains();
		for (Domain domain : domains) {
			QoSModel qosModel = this.dao.getByDomain(domain.getName());
			System.out.println(domain.getName() + "\t gen QoSModel");
			this.map.put(domain, qosModel);
		}
	}
	
	public QoSModel getQoSModelByDomain(Domain domain) {
		QoSModel qosModel = this.map.get(domain);
		return qosModel;
	}
	
	public static QoSModelFactory getInstance() {
		if (instance == null)
			instance = new QoSModelFactory();
		return instance;
	}
}
