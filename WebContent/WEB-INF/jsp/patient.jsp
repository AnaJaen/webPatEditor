<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<spring:url value="/resources/main.css" var="mainCSS" />
		<link href="${mainCSS}" rel="stylesheet"/>
		<title>Patientenliste</title>
	</head>
	<body>
	<br>
	<a href="index.jsp" >Index</a>
	<br><br>
		  <div align="center" style="font-family:calibri;">
	            <h1>Patientenliste</h1>
	            <table border="1">
	                <tr>
	                	<!-- überschriften der Tabelle -->
	               		<th>ID</th>
	               		<th>SVNR</th>
		                <th>Adresse</th>
		                <th>Geburtsdatum</th>
		                <th>Geschlecht</th>
		                <th>Vorname</th>
		                <th>Nachname</th>
		                <th>Krankenkasse</th>
		                <th>Bearbeitung</th>
	                </tr>
	                
	                <!-- der Name der Attribut listPatient (gelesen vom Model) muss man in der Controllermethode hinzugefügt -Linie66-.
	                Diese Attribut ist in der variable datenPt gespeischert. Die Werte von der verschiedenen Patientenobjekt sind 
	                in jede zeile/field dargestellt)-->
	                <c:forEach var="datenPt" items="${listPatient}" varStatus="status">
	                <tr>
	                	<!-- Atributte von Patient.java zugegriefen -->
	                    <td>${datenPt.id}</td>
	                    <td>${datenPt.svnr}</td>
	                    <td>${datenPt.adresse}</td>
	                    <td>${datenPt.geburtsdatum}</td>
	                    <td>${datenPt.geschlecht}</td>
	                    <td>${datenPt.vorname}</td>
	                    <td>${datenPt.nachname}</td>
	                    <td>${datenPt.krankenkasse}</td>
	                    <td>
	                    	<form:form action="patientForm" method="POST">
	                    		<input type="hidden" name="nr" value="${datenPt.id}"/>
	                    		<input type="submit" value="Edit" class="link"/>
	                    	</form:form>
	                    	<form:form action="patientInfo" method="POST">
		                    	<input type="hidden" name="nr" value="${datenPt.id}"/>
		                    	<input type="submit" value="Delete" class= "link"/>
	                    	</form:form>
	                    </td>
	                </tr>
	                </c:forEach>             
	            </table>
	            <br>
	            <br>
	            
	            <!-- Button um neue Patienten hinzuzufügen. 
	            Request-Methode patientForm. In der Kontrollerklasse AppController befindet sich der Methode.java hinzugefügt -Linie 74- -->
	            <form:form action="patientForm" method="POST">
	            	<input type="hidden" name="nr" value="-1"/>
	            	<input type="submit" value="New Patient"/>
	            </form:form>
	            <br>
	        </div>
	</body>
</html>