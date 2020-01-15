package factory;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ITFactory implements LanguageFactory{
		Document doc;
	public ITFactory() {
		
		try {
		File fXmlFile = new File("src/factory/strings_it.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		}
		
		catch(Exception e) {
			Logger.getGlobal().log(Level.WARNING,"LanguageFactory",e);
		}
	}
	
	public String getWelcomeString() {
		return doc.getElementsByTagName("Welcome").item(0).getTextContent();
		
	}

	@Override
	public String getNameString() {
		return doc.getElementsByTagName("Name").item(0).getTextContent();
		
	}

	@Override
	public String getSurnameString() {
		return doc.getElementsByTagName("Surname").item(0).getTextContent();
		
	}

	@Override
	public String getViewOrders() {
		return doc.getElementsByTagName("ViewOrders").item(0).getTextContent();
		
	}

	@Override
	public String getModifyProfile() {
		return doc.getElementsByTagName("ModifyProfile").item(0).getTextContent();
	}

	@Override
	public String getMessagesString() {
		return doc.getElementsByTagName("Messages").item(0).getTextContent();
	}

	@Override
	public String getYourOrdersString() {
		return doc.getElementsByTagName("YourOrders").item(0).getTextContent();
	}

	@Override
	public String getDetailsString() {
		return doc.getElementsByTagName("Details").item(0).getTextContent();
	}

	@Override
	public String getBackString() {
		return doc.getElementsByTagName("Back").item(0).getTextContent();
	}

	@Override
	public String getOldPasswordString() {
		return doc.getElementsByTagName("OldPassword").item(0).getTextContent();
	}

	@Override
	public String getNewPasswordString() {
		return doc.getElementsByTagName("NewPassword").item(0).getTextContent();
	}

	@Override
	public String getSubmitString() {
		return doc.getElementsByTagName("Submit").item(0).getTextContent();
	}

	@Override
	public String getMessageString() {
		return doc.getElementsByTagName("Message").item(0).getTextContent();
	}

	@Override
	public String getFromString() {
		return doc.getElementsByTagName("From").item(0).getTextContent();
	}

	@Override
	public String getNewMessageString() {
		return doc.getElementsByTagName("NewMessage").item(0).getTextContent();
	}

	@Override
	public String getBuyerString() {
		return doc.getElementsByTagName("Buyer").item(0).getTextContent();
	}

	@Override
	public String getSellerString() {
		return doc.getElementsByTagName("Seller").item(0).getTextContent();
	}

	@Override
	public String getPriceString() {
		return doc.getElementsByTagName("Price").item(0).getTextContent();
	}

	@Override
	public String getItemString() {
		return doc.getElementsByTagName("Item").item(0).getTextContent();
	}

	@Override
	public String getDateString() {
		return doc.getElementsByTagName("Date").item(0).getTextContent();
	}

	@Override
	public String getWrongData() {
		return doc.getElementsByTagName("WrongData").item(0).getTextContent();
	}

	@Override
	public String getErrorString() {
		return doc.getElementsByTagName("Error").item(0).getTextContent();
	}

	@Override
	public String getWrongOldPsw() {
		return doc.getElementsByTagName("WrongOldPsw").item(0).getTextContent();
	}

	@Override
	public String getRegister() {
		return doc.getElementsByTagName("Register").item(0).getTextContent();
	}

	@Override
	public String getSearchText() {
		return doc.getElementsByTagName("SearchText").item(0).getTextContent();
	}

	@Override
	public String getSearchString() {
		return doc.getElementsByTagName("Search").item(0).getTextContent();
	}
}
