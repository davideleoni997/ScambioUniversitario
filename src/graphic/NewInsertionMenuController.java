package graphic;

import java.util.logging.Level;
import java.util.logging.Logger;

import factory.LanguageFactory;

public class NewInsertionMenuController {
	private LanguageFactory lg;
	private ViewController vc;
	
	public NewInsertionMenuController() {
		vc = ViewController.getInstance();
		try{
			if(System.getProperty("user.language").equalsIgnoreCase("en"))
				lg = LanguageFactory.getfactory(0);
			else
				lg = LanguageFactory.getfactory(1);
		}
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"NewInsertionController",e);
		}
	}
	
}
