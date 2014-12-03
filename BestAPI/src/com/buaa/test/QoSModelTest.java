package com.buaa.test;
import com.buaa.dao.*;
import com.buaa.model.*;

public class QoSModelTest {

	public static void main(String[] args) {
		QoSModelDAO dao = new QoSModelDAO();
		System.out.println(dao.getByDomain("Payment"));
	}

}