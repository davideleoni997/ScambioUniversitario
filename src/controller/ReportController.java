package controller;

import java.util.List;

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
		InsertionDao.ban(id);
	}
	
	public void removeReport(Integer id) {
		ReportDao.removeReport(id);
	}
}
