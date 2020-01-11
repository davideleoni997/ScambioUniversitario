package controller;

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
	
	public String[] getResearchResults(String research, Filters filters) {
		
		String[] out= new String[100];
		try {
			
			Insertion[] ins;
			ins=InsertionDao.getReserach(research, filters);
			for(int i=0;i<ins.length;i++) {
				if(ins[i]!=null)
					out[i]=ins[i].getTitle();
			}
			
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"InsController",e);
		}
		return out;
	}
}
