package controller;

import dao.InsertionDao;
import logic.Insertion;

public class InsertionController {

	public InsertionController() {
		
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
	
	public String[] getResearchResults(String research) {
		
		String out[]= new String[100];
		try {
			
			Insertion ins[]= new Insertion[100];
			ins=InsertionDao.getReserach(research);
			for(int i=0;i<ins.length;i++) {
				if(ins[i]!=null)
				out[i]=new String(ins[i].getTitle());
			}
			
		}
		
		catch(Exception e) {
			return null;
		}
		return out;
	}
}
