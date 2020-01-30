package logic;

import java.util.LinkedList;
import java.util.List;

import bean.InsertionBean;

public class FiltroUni implements Filtro{
	String uni;
	
	public FiltroUni(String uni) {
		this.uni = uni;
	}
	
	@Override
	public List<InsertionBean> filtra(List<InsertionBean> inserzioni) {
		List<InsertionBean> university = new LinkedList<>();
		
		for(InsertionBean ins : inserzioni) {
			if(ins.getFilter().getUniversity().contains(uni))
				university.add(ins);
		}
		return university;
	}

}
