package ptm.tji.client;
import java.util.List;

import ptm.tji.shared.FieldVerifier;
import ptm.tji.shared.Osoba;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Karol1Gwt implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("send");
		final TextBox imieField = new TextBox();
		final TextBox nazwiskoField = new TextBox();
		final Label imieLabel = new Label();
		final Label nazwiskoLabel = new Label();
		final DatePicker urodzeniaDate = new DatePicker();
		final TextBox urodzeniaField = new TextBox();
		final Label urodzeniaLabel = new Label();
		final VerticalPanel telePanel = new VerticalPanel();
		final TextBox teleStac = new TextBox();
		final TextBox teleKom = new TextBox();
		final Label teleLabel = new Label();
		final Label teleLabel2 = new Label();
		final TextBox emailField = new TextBox();
		final Label emailLabel = new Label();
		final HTML responseLabel = new HTML();
		final HTML requestLabel = new HTML();
		
		final Label errorLabel = new Label();
		
		
		imieField.setText("Imie");
		nazwiskoField.setText("Nazwisko");
		imieLabel.setText("Imie");
		nazwiskoLabel.setText("Nazwisko");
		urodzeniaField.setText("");
		urodzeniaLabel.setText("Data urodzenia");
		teleStac.setText("223373921");
		teleKom.setText("888610226");
		telePanel.add(teleStac);
		teleLabel.setText("Telefon stac.");
		teleLabel2.setText("Telefon kom.");
		emailField.setText("kowalski@gmail.com");
		emailLabel.setText("e-mail");
		
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		
		//-------------------------------------------------------------------
		final CellTable<Osoba> tabela = new CellTable<Osoba>(6);
		
		TextColumn<Osoba> imieColumn = new TextColumn<Osoba>()
		{
			@Override
			public String getValue(Osoba object)
			{
				return object.getImie();
			}
		};
		
		tabela.addColumn(imieColumn);
		//--------------------------------------------------------------------
		TextColumn<Osoba> nazwiskoColumn = new TextColumn<Osoba>()
		{
			@Override
			public String getValue(Osoba object)
			{
				return object.getNazwisko();
			}
			
		};
		tabela.addColumn(nazwiskoColumn);
		//-------------------------------------------------------------------
		TextColumn<Osoba> dataUrodzeniaColumn = new TextColumn<Osoba>()
		{
			@Override
			public String getValue(Osoba object)
			{
				return object.getDataUrodzenia();
			}
			
		};
		tabela.addColumn(dataUrodzeniaColumn);
		//-------------------------------------------------------------------
		
		TextColumn<Osoba> teleStColumn = new TextColumn<Osoba>()
		{
			@Override
			public String getValue(Osoba object)
			{
				return object.getTeleSt();
			}
			
		};
		tabela.addColumn(teleStColumn);
		//-------------------------------------------------------------------
		TextColumn<Osoba> teleKomColumn = new TextColumn<Osoba>()
		{
			@Override
			public String getValue(Osoba object)
			{
				return object.getTeleKom();
			}
			
		};
		tabela.addColumn(teleKomColumn);
		//-------------------------------------------------------------------
		TextColumn<Osoba> emailColumn = new TextColumn<Osoba>()
		{
			@Override
			public String getValue(Osoba object)
			{
				return object.getEmail();
			}
			
		};
		tabela.addColumn(emailColumn);
		//-------------------------------------------------------------------
		SimplePager pager = new SimplePager();
		pager.setDisplay(tabela);
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(tabela);
		vPanel.add(pager);
		//-------------------------------------------------------------------
		
		final AsyncDataProvider<Osoba> provider = new AsyncDataProvider<Osoba>() 
		{
					
			@Override
			protected void onRangeChanged(HasData<Osoba> display) {
				final int start = display.getVisibleRange().getStart();
				int length = display.getVisibleRange().getLength();
				
				AsyncCallback<List<Osoba>> callback = new AsyncCallback<List<Osoba>>() 
				{
					
					@Override
					public void onFailure(Throwable cought) {
						Window.alert(cought.getMessage());
					}
					
					@Override
					public void onSuccess(List<Osoba> result) {
						updateRowData(start, result);
					}
				};
				greetingService.fetchPage(start, length, callback);
			}
		};
		
		provider.addDataDisplay(tabela);
		provider.updateRowCount(12, false);
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(imieLabel);
		RootPanel.get("snameFieldContainer").add(nazwiskoLabel);
		RootPanel.get("nameFieldContainer").add(imieField);
		RootPanel.get("snameFieldContainer").add(nazwiskoField);
		RootPanel.get("urodzeniaDate").add(urodzeniaLabel);
		RootPanel.get("urodzeniaDate").add(urodzeniaDate);
		RootPanel.get("telefonNumer").add(teleLabel);
		RootPanel.get("telefonNumer").add(telePanel);
		RootPanel.get("telefonNumer").add(teleStac);
		RootPanel.get("telefonNumer").add(teleLabel2);
		RootPanel.get("telefonNumer").add(teleKom);
		RootPanel.get("emailAdres").add(emailLabel);
		RootPanel.get("emailAdres").add(emailField);
		
		
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		RootPanel.get("responseFieldContainer").add(responseLabel);
		RootPanel.get("requestFieldContainer").add(responseLabel);
		
		RootPanel.get("tableFieldContainer").add(vPanel);

		
		

		// Focus the cursor on the name field when the app loads
		imieField.setFocus(true);
		imieField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Zapisanie na serwerze użytkowika:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Odpowiedz serwera:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			
			
			
			
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = imieField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Imię minimum cztery znaki");
					return;
				}
				
				
				Osoba personToServer = new Osoba(
						imieField.getText(),
						nazwiskoField.getText(),
						urodzeniaDate.getStyleName(),
						teleStac.getText(),
						teleKom.getText(),
						emailField.getText()
						);

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				requestLabel.setText(personToServer.toString());
				responseLabel.setText("");
				greetingService.greetServer(personToServer,
						new AsyncCallback<Boolean>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox.setText("Remote Procedure Call - Failure");
								serverResponseLabel.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								responseLabel.setText("Error");
								closeButton.setFocus(true);
							}

							public void onSuccess(Boolean result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML("ok");
								dialogBox.center();
								responseLabel.setText("Dane zostały pomyślnie zapisane :-)");
								closeButton.setFocus(true);
							}

						});
				
				greetingService.getOsoba(new AsyncCallback<List<Osoba>>() {
					
					@Override
					public void onFailure(Throwable cought) {
						responseLabel.setText("Fail");
					}
					
					@Override
					public void onSuccess(List<Osoba> result) {
						
						
						for (Osoba person : result) {
							
							
							responseLabel.setText(person.getImie().toString());
							
							
						}
						
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		imieField.addKeyUpHandler(handler);
	}
}