package logic;

import java.util.LinkedList;
import java.util.List;

import bean.InsertionBean;

public class FiltroBook implements Filtro{
	Boolean book;
	
	public FiltroBook(Boolean book) {
		this.book = book;
	}
	
	@Override
	public List<InsertionBean> filtra(List<InsertionBean> inserzioni) {
		List<InsertionBean> libri = new LinkedList<>();
		
		for(InsertionBean ins : inserzioni) {
			if(ins.getFilter().getBook().equals(book))
				libri.add(ins);
		}
		return libri;
	}
}


