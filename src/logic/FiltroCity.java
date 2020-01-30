package logic;

import java.util.LinkedList;
import java.util.List;

import bean.InsertionBean;

public class FiltroCity implements Filtro{
	String city;
	
	public FiltroCity(String city) {
		this.city = city;
	}

	@Override
	public List<InsertionBean> filtra(List<InsertionBean> inserzioni) {
		
		List<InsertionBean> cities = new LinkedList<>();
		
		for(InsertionBean ins : inserzioni) {
			if(ins.getFilter().getCity().contains(city))
				cities.add(ins);
		}
		return cities;
	}
}
