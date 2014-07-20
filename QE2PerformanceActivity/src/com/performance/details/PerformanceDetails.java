package com.performance.details;


//http://localhost:8080/QE2PerformanceActivity/QE2PerformanceApplication/api
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConfigurationException;

import com.performance.jaxbs.BridgePerformanceData;
import com.performance.jaxbs.Report;
import com.performance.jaxbs.ReportDetails;
import com.performance.utilities.DBDetails;


@Path("/api")
public class PerformanceDetails {
	
	 private static final Logger LOG = Logger.getLogger(PerformanceDetails.class.getName());   	 
	 
	   @GET       
       @Produces(MediaType.APPLICATION_XML)	   
	   public BridgePerformanceData getRoot(){			
		   return getPerformanceDetails();		   
		
	   }	 
	   
	   /**
	    * Constructs the data for the xml
	    * @return xml
	    */
       private BridgePerformanceData getPerformanceDetails(){ 
    	   Connection con = null;
           try {               	   
				BridgePerformanceData crossingData = new BridgePerformanceData();
				List<Report> reportList =  new java.util.ArrayList<Report>();
				DBDetails dbDetails = new DBDetails();
				con = dbDetails.getDBConnection();
				java.util.List<ReportDetails> reportDetailsList = null;  		
				reportList.add(getReport("BRIDGE-HOURLY-ACTIVITY", "CROSSING ACTIVITY", dbDetails, con, reportDetailsList)); 
				con.close();
				crossingData.setReport(reportList);
                return crossingData;   //return the populated xml;
           } catch (java.lang.Exception ex) {
               ex.printStackTrace();
               throw new RuntimeException(ex);
           }  
         
       }
              
       /**
        * Constructs the data for the xml
        * @param queryName
        * @param reportType
        * @param reportDetailsList
        * @return
     * @throws SQLException 
        */
       private Report getReport(String queryName, String reportType, DBDetails dbDetails, Connection con, java.util.List<ReportDetails> reportDetailsList) throws SQLException{
			LOG.info(reportType+"   query start");
			String sqlQuery = dbDetails.getSqlQuery(queryName);
			ResultSet resultSet = null;
			Report report = new Report();	
			try {
				resultSet = getResultSet(con, sqlQuery);
				report.setType(reportType);  
				reportDetailsList = getReportDetailsList(resultSet,reportType);
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
			}catch (DatatypeConfigurationException e) {				
				e.printStackTrace();
			} catch (SQLException e) {				
				e.printStackTrace();
			}finally{
				con.close();
			}
			report.setReportDetails(reportDetailsList);
			LOG.info(reportType+"   query end");
			return report;  	   
       }
       
            
       // get result list  usisng sqlQuery
       /**
        * 
        * @param con
        * @param sqlQuery
        * @return
        * @throws SQLException
        * @throws ClassNotFoundException
        */
       private static  ResultSet getResultSet(Connection con, 
   			String sqlQuery) throws SQLException, ClassNotFoundException {		   	
   		Statement stmt;
   		stmt = con.createStatement();
   		ResultSet rs = stmt.executeQuery(sqlQuery);	   		
   		return rs;
    	}   
       
       
       // get ReportDetails list    
       /**
        * 
        * @param rs
        * @param reportType
        * @return
        * @throws DatatypeConfigurationException
        * @throws SQLException
        */
       private java.util.List<ReportDetails> getReportDetailsList(ResultSet rs, String reportType) throws DatatypeConfigurationException, SQLException{
    	   
          	java.util.List<ReportDetails> reportDetailsList = new java.util.ArrayList<ReportDetails>();
          	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm");	
          	int count = 1;
   	    	while (rs.next()){
   			       	if(reportType.equalsIgnoreCase("CROSSING ACTIVITY")){
	   			       	if(count == 1){				        	
				        	ReportDetails rD = new ReportDetails();
				        	rD.setGateNumber("Gate Number");
				        	rD.setVehicleType("Vehicle Type"); 
							rD.setDirection("Direction");
							rD.setVrn("Vehicle Registration Number");
							rD.setCrossingDateAndTime(dateFormat.format(new Date()));
			       	      	reportDetailsList.add(rD);
				        }
			       		       
						ReportDetails  reportDetails = new ReportDetails();	  
						reportDetails.setGateNumber(rs.getString("gate_number"));
						reportDetails.setVehicleType(rs.getString("vehicle_type")); 
						reportDetails.setDirection(rs.getString("direction"));
						reportDetails.setVrn(rs.getString("vrn"));
						reportDetails.setCrossingDateAndTime(dateFormat.format(rs.getDate("crossing_datetime")));
						reportDetailsList.add(reportDetails);   					       
   			       	}
   			       	count++;
   	        }
           	return reportDetailsList;
          }
  
} 