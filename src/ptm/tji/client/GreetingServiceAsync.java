package ptm.tji.client;

import java.util.List;

import ptm.tji.shared.Osoba;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(Osoba person, AsyncCallback<Boolean> callback) 
		throws IllegalArgumentException;
	void getOsoba (AsyncCallback<List<Osoba>> callback) 
			throws IllegalArgumentException;
	void fetchPage(int stard, int length, AsyncCallback<List<Osoba>> callback);
	void fetchCount(AsyncCallback<Long> callback);
}
