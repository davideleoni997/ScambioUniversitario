package controller;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.InsertionDao;
import logic.Filters;
import logic.Insertion;

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
	
	public Vector<String> getResearchResults(String research, Filters filters) {
		
		Vector<String> out = new Vector<>();
		try {
			Vector<Insertion> ins;
			ins=InsertionDao.getReserach(research, filters);
			for(Insertion inserzione : ins) {
					out.add(inserzione.getTitle());		
			}
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"InsController",e);
		}
		return out;
	}
}
