package controller;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.InsertionDao;
import dao.ReportDao;

import logic.Report;

public class ReportController {

	public ReportController() {
		//Per instanziare report
	}
	
	public void newReport(Integer insReported, String desc, Integer reporter) {
		try {
			ReportDao.newReport(insReported, desc, reporter);
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getMessage", e);
		
		}
	}
	
	public List<Report> getReport() {
		try {
			return ReportDao.getReports();
		} catch (Exception e) {
			
			Logger.getGlobal().log(Level.WARNING, "getMessage", e);
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
			
			Logger.getGlobal().log(Level.WARNING, "getMessage", e);
		
		}
	}
}
