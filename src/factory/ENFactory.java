package factory;


public class ENFactory implements LanguageFactory{
	
	public ENFactory() {
		//constructor for the english language factory
	}

	public String getWelcomeString() {
		return "Welcome to the login screen";
		
	}

	@Override
	public String getNameString() {
		return "Name";
		
	}

	@Override
	public String getSurnameString() {
		return "Surname";
		
	}

	@Override
	public String getViewOrders() {
		return "View orders";
		
	}

	@Override
	public String getModifyProfile() {
		return "Modify Profile";
	}

	@Override
	public String getMessagesString() {
		return "Messages";
	}

	@Override
	public String getYourOrdersString() {
		return "Your orders";
	}

	@Override
	public String getDetailsString() {
		return "Details";
	}

	@Override
	public String getBackString() {
		return "Back";
	}

	@Override
	public String getOldPasswordString() {
		return "Insert Old password";
	}

	@Override
	public String getNewPasswordString() {
		return "Insert new Password";
	}

	@Override
	public String getSubmitString() {
		return "Submit";
	}

	@Override
	public String getMessageString() {
		return "Message";
	}

	@Override
	public String getFromString() {
		return "From";
	}

	@Override
	public String getNewMessageString() {
		return "New message";
	}

	@Override
	public String getBuyerString() {
		return "Buyer";
	}

	@Override
	public String getSellerString() {
		return "Seller";
	}

	@Override
	public String getPriceString() {
		return "Price";
	}

	@Override
	public String getItemString() {
		return "Item";
	}

	@Override
	public String getDateString() {
		return "Date";
	}

	@Override
	public String getWrongData() {
		return "Wrong Data";
	}

	@Override
	public String getErrorString() {
		return "Error";
	}

	@Override
	public String getWrongOldPsw() {
		return "Wrong Old password";
	}

	@Override
	public String getRegister() {
		return "Register";
	}

	@Override
	public String getSearchText() {
		return "Start by searching for a book!";
	}

	@Override
	public String getSearchString() {
		return "Search";
	}

	@Override
	public String getStudQuestion() {
		return "Are you a student or a company?";
	}

	@Override
	public String getEnrollString() {
		return "Enrollment number";
	}

	@Override
	public String getCheckString() {
		return "Insert password again";
	}

	@Override
	public String getLogoString() {
		return "Company logo";
	}

	@Override
	public String getUploadString() {
		return "Upload";
	}

	@Override
	public String getCheckError() {
		return "The passwords don't match";
	}

	@Override
	public String getStudString() {
		return "Student";
	}

	@Override
	public String getCompanyString() {
		return "Company";
	}
	
	@Override
	public String getFiltersString() {
		return "Filters";
	}

	@Override
	public String getUniversityString() {
		return "University";
	}

	@Override
	public String getCityString() {
		return "City";
	}

	@Override
	public String getSubjectString() {
		return "Subject";
	}

	@Override
	public String getBooksString() {
		return "Books";
	}

	@Override
	public String getNotesString() {
		return "Notes";
	}

	@Override
	public String getClearString() {
		return "Clear";
	}
	
	@Override
	public String getApplyString() {
		return "Apply";
	}

	@Override
	public String getOrderString() {
		return "Order";
	}

	@Override
	public String getNewerString() {
		return "Newer";
	}

	@Override
	public String getOlderString() {
		return "Older";
	}

	@Override
	public String getNearestString() {
		return "Nearest";
	}

	@Override
	public String getFarthestString() {
		return "Farthest";
	}
	
	@Override
	public String getTitleString() {
		return "Title";
	}
	
	@Override
	public String getNewInsertionText() {
		return "Or create your own Insertion!";
	}

	@Override
	public String getNewInsertionString() {
		return "New Insertion";
	}

	@Override
	public String getDescriptionString() {
		return "Description";
	}

	@Override
	public String getBuyString() {
		return "Buy";
	}

	@Override
	public String getConfirmationString() {
		return "Confirmation";
	}

	@Override
	public String getConfirmationBuyText() {
		return "Do you really want to buy the item?";
	}

	@Override
	public String getYesString() {
		return "Yes";
	}

	@Override
	public String getNoString() {
		return "No";
	}

	@Override
	public String getImagesString() {
		return "Images";
	}

	@Override
	public String getInsertString() {
		return "Insert";
	}

	@Override
	public String getNoLogoString() {
		return "No Logo";
	}

	@Override
	public String getPaidString() {
		return "Paid";
	}

	@Override
	public String getPayString() {
		return "Pay";
	}

	@Override
	public String getPaymentErrorString() {
		return "Payment Error";
	}

	@Override
	public String getEnrollErrorString() {
		return "Enrollment number and name/surname mismatch";
	}
	
	@Override
	public String getNoAction() {
		return "No Action";
	}

	@Override
	public String getDeleteInsertionString() {
		return "Delete Insertion";
	}
}