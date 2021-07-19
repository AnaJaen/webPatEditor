<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Patient</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
      <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script> 
      <script>
          $(function() {
          $("#datepicker").datepicker({dateFormat: "dd.mm.yy"});
          });
      </script>
   </head>
   
   <body>
    <div style="font-family:calibri;">
      <h2>Patient</h2>
      <form:form  modelAttribute="patientObjekt" method = "POST" action = "patientEdit">
         <table>
            <tr>
               <td><form:label path = "id">PatientID</form:label></td>
               <td><form:input path = "id" /></td> <!-- diese Path-Namen müssen einstimmen mit den Attributnamen in Patient.java -->
            </tr>
            <tr>
               <td><form:label path = "svnr">SVNR</form:label></td>
               <td><form:input path = "svnr" /></td>
            </tr>
            <tr>
               <td><form:label path = "adresse">Adresse</form:label></td>
               <td><form:input path = "adresse" /></td>
            </tr>
           	<tr>
           		<td><form:label path="geburtsdatum">Geburtsdatum</form:label></td>
           		<td><form:input id="datepicker" path="geburtsdatum"/></td>
           		
           	</tr>
            <tr>
               <td><form:label path = "geschlecht">Geschlecht</form:label></td>
               
               <td>
               		<form:select path="geschlecht">
               			<form:options items="${geschlechtList}"/>
               		</form:select>
               </td>
            </tr>
            <tr>
               <td><form:label path = "vorname">Vorname</form:label></td>
               <td><form:input path = "vorname" /></td>
            </tr>
            <tr>
               <td><form:label path = "nachname">Nachname</form:label></td>
               <td><form:input path = "nachname" /></td>
            </tr>
            <tr>
               <td><form:label path = "krankenkasse">Krankenkasse</form:label></td>
               <td><form:input path = "krankenkasse" /></td>
            </tr>

            <tr>
               <td colspan = "2">
               </td>
            </tr>
            <tr>
            	<td>
            		<form:form>
            			<input type="hidden" name="nr" value="${patientObjekt.id}"/>
            			<input type="submit" value="Save"/>
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
	