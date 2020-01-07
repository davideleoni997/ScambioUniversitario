package bean;

import controller.OrderController;
import logic.Order;


//Rivedere per singolo ordine, non lista
public class OrderBean {
	private Integer Length;
	private Order list[];
	private String user;
	
	public Order[] getList() {
		return list;
	}

	public void setList(Order[] list) {
		this.list = list;
		if(list!=null)
			this.setLength(this.getList().length);
			else
			this.setLength(0);
	}


	public OrderBean() {
		this.Length = 0;
		this.list = null;
		
	}

	public Integer getLength() {
		return Length;
	}
	
	private void setLength(Integer length) {
		this.Length= length;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		OrderController oC = new OrderController(this.user);
		this.setList(oC.getOrdersInfo(this.user));
		
	}

	public String getOrders() {
		 
		 
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
