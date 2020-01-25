package factory;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ENFactory implements LanguageFactory{
	private static final String LANGUAGE = "En";
	Document doc;
	public ENFactory() {
		try {
			
			File fXmlFile = new File("src/factory/strings.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			dbFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			}
			
			catch(Exception e) {
				Logger.getGlobal().log(Level.WARNING,"LanguageFactory",e);
			}
	}

	public String getWelcomeString() {
		Element n = (Element) doc.getElementsByTagName("Welcome").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
		
	}

	@Override
	public String getNameString() {
		Element n = (Element) doc.getElementsByTagName("Name").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
		
	}

	@Override
	public String getSurnameString() {
		Element n = (Element) doc.getElementsByTagName("Surname").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
		
	}

	@Override
	public String getViewOrders() {
		Element n = (Element) doc.getElementsByTagName("ViewOrders").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
		
	}

	@Override
	public String getModifyProfile() {
		Element n = (Element) doc.getElementsByTagName("ModifyProfile").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getMessagesString() {
		Element n = (Element) doc.getElementsByTagName("Messages").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getYourOrdersString() {
		Element n = (Element) doc.getElementsByTagName("YourOrders").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getDetailsString() {
		Element n = (Element) doc.getElementsByTagName("Details").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getBackString() {
		Element n = (Element) doc.getElementsByTagName("Back").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getOldPasswordString() {
		Element n = (Element) doc.getElementsByTagName("OldPassword").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getNewPasswordString() {
		Element n = (Element) doc.getElementsByTagName("NewPassword").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getSubmitString() {
		Element n = (Element) doc.getElementsByTagName("Submit").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getMessageString() {
		Element n = (Element) doc.getElementsByTagName("Message").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getFromString() {
		Element n = (Element) doc.getElementsByTagName("From").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getNewMessageString() {
		Element n = (Element) doc.getElementsByTagName("NewMessage").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getBuyerString() {
		Element n = (Element) doc.getElementsByTagName("Buyer").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getSellerString() {
		Element n = (Element) doc.getElementsByTagName("Seller").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getPriceString() {
		Element n = (Element) doc.getElementsByTagName("Price").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getItemString() {
		Element n = (Element) doc.getElementsByTagName("Item").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getDateString() {
		Element n = (Element) doc.getElementsByTagName("Date").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getWrongData() {
		Element n = (Element) doc.getElementsByTagName("WrongData").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getErrorString() {
		Element n = (Element) doc.getElementsByTagName("Error").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getWrongOldPsw() {
		Element n = (Element) doc.getElementsByTagName("WrongOldPsw").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getRegister() {
		Element n = (Element) doc.getElementsByTagName("Register").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getSearchText() {
		Element n = (Element) doc.getElementsByTagName("SearchText").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getSearchString() {
		Element n = (Element) doc.getElementsByTagName("Search").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getStudQuestion() {
		Element n = (Element) doc.getElementsByTagName("StudQuestion").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getEnrollString() {
		Element n = (Element) doc.getElementsByTagName("Enroll").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getCheckString() {
		Element n = (Element) doc.getElementsByTagName("Check").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getLogoString() {
		Element n = (Element) doc.getElementsByTagName("Logo").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getUploadString() {
		Element n = (Element) doc.getElementsByTagName("Upload").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getCheckError() {
		Element n = (Element) doc.getElementsByTagName("CheckError").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getStudString() {
		Element n = (Element) doc.getElementsByTagName("Student").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getCompanyString() {
		Element n = (Element) doc.getElementsByTagName("Company").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}
	
	@Override
	public String getFiltersString() {
		Element n = (Element) doc.getElementsByTagName("Filters").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getUniversityString() {
		Element n = (Element) doc.getElementsByTagName("University").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getCityString() {
		Element n = (Element) doc.getElementsByTagName("City").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getSubjectString() {
		Element n = (Element) doc.getElementsByTagName("Subject").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getBooksString() {
		Element n = (Element) doc.getElementsByTagName("Books").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getNotesString() {
		Element n = (Element) doc.getElementsByTagName("Notes").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getClearString() {
		Element n = (Element) doc.getElementsByTagName("Clear").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}
	
	@Override
	public String getApplyString() {
		Element n = (Element) doc.getElementsByTagName("Apply").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getOrderString() {
		Element n = (Element) doc.getElementsByTagName("Order").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getNewerString() {
		Element n = (Element) doc.getElementsByTagName("Newer").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getOlderString() {
		Element n = (Element) doc.getElementsByTagName("Older").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getNearestString() {
		Element n = (Element) doc.getElementsByTagName("Nearest").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getFarthestString() {
		Element n = (Element) doc.getElementsByTagName("Farthest").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}
	
	@Override
	public String getTitleString() {
		Element n = (Element) doc.getElementsByTagName("Title").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}
	
	@Override
	public String getNewInsertionText() {
		Element n = (Element) doc.getElementsByTagName("NewInsertionText").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getNewInsertionString() {
		Element n = (Element) doc.getElementsByTagName("NewInsertion").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getDescriptionString() {
		Element n = (Element) doc.getElementsByTagName("Description").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getBuyString() {
		Element n = (Element) doc.getElementsByTagName("Buy").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getConfirmationString() {
		Element n = (Element) doc.getElementsByTagName("Confirmation").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getConfirmationBuyText() {
		Element n = (Element) doc.getElementsByTagName("ConfirmationBuyText").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getYesString() {
		Element n = (Element) doc.getElementsByTagName("Yes").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getNoString() {
		Element n = (Element) doc.getElementsByTagName("No").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getImagesString() {
		Element n = (Element) doc.getElementsByTagName("Images").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getInsertString() {
		Element n = (Element) doc.getElementsByTagName("Insert").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getNoLogoString() {
		Element n = (Element) doc.getElementsByTagName("NoLogo").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getPaidString() {
		Element n = (Element) doc.getElementsByTagName("Paid").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getPayString() {
		Element n = (Element) doc.getElementsByTagName("Pay").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}

	@Override
	public String getPaymentErrorString() {
		Element n = (Element) doc.getElementsByTagName("PaymentError").item(0);
		return n.getElementsByTagName(LANGUAGE).item(0).getTextContent();
	}
}