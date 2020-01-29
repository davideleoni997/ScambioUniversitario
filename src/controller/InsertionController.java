package controller;


import java.io.File;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;
import dao.InsertionDao;
import logic.BasicInformations;
import logic.Filters;


public class InsertionController {

	public InsertionController() {
		//Costruttore
	}
	
	public boolean newInsertion(BasicInformations basic, List<File> images, Integer seller, Filters filter) {
		
		try {
			
			InsertionDao.newInsertion(basic, images, seller, filter);
		}
		
		
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public List<InsertionBean> getResearchResults(String research, Filters filters) {
			
			try {
				return InsertionDao.getReserach(research, filters);
			} catch (Exception e) {
				Logger.getGlobal().log(Level.WARNING, "ClassNotFound", e);
				return new LinkedList<>();
			} 
			
			
	}
}
