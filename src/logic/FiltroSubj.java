package logic;

import java.util.LinkedList;
import java.util.List;

import bean.InsertionBean;

public class FiltroSubj implements Filtro{
	String subj;
	
	public FiltroSubj(String subj) {
		this.subj = subj;
	}

	@Override
	public List<InsertionBean> filtra(List<InsertionBean> inserzioni) {
		
		List<InsertionBean> materia = new LinkedList<>();
		
		for(InsertionBean ins : inserzioni) {
			if(ins.getFilter().getSubject().contains(subj))
				materia.add(ins);
		}
		return materia;
	}
}
