//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.26 at 03:23:27 PM BST 
//


package com.performance.jaxbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

  
@XmlRootElement(name="report")
    public  class Report {

        protected List<ReportDetails> reportDetails;       
        protected String type;
      
        public List<ReportDetails> getReportDetails() {
            if (reportDetails == null) {
            	reportDetails = new ArrayList<ReportDetails>();
            }
            return this.reportDetails;
        }                
        public void setReportDetails(List<ReportDetails> reportDetails) {
			this.reportDetails = reportDetails;
		}


		@XmlAttribute(name = "type")
        public String getType() {
            return type;
        }    
        public void setType(String value) {
            this.type = value;
        }

}
