package controller;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.InsertionDao;
import dao.ReportDao;

import logic.Report;

public class ReportController {

	private static ReportController instance;

    public static ReportController getInstance() {
        if (instance == null)
            instance = new ReportController();
        return instance;
    }
	
    private ReportController() {
    	//Constructor reports
    	}
    
    
    
	public void newReport(Integer insReported, String desc, Integer reporter) {
		try {
			Report rep = new Report(0,insReported,desc,reporter);
			rep.newReport();
			
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "newReport", e);
		
		}
	}
	
	public List<Report> getReport() {
		try {
			return ReportDao.getReports();
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getReport", e);
			return new LinkedList<>();
		
		}
	}
	
	public void ban(Integer repId,Integer id) {
		removeReport(repId);
		try {
			InsertionDao.ban(id);
		} catch (Exception e) {
			Logger.getGlobal().log(Level.WARNING, "Ban", e);
		}
	}
	
	public void removeReport(Integer id) {
		try {
			ReportDao.removeReport(id);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "removeReport", e);
		
		}
	}
}
