package logic;

import java.util.List;

import bean.InsertionBean;

public class FiltroAnd implements Filtro{
	private Filtro filtro;
	private Filtro altroFiltro;
	
	public FiltroAnd(Filtro filtro, Filtro altroFiltro) {
		super();
		this.filtro = filtro;
		this.altroFiltro = altroFiltro;
	}

	@Override
	public List<InsertionBean> filtra(List<InsertionBean> inserzioni) {
		
		List<InsertionBean> primoFiltro = filtro.filtra(inserzioni);
		return altroFiltro.filtra(primoFiltro);
	}
	
	

}
