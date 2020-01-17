package factory;

public interface LanguageFactory {
	public static LanguageFactory getfactory(int lang) {
		if(lang==1)
			return new ITFactory();
		else
			return new ENFactory();
	}
	
	public abstract String getWelcomeString();
	
	public abstract String getNameString();
	
	public abstract String getSurnameString();
	
	public abstract String getViewOrders();
	
	public abstract String getModifyProfile();
	
	public abstract String getMessagesString();
	
	public abstract String getYourOrdersString();
	
	public abstract String getDetailsString();
	
	public abstract String getBackString();
	
	public abstract String getOldPasswordString();
	
	public abstract String getNewPasswordString();
	
	public abstract String getSubmitString();
	
	public abstract String getMessageString();
	
	public abstract String getFromString();
	
	public abstract String getNewMessageString();
	
	public abstract String getBuyerString();
	
	public abstract String getSellerString();
	
	public abstract String getPriceString();
	
	public abstract String getItemString();
	
	public abstract String getDateString();
	
	public abstract String getWrongData();
	
	public abstract String getErrorString();
	
	public abstract String getWrongOldPsw();
	
	public abstract String getRegister();
	
	public abstract String getSearchText();
	
	public abstract String getSearchString();
	
	public abstract String getStudQuestion();
	
	public abstract String getEnrollString();
	
	public abstract String getCheckString();
	
	public abstract String getLogoString();
	
	public abstract String getUploadString();
	
	public abstract String getCheckError();
	
	public abstract String getStudString();
	
	public abstract String getCompanyString();
	
	public abstract String getFiltersString();
	
	public abstract String getUniversityString();
	
	public abstract String getCityString();
	
	public abstract String getSubjectString();
	
	public abstract String getBooksString();
	
	public abstract String getNotesString();
	
	public abstract String getApplyString();
	
	public abstract String getClearString();
	
	public abstract String getOrderString();
	
	public abstract String getNewerString();
	
	public abstract String getOlderString();
	
	public abstract String getNearestString();
	
	public abstract String getFarthestString();
}
