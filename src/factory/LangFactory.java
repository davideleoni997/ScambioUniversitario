package factory;

public class LangFactory implements LanguageFactory {
	private int language;
	
	
	public LangFactory(int language) {
		super();
		this.language = language;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	
	
	
	@Override
	public String getWelcomeString() {
		if(language == 1)
			return "Benevenuto nella schermata di login";
		else
			return "Welcome to the login screen";
		
	}

	@Override
	public String getNameString() {
		if(language ==1)
			return "Nome";
		else
			return "Name";
	}

	@Override
	public String getSurnameString() {
		if(language ==1)
			return "Cognome";
		else
			return "Surname";
	}

	@Override
	public String getViewOrders() {
		if(language ==1)
			return "Vedi Ordini";
		else
			return "View orders";
	}

	@Override
	public String getModifyProfile() {
		if(language ==1)
			return "Modifica profilo";
		else
			return "Modify Profile";
	}

	@Override
	public String getMessagesString() {
		if(language ==1)
			return "Messaggi";
		else
			return "Messages";
	}

	@Override
	public String getYourOrdersString() {
		if(language ==1)
			return "I tuoi ordini";
		else
			return "Your orders";
	}

	@Override
	public String getDetailsString() {
		if(language ==1)
			return "Dettagli";
		else
			return "Details";
	}

	@Override
	public String getBackString() {
		if(language ==1)
			return "Indietro";
		else
			return "Back";
	}

	@Override
	public String getOldPasswordString() {
		if(language ==1)
			return "Inserisci Vecchia Password";
		else
			return "Insert Old password";
	}

	@Override
	public String getNewPasswordString() {
		if(language ==1)
			return "Inserisci Nuova Password";
		else
			return "Insert new Password";
	}

	@Override
	public String getSubmitString() {
		if(language ==1)
			return "Invia";
		else
			return "Submit";
	}

	@Override
	public String getMessageString() {
		if(language ==1)
			return "Messaggio";
		else
			return "Message";
	}

	@Override
	public String getFromString() {
		if(language ==1)
			return "Da";
		else
			return "From";
	}

	@Override
	public String getNewMessageString() {
		if(language ==1)
			return "Nuovo messaggio";
		else
			return "New message";
	}

	@Override
	public String getBuyerString() {
		if(language ==1)
			return "Compratore";
		else
			return "Buyer";
	}

	@Override
	public String getSellerString() {
		if(language ==1)
			return "Venditore";
		else
			return "Seller";
	}

	@Override
	public String getPriceString() {
		if(language ==1)
			return "Prezzo";
		else
			return "Price";
	}

	@Override
	public String getItemString() {
		if(language ==1)
			return "Oggetto";
		else
			return "Item";
	}

	@Override
	public String getDateString() {
		if(language ==1)
			return "Data";
		else
			return "Date";
	}

	@Override
	public String getWrongData() {
		if(language ==1)
			return "Dati Errati";
		else
			return "Wrong Data";
	}

	@Override
	public String getErrorString() {
		if(language ==1)
			return "Errore";
		else
			return "Error";
	}

	@Override
	public String getWrongOldPsw() {
		if(language ==1)
			return "Vecchia Password errata";
		else
			return "Wrong Old password";
	}

	@Override
	public String getRegister() {
		if(language ==1)
			return "Registrati";
		else
			return "Register";
	}

	@Override
	public String getSearchText() {
		if(language ==1)
			return "Inizia cercando un libro!";
		else
			return "Start by searching for a book!";
	}

	@Override
	public String getSearchString() {
		if(language ==1)
			return "Cerca";
		else
			return "Search";
	}

	@Override
	public String getStudQuestion() {
		if(language ==1)
			return "Sei uno studente o una società?";
		else
			return "Are you a student or a company?";
	}

	@Override
	public String getEnrollString() {
		if(language ==1)
			return "Numero di matricola";
		else
			return "Enrollment number";
	}

	@Override
	public String getCheckString() {
		if(language ==1)
			return "Inserisci nuovamente password";
		else
			return "Insert password again";
	}

	@Override
	public String getLogoString() {
		if(language ==1)
			return "Logo società";
		else
			return "Company logo";
	}

	@Override
	public String getUploadString() {
		return "Upload";
		
	}

	@Override
	public String getCheckError() {
		if(language ==1)
			return "Le password non corrispondono";
		else
			return "The passwords don't match";
	}

	@Override
	public String getStudString() {
		if(language ==1)
			return "Studente";
		else
			return "Student";
	}

	@Override
	public String getCompanyString() {
		if(language ==1)
			return "Società";
		else
			return "Company";
	}
	
	@Override
	public String getFiltersString() {
		if(language ==1)
			return "Filtri";
		else
			return "Filters";
	}

	@Override
	public String getUniversityString() {
		if(language ==1)
			return "Università";
		else
			return "University";
	}

	@Override
	public String getCityString() {
		if(language ==1)
			return "Città";
		else
			return "City";
	}

	@Override
	public String getSubjectString() {
		if(language ==1)
			return "Materia";
		else
			return "Subject";
	}

	@Override
	public String getBooksString() {
		if(language ==1)
			return "Libri";
		else
			return "Books";
	}

	@Override
	public String getNotesString() {
		if(language ==1)
			return "Appunti";
		else
			return "Notes";
	}

	@Override
	public String getClearString() {
		if(language ==1)
			return "Ripristina";
		else
			return "Clear";
	}
	
	@Override
	public String getApplyString() {
		if(language ==1)
			return "Applica";
		else
			return "Apply";
	}

	@Override
	public String getOrderString() {
		if(language ==1)
			return "Ordine";
		else
			return "Order";
	}

	@Override
	public String getNewerString() {
		if(language ==1)
			return "Più nuovo";
		else
			return "Newer";
	}

	@Override
	public String getOlderString() {
		if(language ==1)
			return "Più vecchio";
		else
			return "Older";
	}

	@Override
	public String getNearestString() {
		if(language ==1)
			return "Più vicino";
		else
			return "Nearest";
	}

	@Override
	public String getFarthestString() {
		if(language ==1)
			return "Più lontano";
		else
			return "Farthest";
	}
	
	@Override
	public String getTitleString() {
		if(language ==1)
			return "Titolo";
		else
			return "Title";
	}
	
	@Override
	public String getNewInsertionText() {
		if(language ==1)
			return "O crea la tua inserzione!";
		else
			return "Or create your own Insertion!";
	}

	@Override
	public String getNewInsertionString() {
		if(language ==1)
			return "Nuova inserzione";
		else
			return "New Insertion";
	}

	@Override
	public String getDescriptionString() {
		if(language ==1)
			return "Descrizione";
		else
			return "Description";
	}

	@Override
	public String getBuyString() {
		if(language == 1)
			return "Compra";
		else
			return "Buy";
	}

	@Override
	public String getConfirmationString() {
		if(language ==1)
			return "Conferma";
		else
			return "Confirmation";
	}

	@Override
	public String getConfirmationBuyText() {
		if(language ==1)
			return "Vuoi davvero comprare l'oggetto?";
		else
			return "Do you really want to buy the item?";
	}

	@Override
	public String getYesString() {
		if(language ==1)
			return "Si";
		else
			return "Yes";
	}

	@Override
	public String getNoString() {
		if(language ==1)
			return "No";
		else
			return "No";
	}

	@Override
	public String getImagesString() {
		if(language ==1)
			return "Immagini";
		else
			return "Images";
	}

	@Override
	public String getInsertString() {
		if(language ==1)
			return "Inserisci";
		else
			return "Insert";
	}

	@Override
	public String getNoLogoString() {
		if(language ==1)
			return "Nessun Logo";
		else
			return "No Logo";
	}

	@Override
	public String getPaidString() {
		if(language ==1)
			return "Pagato";
		else
			return "Paid";
	}

	@Override
	public String getPayString() {
		if(language ==1)
			return "Paga";
		else
			return "Pay";
	}

	@Override
	public String getPaymentErrorString() {
		if(language ==1)
			return "Errore con il pagamento";
		else
			return "Payment Error";
	}

	@Override
	public String getEnrollErrorString() {
		if(language ==1)
			return "Matricola e nome/cognome non corrispondono";
		else
			return "Enrollment number and name/surname mismatch";
	}

	@Override
	public String getNoAction() {
		if(language ==1)
			return "Nessuna azione";
		else
			return "No Action";
	}

	@Override
	public String getDeleteInsertionString() {
		if(language ==1)
			return "Elimina inserzione";
		else
			return "Delete Insertion";
	}
}