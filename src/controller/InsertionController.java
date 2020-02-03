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
import logic.FiltroAnd;
import logic.FiltroBook;
import logic.FiltroCity;
import logic.FiltroNotes;
import logic.FiltroSubj;
import logic.FiltroUni;


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
				if(filters == null)
					filters = new Filters();
				FiltroUni uni = new FiltroUni(filters.getUniversity());
				FiltroCity city = new FiltroCity(filters.getCity());
				
				FiltroSubj subj = new FiltroSubj(filters.getSubject());
				FiltroBook book = new FiltroBook(filters.getBook());
				
				FiltroNotes notes = new FiltroNotes(filters.getNotes());
				FiltroAnd first = new FiltroAnd(uni,city);
				
				FiltroAnd second = new FiltroAnd(subj,book);
				FiltroAnd third = new FiltroAnd(notes,first);
				
				FiltroAnd fourth = new FiltroAnd(second,third);
				return fourth.filtra(InsertionDao.getReserach(research, filters.getDate()));
			} catch (Exception e) {
				Logger.getGlobal().log(Level.WARNING, "ClassNotFound", e);
				return new LinkedList<>();
			} 
			
			
	}
}
