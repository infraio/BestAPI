package com.buaa.model;

import java.util.ArrayList;
import java.util.List;

import com.buaa.dao.TreeDAOFactory;

public class DataInstance {
	
	private List<DataItem> independentItems;
	private List<DataItem> relatedItems;
	
	public DataInstance() {
		this.independentItems = new ArrayList<DataItem>();
		this.relatedItems = new ArrayList<DataItem>();
	}

	public List<DataItem> getIndependentItems() {
		return independentItems;
	}
	
	public void addIndependentItem (DataItem item) {
		this.independentItems.add(item);
	}

	public void setIndependentItems(List<DataItem> independentItems) {
		this.independentItems = independentItems;
	}

	public List<DataItem> getRelatedItems() {
		return relatedItems;
	}

	public void addRelatedItem(DataItem item) {
		this.relatedItems.add(item);
	}
	
	public void setRelatedItems(List<DataItem> relatedItems) {
		this.relatedItems = relatedItems;
	}
}
