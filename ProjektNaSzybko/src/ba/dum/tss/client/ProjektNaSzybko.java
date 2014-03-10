package ba.dum.tss.client;

import ba.dum.tss.shared.Stats;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

public class ProjektNaSzybko implements EntryPoint {

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your code "
			+ "connection and try again.";

	RemoteCalcAsync remoteCalc = GWT.create(RemoteCalc.class);

	public void onModuleLoad() {
		HorizontalPanel hp = new HorizontalPanel();
		HorizontalPanel hp1 = new HorizontalPanel();
		HorizontalPanel hp3 = new HorizontalPanel();
		// ========Split==============================================
		final TextBox l1 = new TextBox();
		final Button btn1 = new Button("SPLIT!!!");

		final Label wynik = new Label("");
		final Label error = new Label("");
		// ===========================================================

		// ========Last.fm rest api===================================
		final Button btn2 = new Button("Get album");
		final TextBox artist = new TextBox();
		artist.setText("");
		final TextBox album = new TextBox();
		album.setText("");

		final Label lblArtist = new Label("Podaj artyste");
		final Label lblAlbum = new Label("Podaj album");
		final Label lblErrorLast = new Label("");
		final Label lblResult = new Label("");
		final Label lblParsed = new Label("porque");
		// ===========================================================

		hp.add(l1);
		hp.add(btn1);
		hp.add(wynik);
		hp.add(error);
		
		hp1.add(lblArtist);
		hp1.add(artist);
		hp1.add(lblAlbum);
		hp1.add(album);
		hp1.add(btn2);
		hp1.add(lblResult);

		hp3.add(lblParsed);
		
		RootPanel.get("content").add(hp);

		RootPanel.get("stats").add(hp1);
		
		RootPanel.get("parsed").add(hp3);

		btn1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				remoteCalc.stats(l1.getText(), new AsyncCallback<Stats>() {
					
					@Override
					public void onSuccess(Stats result) {
						wynik.setText("Ilosc znakow= " + result.getChars()
								+ "| Ilosc slow= " + result.getWords());
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						error.setText(SERVER_ERROR);
						
					}
				});

			}
		});

		btn2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RequestBuilder req = new RequestBuilder(
						RequestBuilder.GET,
						"http://ws.audioscrobbler.com/2.0/?method=album.getInfo&api_key=0dedf5a696bdd3356c6e4e7077b2973b&album="
								+ album.getText()
								+ "&artist="
								+ artist.getText() + "");
				req.setCallback(new RequestCallback() {

					@Override
					public void onResponseReceived(Request request,
							Response response) {
						Document doc = XMLParser.parse(response.getText());
						String str = doc.getFirstChild().getFirstChild().toString();
						/*
						JSONValue jsonValue;
						JSONArray jsonArray;
						JSONObject jsonObject;
						JSONString jsonString;
						jsonValue = JSONParser.parseStrict(response.getText());
						// parseStrict is available in GWT >=2.1
						// But without it, GWT is just internally calling eval()
						// which is strongly discouraged for untrusted sources

						if ((jsonObject = jsonValue.isObject()) == null) {
						    Window.alert("Error parsing the JSON");
						    // Possibilites: error during download,
						    // someone trying to break the application, etc.
						}

						jsonValue = jsonObject.get("d"); // Actually, this needs
						                                 // a null check too
						if ((jsonArray = jsonValue.isArray()) == null) {
						    Window.alert("Error parsing the JSON");
						}

						jsonValue = jsonArray.get(0);
						if ((jsonObject = jsonValue.isObject()) == null) {
						    Window.alert("Error parsing the JSON");
						}

						jsonValue = jsonObject.get("Desc");
						if ((jsonString = jsonValue.isString()) == null) {
						    Window.alert("Error parsing the JSON");
						}

						Window.alert(jsonString.stringValue()); // Finally!
						*/
						lblParsed.setText(str);
						lblResult.setText(response.getText());

					}

					@Override
					public void onError(Request request, Throwable exception) {
						lblErrorLast.setText(request.toString());
						lblResult.setText(exception.getMessage());

					}
				});
				try {
					Request r = req.send();
					lblErrorLast.setText(r.toString() + "\n" + SERVER_ERROR);
				} catch (RequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
