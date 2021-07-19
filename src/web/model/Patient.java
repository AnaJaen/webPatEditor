package web.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Patient
{
	private int id;
	private int svnr;
	private String adresse;
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private LocalDate geburtsdatum;
	private String geschlecht;
	private String vorname;
	private String nachname;
	private String krankenkasse;

	
	public Patient() 
	{
		
	}

	public Patient(int id, int svnr, String adresse, LocalDate geburtsdatum, String geschlecht, String vorname,
			String nachname, String krankenkasse)
	{
		this.id = id;
		this.svnr = svnr;
		this.adresse = adresse;
		this.geburtsdatum = geburtsdatum;
		this.geschlecht = geschlecht;
		this.vorname = vorname;
		this.nachname = nachname;
		this.krankenkasse = krankenkasse;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getSvnr()
	{
		return svnr;
	}

	public void setSvnr(int svnr)
	{
		this.svnr = svnr;
	}

	public String getAdresse()
	{
		return adresse;
	}

	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}

	public LocalDate getGeburtsdatum()
	{
		return geburtsdatum;
	}

	public void setGeburtsdatum(LocalDate geburtsdatum)
	{
		this.geburtsdatum = geburtsdatum;
	}

	public String getGeschlecht()
	{
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht)
	{
		this.geschlecht = geschlecht;
	}

	public String getVorname()
	{
		return vorname;
	}

	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}

	public String getNachname()
	{
		return nachname;
	}

	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}

	public String getKrankenkasse()
	{
		return krankenkasse;
	}

	public void setKrankenkasse(String krankenkasse)
	{
		this.krankenkasse = krankenkasse;
	}

	@Override
	public String toString()
	{
		return "Patient [id=" + id + ", svnr=" + svnr + ", adresse=" + adresse + ", geburtsdatum=" + geburtsdatum
				+ ", geschlecht=" + geschlecht + ", vorname=" + vorname + ", nachname=" + nachname + ", krankenkasse="
				+ krankenkasse + "]";
	}
	
	
	
}

