SOAMuleRepo
==================
Demo of the creating REST webservices using JaxB and consuming by the Mule server.

QE2PerformanceActivity[REST webservice implementation]
-------
1.	Create jaxb objects – using eclipse IDE– in project explorer , right click on the schema file[xsd] generate jaxb classes.
2.	Construct the REST xml from the database query result set.
3.	Deploy to the tomcat server. 
Which can be accessed from the 
Ipaddress:port/display-name/url-pattern/path
ip address – tomcats ipaddress and port
display-name – from web.xml -  
url-pattern – from web.xml -  
path -  from the invokation class     

To Run
-------
Deploy to the tomcat server[Export as war and drop in tomcats webapps] and in the browser you can access it with the url- QE2PerformanceActivity/QE2PerformanceApplication/api

performancepush[Mule implementation]
--------------
1.	Mule server periodically every pulls the xml using polling connector component, from the from the Rest webservice [with the path ip address /QE2PerformanceActivity/QE2PerformanceApplication/api] with the http component
2.	Sends the xml to the using  xsl-transformer component to be transformed as csv data file
3.	CSV file will be transferred to the client server using FTP connector component


To Run
-------
Run as mule server or deploy into the mule sever as Mule Deployable Archive war and copy into mule-standalone the directory of the mule-standalone/apps



Technologies
---------
- J2E
- Tomcat
- MySQL
- Eclipse 
- Mule Studio [CE]
- Mule Standalone


