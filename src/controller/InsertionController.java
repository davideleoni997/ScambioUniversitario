package controller;


import java.io.File;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.InsertionBean;
import dao.InsertionDao;
import dao.UtenteDao;
import javafx.scene.image.Image;
import logic.BasicInformations;
import logic.Filters;
import logic.FiltroAnd;
import logic.FiltroBook;
import logic.FiltroCity;
import logic.FiltroNotes;
import logic.FiltroSubj;
import logic.FiltroUni;
import logic.Insertion;


public class InsertionController {
//Singleton controller that has the responasbility to interact with the insertions
	private static InsertionController instance;
	
	public static InsertionController getInstance() {
        if (instance == null)
            instance = new InsertionController();
        return instance;
    }
	
	private InsertionController() {
		//Costruttore
	}
	
	public boolean newInsertion(BasicInformations basic, List<File> images, Integer seller, Filters filter) {
		//method to create a new Insertion
		//Create an insertion entity and call its method to create a new insertion
		//If everything goes fine returns true, if an exception is thrown it returns false
		try {
			Insertion ins = new Insertion(-1,basic,images,seller,filter); //Create an instance of insertion
			ins.newInsertion(); // Call the method to create a new insertion on the DB
			
		}
		
		
		catch (Exception e) {
			//An exception is thrown, mostly a classnotfound for the JDBC or an sqlException
			return false;
		}
		
		return true;
	}
	
	public List<InsertionBean> getResearchResults(String research, Filters filters) {
			//Get a list of InsertionBeans using a string and a collection of
			//attributes rapresented by Filters
			try {
				if(filters == null)
					filters = new Filters();

				//Filtering the research by using the attributes of Filters
				//Used the decorator pattern to create the objects that
				//implements Filtro
				FiltroUni uni = new FiltroUni(filters.getUniversity()); //Filter based on the University
				FiltroCity city = new FiltroCity(filters.getCity());//Filter based on the city
				
				FiltroSubj subj = new FiltroSubj(filters.getSubject());//Filter based on the subject
				FiltroBook book = new FiltroBook(filters.getBook());//Filter based on the type of item
				
				FiltroNotes notes = new FiltroNotes(filters.getNotes());//Filter based on the type of item
				FiltroAnd first = new FiltroAnd(uni,city);//Join first two filters
				
				FiltroAnd second = new FiltroAnd(subj,book);//Join the second group of filters
				FiltroAnd third = new FiltroAnd(notes,first);//Join last filter and first group
				
				FiltroAnd fourth = new FiltroAnd(second,third);//Join all filters
				//call the filter method of the joined filters on the list returned by the Dao
				return fourth.filtra(InsertionDao.getReserach(research, filters.getDate()));
			} catch (Exception e) {
				//Catch classnotfound or sqlException
				Logger.getGlobal().log(Level.WARNING, "ClassNotFound", e);
				return new LinkedList<>();
			} 
			
			
	}
	
	public List<InsertionBean> myInsertions(Integer user) {
		//Method to return only the insertions created by the user
		try {
			
			return InsertionDao.getMyInsertions(user); //Return user insertions using the Dao
		} catch (Exception e) {
			//SqlException or ClassNotFound
			Logger.getGlobal().log(Level.WARNING, "ClassNotFound", e);
			return new LinkedList<>();
		} 
		
		
}
	
	public InsertionBean getDetail(Integer id) {
			//method to return a single InsertionBean matching the id
			Insertion ins = new Insertion();
			ins.setId(id); //Create the object Insertion and set its id to id
			//return the insertionbean representing that Insertion
			return ins.getDetail();		
	}

	public void delete(Integer id) {
		//method to delete an insertion using its id
		try {
			//method in the dao to delete the insertion
			InsertionDao.ban(id);
		} catch (Exception e) {
			//Class not found or SqlException
			Logger.getGlobal().log(Level.WARNING, "Delete", e);
		
		}
		
	}

	public void modify(InsertionBean ib) {
		try {
			//Method to update the informations of an insertion using its bean rappresentation
			InsertionDao.update(ib);
		} catch (Exception e) {
			//SqlException or ClassNotFound
			Logger.getGlobal().log(Level.WARNING, "Delete", e);
		
		}
		
	}
	
	public boolean isCompany(String seller) {
		//Check if a seller is a company using its username
		try {
			return UtenteDao.isCompany(seller);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "isCompany", e);
		 return false;
		}
	}

	public Image getLogo(String seller) {
		//Method to retrieve the logo of a company using its username, usually used after the isCompany method
		try {
			return UtenteDao.getLogo(seller);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "isCompany", e);
		 return new Image(getClass().getResourceAsStream(("No-Image-Available.png")));
		}
	}
}
