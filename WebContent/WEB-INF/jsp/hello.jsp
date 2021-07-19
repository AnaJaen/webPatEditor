<%@ page contentType = "text/html" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
   <head>
	  <spring:url value="/resources/main.css" var="mainCSS" />
      <link href="${mainCSS}" rel="stylesheet"/>
      <title>Willkommen</title>
     
        <Script>
    	function CurrentDateHour() 
    	{

	      var uhrzeit = new Date()	
	      var hrs = uhrzeit.getHours();
	      var min = uhrzeit.getMinutes();	
	      var heute = new Date();
	      var m = new Array();
	      var d = new Array()
	      var an= heute.getYear();
	
	      m[0]="Jänner";  m[1]="Februar";  m[2]="März";
	      m[3]="April";   m[4]="Mai";  m[5]="Juni";
	      m[6]="Juli";    m[7]="August";   m[8]="September";
	      m[9]="Oktober";   m[10]="November"; m[11]="Dezember";
	
	      document.write("Es ist "+hrs+":"+min+"h. (");
	      document.write(heute.getDate());
	      document.write(". ");
	      document.write(m[heute.getMonth()]);
	      document.write(")");
	    }
  		</Script>
      
   </head>
   
   <body>
   	  <h1>WebApp Patienteneditor</h1>
      <h2>${message}</h2>
      
      <a href="patient" >zur Patientenlist</a>
      	
      <br><br><br>
      <script>
        CurrentDateHour();
 	  </script>
      
   </body>
</html>
