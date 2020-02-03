package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.InsertionController;
import logic.BasicInformations;
import logic.Filters;

class TestNewInsertion {
	//Test for the creation of a new insertion in the DB
	//Check if after creating the insertion it something pops up in the list
	//After researching for it
	//Creator : Davide Leoni

	@Test
	void testgetResult() {
		InsertionController ic = new InsertionController();
		BasicInformations basic = new BasicInformations();
		basic.setTitle("TestTitle");
		basic.setDesc("TestDesc");
		basic.setPrice(20);
		Filters filter = new Filters();
		filter.setUniversity("test Uni");
		filter.setSubject("Test subj");
		List<File> images = new LinkedList<>();
		ic.newInsertion(basic, images, 5, filter); 
		
		
		assertTrue(!ic.getResearchResults("TestTitle", filter).isEmpty());
	}

}
