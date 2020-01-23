package controller;


import java.io.File;
import java.util.LinkedList;
import java.util.List;

import bean.InsertionBean;
import dao.InsertionDao;

import logic.Filters;


public class InsertionController {

	public InsertionController() {
		//Costruttore
	}
	
	public boolean newInsertionMockup() {
		
		try {
			
			InsertionDao.newInsertion("Libri","Nuovo" , "23", null, 12);
		}
		
		
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean newInsertion(String title, String desc, String price, LinkedList<File> images, Integer seller) {
		
		try {
			
			InsertionDao.newInsertion(title, desc, price, images, seller);
		}
		
		
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public List<InsertionBean> getResearchResults(String research, Filters filters) {
			
			return InsertionDao.getReserach(research, filters);
			
			
	}
}
