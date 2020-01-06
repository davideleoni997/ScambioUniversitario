package bean;

import controller.OrderController;
import logic.Order;

public class OrderBean {
	private Integer Length;
	private Order list[];
	
	public Order[] getList() {
		return list;
	}

	public void setList(Order[] list) {
		this.list = list;
	}

	public OrderBean(Order[] list) {
		super();
		Length = list.length;
		this.list = list;
	}

	public OrderBean(String user) {
		this.Length = 0;
		OrderController oC = new OrderController(user);
		this.setList(oC.getOrdersInfo(user));
		if(list!=null)
		this.setLength(this.getList().length);
		else
		this.setLength(0);
	}

	public Integer getLength() {
		return Length;
	}

	public void setLength(Integer length) {
		Length = length;
	}


	public String getOrders(String user) {
		 
		 
		String out="";		//Stringa finale			
			if(this.list!=null)
			for(int i=0;i<this.Length;i++)			//Itero su ogni ordine
				{ 
				if(this.list[i] !=null) {
					out = out+"<li>"; 				//Nuovo punto elenco
					out=out+"\n"+this.list[i].getItem().getNome(); //Prendo nome oggetto
				    
					out = out+"</li>\n"; //Fine ordine
					}
				}
			else
				out="No Orders";
		return out;   //Ritorno stringa finale elenco
		 
	}

}
