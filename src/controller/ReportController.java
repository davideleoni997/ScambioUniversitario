package controller;


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
		ReportDao.newReport(insReported, desc, reporter);
	}
	
	public List<Report> getReport() {
		return ReportDao.getReports();
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
		ReportDao.removeReport(id);
	}
}
