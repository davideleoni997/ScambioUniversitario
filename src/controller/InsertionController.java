package controller;

import java.util.Vector;

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
	
	public Vector<InsertionBean> getResearchResults(String research, Filters filters) {
			
			return InsertionDao.getReserach(research, filters);
			
			
	}
}
