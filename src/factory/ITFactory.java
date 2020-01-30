package factory;



public class ITFactory implements LanguageFactory{
	
	public ITFactory() {
		//Costruttore factory lingua italiana
		
	}
	
	@Override
	public String getWelcomeString() {
		return "Benevenuto nella schermata di login";
		
	}

	@Override
	public String getNameString() {
		return "Nome";
		
	}

	@Override
	public String getSurnameString() {
		return "Cognome";
		
	}

	@Override
	public String getViewOrders() {
		
		return "Vedi Ordini";
	}

	@Override
	public String getModifyProfile() {
		
		return "Modifica profilo";
	}

	@Override
	public String getMessagesString() {
		return "Messaggi";
	}

	@Override
	public String getYourOrdersString() {
		return "I tuoi ordini";
	}

	@Override
	public String getDetailsString() {
		return "Dettagli";
	}

	@Override
	public String getBackString() {
		return "Indietro";
	}

	@Override
	public String getOldPasswordString() {
		return "Inserisci Vecchia Password";
	}

	@Override
	public String getNewPasswordString() {
		return "Inserisci Nuova Password";
	}

	@Override
	public String getSubmitString() {
		return "Invia";
	}

	@Override
	public String getMessageString() {
		
		return "Messaggio";
	}

	@Override
	public String getFromString() {
		return "Da";
	}

	@Override
	public String getNewMessageString() {
		return "Nuovo messaggio";
	}

	@Override
	public String getBuyerString() {
		return "Compratore";
	}

	@Override
	public String getSellerString() {
		return "Venditore";
	}

	@Override
	public String getPriceString() {
		return "Prezzo";
	}

	@Override
	public String getItemString() {
	 return "Oggetto";
	}

	@Override
	public String getDateString() {
		return "Data";
	}

	@Override
	public String getWrongData() {
		return "Dati Errati";
	}

	@Override
	public String getErrorString() {
		return "Errore";
	}

	@Override
	public String getWrongOldPsw() {
		return "Vecchia Password errata";
	}

	@Override
	public String getRegister() {
		return "Registrati";
	}

	@Override
	public String getSearchText() {
		return "Inizia cercando un libro!";
	}

	@Override
	public String getSearchString() {
		return "Cerca";
	}

	@Override
	public String getStudQuestion() {
		return "Sei uno studente o una società?";
	}

	@Override
	public String getEnrollString() {
		return "Numero di matricola";
	}

	@Override
	public String getCheckString() {
		return "Inserisci nuovamente password";
	}

	@Override
	public String getLogoString() {
		return "Logo società";
	}

	@Override
	public String getUploadString() {
		return "Upload";
	}

	@Override
	public String getCheckError() {
		return "Le password non corrispondono";
	}

	@Override
	public String getStudString() {
		return "Studente";
	}

	@Override
	public String getCompanyString() {
		return "Società";
	}
	
	@Override
	public String getFiltersString() {
		return "Filtri";
	}

	@Override
	public String getUniversityString() {
		return "Università";
	}

	@Override
	public String getCityString() {
		return "Città";
	}

	@Override
	public String getSubjectString() {
		return "Materia";
	}

	@Override
	public String getBooksString() {
		return "Libri";
	}

	@Override
	public String getNotesString() {
		return "Appunti";
	}

	@Override
	public String getClearString() {
		return "Ripristina";
	}
	
	@Override
	public String getApplyString() {
		return "Applica";
	}

	@Override
	public String getOrderString() {
		return "Ordine";
	}

	@Override
	public String getNewerString() {
		return "Più nuovo";
	}

	@Override
	public String getOlderString() {
		return "Più vecchio";
	}

	@Override
	public String getNearestString() {
		return "Più vicino";
	}

	@Override
	public String getFarthestString() {
		return "Più lontano";
	}
	
	@Override
	public String getTitleString() {
		return "Titolo";
	}
	
	@Override
	public String getNewInsertionText() {
		return "O crea la tua inserzione!";
	}

	@Override
	public String getNewInsertionString() {
		return "Nuova inserzione";
	}

	@Override
	public String getDescriptionString() {
		return "Descrizione";
	}

	@Override
	public String getBuyString() {
		return "Compra";
	}

	@Override
	public String getConfirmationString() {
		return "Conferma";
	}

	@Override
	public String getConfirmationBuyText() {
		return "Vuoi davvero comprare l'oggetto?";
	}

	@Override
	public String getYesString() {
		return "Si";
	}

	@Override
	public String getNoString() {
		return "No";
	}

	@Override
	public String getImagesString() {
		return "Immagini";
	}

	@Override
	public String getInsertString() {
		return "Inserisci";
	}

	@Override
	public String getNoLogoString() {
		return "Nessun Logo";
	}

	@Override
	public String getPaidString() {
		return "Pagato";
	}

	@Override
	public String getPayString() {
		return "Paga";
	}

	@Override
	public String getPaymentErrorString() {
		return "Errore con il pagamento";
	}

	@Override
	public String getEnrollErrorString() {
		return "Matricola e nome/cognome non corrispondono";
	}

	@Override
	public String getNoAction() {
		return "Nessuna azione";
	}

	@Override
	public String getDeleteInsertionString() {
		return "Elimina inserzione";
	}
}
