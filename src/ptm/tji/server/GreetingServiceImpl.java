package ptm.tji.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import ptm.tji.client.GreetingService;
import ptm.tji.shared.Osoba;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	@Override
	public Boolean greetServer(Osoba person) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		//Zapis do bazy danych przy pomocy persist manager'a
		
		OsobaP person_persist = new OsobaP(
				person.getImie(),
				person.getNazwisko(),
				person.getDataUrodzenia(),
				person.getTeleSt(),
				person.getTeleKom(),
				person.getEmail()
				);
		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistent(person_persist);
		
		return true;
	}

	@Override
	public List<Osoba> getOsoba() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Query q = pm.newQuery("select from " + OsobaP.class.getName()); 
		
		@SuppressWarnings("unchecked")
		List<OsobaP> op = (List<OsobaP>) q.execute();
		
		List<Osoba> person = new ArrayList<Osoba>(op.size());
				for (OsobaP o : op) {
					person.add(new Osoba(
							o.getImie(),
							o.getNazwisko(),
							o.getDataUrodzenia(),
							o.getTeleSt(),
							o.getTeleKom(),
							o.getEmail()
							)
					);
				}
		
		
		return person;
	}

	@Override
	public List<Osoba> fetchPage(int start, int length)
			throws IllegalArgumentException {
		
		PersistenceManager persist_man = pmf.getPersistenceManager();
		Query query = persist_man.newQuery("select from "+ OsobaP.class.getName()); 
		query.setRange(start-1, start+length+1);
		@SuppressWarnings("unchecked")
		List<OsobaP> op = (List<OsobaP>) query.execute();
		
		List<Osoba> os = new ArrayList<Osoba>(op.size());
		for (OsobaP o: op){
			os.add(new Osoba(o.getImie(), o.getNazwisko(), o.getDataUrodzenia(), o.getTeleSt(), o.getTeleKom(), o.getEmail()));
		}
		
		return os;
	}

	@Override
	public Long fetchCount() throws IllegalArgumentException {
		PersistenceManager persist_man = pmf.getPersistenceManager();
		Query query = persist_man.newQuery("select count(p) from "+ OsobaP.class.getName() + " p");
		Long count = (Long) query.execute();
		return count;
	
	}
}
