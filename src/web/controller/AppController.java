package web.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.model.Patient;

//Durch die Annotation Controller ist die Klasse AppController als Controller definiert.
//Die Dispatcher-Servlet wird diese Kontroller-Klasse verwenden
@Controller
public class AppController
{
	//Verbindung der DB
	private final String driver = "org.sqlite.JDBC";
	private final String url = "jdbc:sqlite:C:\\Users\\soulm\\eclipse-workspace\\webPatienteneditor_6AKIF_JaenV\\patient.db";
	private Connection con = null;
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String printHello(Model model)
	{
		model.addAttribute("message", "Willkommen in der Patientenlist!");
		return "hello";
	}
	
	//Ladung der Patientendaten -Anzeige der Liste aller Patienten-
	@RequestMapping(value="/patient", method=RequestMethod.GET)
	public String listPatient(Model model)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Patient> listPt = new ArrayList<Patient>();
		try 
		{
			if (con == null)
			{
				Class.forName(driver);
				con = DriverManager.getConnection(url);
			}
			String sql = "SELECT ID, SVNR, ADRESSE, GEBURTSDATUM, GESCHLECHT, VORNAME, NACHNAME, KRANKENKASSE FROM patient";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				// Patient pt = new Patient(rs.getdatentyp("Variabeln vom Konstruktor genauso wie dort geschrieben sind")
				Patient pt = new Patient(rs.getInt("ID"), rs.getInt("SVNR"), rs.getString("ADRESSE"), LocalDate.parse(rs.getString("GEBURTSDATUM")),
										rs.getString("GESCHLECHT"), rs.getString("VORNAME"), rs.getString("NACHNAME"), rs.getString("KRANKENKASSE"));
				listPt.add(pt); //neue Patientenobjekt hinzugefügt
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		//Mit den Methode addAttribute  ausführen ("Name der Liste", Parameter der Patientliste -in der Linie 34 patient.jsp definiert-) 
		model.addAttribute("listPatient", listPt);
		return "patient"; //Return die Patientview
	}

	//Neuer Patient anlegen -wenn das Button gedrückt ist, wird dieser Methode ausgeführt -patient.jsp ab Linie 51-
	//cuando se lanza patientForm se devuelve el request con la vsta de PatientEdit. desde allí se puede guardar 
	//nuevo paciente  o cancelar la acción
	@RequestMapping(value="/patientForm",method=RequestMethod.POST)
	public ModelAndView getPatient(ModelAndView model, @RequestParam("nr") int id)
	{
		Patient patient = new Patient();
		if (id == -1) 
		{
			patient.setId((int)(Math.random()*90000)+10000);
			patient.setGeburtsdatum(LocalDate.of(2002, 1, 1));
		}
		else
		{
			try
			{
				if (con == null)
				{
					Class.forName(driver);
					con = DriverManager.getConnection(url);
				}
				String sql = "SELECT ID, SVNR, ADRESSE, GEBURTSDATUM, GESCHLECHT, VORNAME, NACHNAME, KRANKENKASSE " 
								+ "FROM patient WHERE ID=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) 
				{
					patient = new Patient(rs.getInt("ID"), rs.getInt("SVNR"), rs.getString("ADRESSE"), LocalDate.parse(rs.getString("GEBURTSDATUM")),
										rs.getString("GESCHLECHT"), rs.getString("VORNAME"), rs.getString("NACHNAME"), rs.getString("KRANKENKASSE"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		model.addObject("patientObjekt", patient);
		model.addObject("geschlechtList", Arrays.asList("WEIBLICH", "MÄNNLICH"));
		model.setViewName("patientEdit");
		return model;
	}
	
	
	@RequestMapping(value="/patientEdit", method=RequestMethod.POST)
	public String editPatient(@ModelAttribute("patientObjekt")Patient patient, Model model, @RequestParam("nr") int id)
	{
		try
		{
			//zuerst die DB-Connection überprüfen
			if (con == null)
			{
				Class.forName(driver);
				con = DriverManager.getConnection(url);
			}
			//Die alte Patient_ID kann ich im PreparedStatement setzen.
			String sql = "SELECT ID FROM patient WHERE ID=?"; 
			PreparedStatement pstmt0 = con.prepareStatement(sql);
			pstmt0.setInt(1, id);
			ResultSet rs = pstmt0.executeQuery();
			
			
			PreparedStatement pstmt = null;
			if (!rs.isBeforeFirst())
			{	
				
			//neue Daten übergeben (falls keine gibt´s), sonst bestehende Daten aktuallisieren (ändern)
			sql =  "INSERT INTO patient (ID, SVNR, ADRESSE, GEBURTSDATUM, GESCHLECHT, VORNAME, NACHNAME, KRANKENKASSE) " 
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			} 
			else
			{
				sql ="UPDATE patient SET ID=?, SVNR=?, ADRESSE=?, GEBURTSDATUM=?, GESCHLECHT=?, VORNAME=?, NACHNAME=?, KRANKENKASSE=? "
						    + "WHERE ID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(9, id);
			}
			// in dem pstmt die verschiedene parameter (?) setzen -Reihenfolge respektieren-)
			pstmt.setInt(1, patient.getId());
			pstmt.setInt(2, patient.getSvnr());
			pstmt.setString(3, patient.getAdresse());
			pstmt.setString(4, patient.getGeburtsdatum().toString());
			pstmt.setString(5, patient.getGeschlecht());
			pstmt.setString(6, patient.getVorname());
			pstmt.setString(7, patient.getNachname());
			pstmt.setString(8, patient.getKrankenkasse());
			pstmt.executeUpdate();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(patient.getGeschlecht());
		return "redirect:patient";
	}
	
	//Patient löschen
		@RequestMapping(value="/patientInfo", method=RequestMethod.POST)
		public ModelAndView showPatient(ModelAndView model, @RequestParam("nr") int id)
		{
			Patient patient = new Patient();
				try
				{
					if (con == null)
					{
						Class.forName(driver);
						con = DriverManager.getConnection(url);
					}
					String sql = "SELECT ID, SVNR, ADRESSE, GEBURTSDATUM, GESCHLECHT, VORNAME, NACHNAME, KRANKENKASSE " 
							+ "FROM patient WHERE ID=?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, id);
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) 
					{
						patient = new Patient(rs.getInt("ID"), rs.getInt("SVNR"), rs.getString("ADRESSE"), 
											LocalDate.parse(rs.getString("GEBURTSDATUM")), rs.getString("GESCHLECHT"), 
											rs.getString("VORNAME"), rs.getString("NACHNAME"), rs.getString("KRANKENKASSE"));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			model.addObject("patientObjekt", patient);
			model.setViewName("patientInfo");
			return model;
		}
		
		@RequestMapping(value="/patientLoeschen", method=RequestMethod.POST)
		public String deletePatient (Model model, @RequestParam("nr") int id)
		{
			try
			{
				if (con == null)
				{
					Class.forName(driver);
					con = DriverManager.getConnection(url);
				}
				String sql = "DELETE FROM patient WHERE ID=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return "redirect:patient";
		}
	
}
