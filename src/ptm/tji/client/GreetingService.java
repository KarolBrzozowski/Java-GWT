package ptm.tji.client;

import java.util.List;

import ptm.tji.shared.Osoba;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService 
{
	Boolean greetServer(Osoba person) throws IllegalArgumentException;
	List<Osoba> getOsoba() throws IllegalArgumentException;
	List<Osoba> fetchPage(int start, int length) throws IllegalArgumentException;
	Long fetchCount() throws IllegalArgumentException;
}