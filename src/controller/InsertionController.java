package controller;


import java.io.File;
import java.util.List;

import bean.InsertionBean;
import dao.InsertionDao;

import logic.Filters;


public class InsertionController {

	public InsertionController() {
		//Costruttore
	}
	
	public boolean newInsertion(String title, String desc, String price, List<File> images, Integer seller, String university,String city,String subject, Boolean book, Boolean note) {
		
		try {
			
			InsertionDao.newInsertion(title, desc, price, images, seller, university, city, subject, book, note);
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
