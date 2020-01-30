package logic;

import java.util.LinkedList;
import java.util.List;

import bean.InsertionBean;

public class FiltroNotes implements Filtro{
Boolean notes;
	
	public FiltroNotes(Boolean book) {
		this.notes = book;
	}
	
	@Override
	public List<InsertionBean> filtra(List<InsertionBean> inserzioni) {
		List<InsertionBean> appunti = new LinkedList<>();
		
		for(InsertionBean ins : inserzioni) {
			if(ins.getFilter().getBook().equals(notes))
				appunti.add(ins);
		}
		return appunti;
	}
}
