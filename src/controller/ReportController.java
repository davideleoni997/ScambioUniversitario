package controller;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.InsertionDao;
import dao.ReportDao;

import logic.Report;

public class ReportController {
	//Singleton of a report controller, has responsabilities over the entity Report
	private static ReportController instance;

    public static ReportController getInstance() {
        if (instance == null)
            instance = new ReportController();
        return instance;
    }
	
    private ReportController() {
    	//Constructor reportController
    	}
    
    
    
	public void newReport(Integer insReported, String desc, Integer reporter) {
		//Method to create a new report
		try {
			Report rep = new Report(0,insReported,desc,reporter);//Create an instance of the entity report
			rep.newReport();//Call its method to save it to DB
			
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "newReport", e);
		
		}
	}
	
	public List<Report> getReport() {
		//Get the list of reports at the moment in the system
		try {
			return ReportDao.getReports();
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getReport", e);
			return new LinkedList<>();
		
		}
	}
	
	public void ban(Integer repId,Integer id) {
		//Method to execute the ban action on an insertion
		//remove the report and then the insertion to satisfy constraints
		removeReport(repId);
		try {
			InsertionDao.ban(id);
		} catch (Exception e) {
			Logger.getGlobal().log(Level.WARNING, "Ban", e);
		}
	}
	
	public void removeReport(Integer id) {
		//Method to remove the report from the system
		try {
			ReportDao.removeReport(id);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "removeReport", e);
		
		}
	}
}
