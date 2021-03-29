package ptm.tji.server;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


//----------------------------------------------------------------------
@PersistenceCapable
public class OsobaP 
{
	
	
	@Persistent
	String imie;
	@Persistent
	String nazwisko;
	@Persistent
	String data_urodzenia;
	@Persistent
	String tele_st;
	@Persistent
	String tele_kom;
	@Persistent
	String email;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
//--------------------------------------------------------------------------------------	
	public OsobaP(String imie, String nazwisko, String data_urodzenia, String tele_st,
			 String tele_kom, String email)
	{
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.data_urodzenia = data_urodzenia;
		this.tele_st = tele_st;
		this.tele_kom = tele_kom;
		this.email = email;
	}
//---------------------------------------------------------------------------------------

	public String getImie()
	{
		return imie;
	}
	
	public void setImie(String imie)
	{
		this.imie = imie;
	}
	//---------------------------------------------------------
	public String getNazwisko()
	{
		return nazwisko;
	}
	
	public void setNazwisko(String nazwisko)
	{
		this.nazwisko = nazwisko;
	}
	//----------------------------------------------------------
	public String getDataUrodzenia()
	{
		return data_urodzenia;
	}
	
	public void setDataUrodzenia(String data_urodzenia)
	{
		this.data_urodzenia = data_urodzenia;
	}
	//----------------------------------------------------------
	public String getTeleSt()
	{
		return tele_st;
	}
	
	public void setTeleSt(String tele_st)
	{
		this.tele_st = tele_st;
	}
	//---------------------------------------------------------
	public String getTeleKom()
	{
		return tele_kom;
	}
	
	public void setTeleKom(String tele_kom)
	{
		this.tele_kom = tele_kom;
	}
	//---------------------------------------------------------
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	//----------------------------------------------------------
}
	