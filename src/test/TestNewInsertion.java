package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.InsertionController;
import controller.LoginController;
import controller.RegistrationController;
import logic.BasicInformations;
import logic.Filters;

class TestNewInsertion {
	//Test for the creation of a new insertion in the DB
	//Check if, after creating the insertion, the length of the list of the user's insertions increases
	//Creator : Davide Leoni

	private static final String USERNAME = "dav";
	
	@Test
	void testgetResult() {
		
		RegistrationController rc = RegistrationController.getInstance();
		rc.registraUtente("Davide", "Leoni", USERNAME, "pass", false, "X12345"); //Create a new user, if it fails the user exists
		LoginController lc = LoginController.getInstance();
		InsertionController ic = InsertionController.getInstance();
		BasicInformations basic = new BasicInformations();
		basic.setTitle("TestTitle");
		basic.setDesc("TestDesc");
		basic.setPrice(20);
		Filters filter = new Filters();
		filter.setUniversity("test Uni");
		filter.setSubject("Test subj");
		List<File> images = new LinkedList<>();
		Integer size = ic.myInsertions(lc.getIdFromUsername(USERNAME)).size();
		ic.newInsertion(basic, images, lc.getIdFromUsername(USERNAME), filter); //Will fail if there is no seller with id 6
		
		
		assertTrue(size < ic.myInsertions(lc.getIdFromUsername(USERNAME)).size());
	}

}
