package logic;

import java.util.Date;

public class BasicInformations{
		private String title;
		private String desc;
		private Date date;
		private Integer price;
		
		public BasicInformations(String title, String desc, Date date, Integer price) {
			super();
			this.title = title;
			this.desc = desc;
			this.date = date;
			this.price = price;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public Integer getPrice() {
			return price;
		}
		public void setPrice(Integer price) {
			this.price = price;
		}
	}