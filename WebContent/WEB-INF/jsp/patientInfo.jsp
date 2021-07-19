<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Patient</title>
   </head>
   
   <body>
    <div style="font-family:calibri;">
      <h2>Patient</h2>
      <p> Der Patient wird gelöscht werden. Sind Sie sicher?</p>
      <form:form  modelAttribute="patientObjekt" method = "POST" action = "patientLoeschen">
         <table>
            <tr>
               <td><form:label path = "id">PatientID</form:label></td>
               <td><form:label path = "id" />${patientObjekt.id}</td> <!-- diese Path-Namen müssen einstimmen mit den Attributnamen in Patient.java -->
            </tr>
            <tr>
               <td><form:label path = "svnr">SVNR</form:label></td>
               <td><form:label path = "svnr" />${patientObjekt.svnr}</td>
            </tr>
            <tr>
               <td><form:label path = "adresse">Adresse</form:label></td>
               <td><form:label path = "adresse" />${patientObjekt.adresse}</td>
            </tr>
           	<tr>
           		<td><form:label path="geburtsdatum">Geburtsdatum</form:label></td>
           		<td><form:label path="geburtsdatum"/>${patientObjekt.geburtsdatum}</td>
           		
           	</tr>
            <tr>
               <td><form:label path = "geschlecht">Geschlecht</form:label></td>
               <td><form:label path = "geschlecht" />${patientObjekt.geschlecht}</td>
            </tr>
            <tr>
               <td><form:label path = "vorname">Vorname</form:label></td>
               <td><form:label path = "vorname" />${patientObjekt.vorname}</td>
            </tr>
            <tr>
               <td><form:label path = "nachname">Nachname</form:label></td>
               <td><form:label path = "nachname" />${patientObjekt.nachname}</td>
            </tr>
            <tr>
               <td><form:label path = "krankenkasse">Krankenkasse</form:label></td>
               <td><form:label path = "krankenkasse" />${patientObjekt.krankenkasse}</td>
            </tr>
           
            <tr>
               <td colspan = "2">
               </td>
            </tr>
            <tr>
            	<td>
            		<form:form>
            			<input type="hidden" name="nr" value="${patientObjekt.id}"/>
            			<input type="submit" value="Delete"/>
            		</form:form>
            	</td>
            	<td>
            		<form:form method="GET" action="patient">
            			<input type="submit" value="Cancel"/>
            		</form:form>
            	</td>
            </tr>
         </table>  
      </form:form>
    </div>
   </body>
</html>
	