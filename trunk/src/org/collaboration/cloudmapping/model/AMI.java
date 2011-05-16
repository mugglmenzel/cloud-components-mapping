package org.collaboration.cloudmapping.model;

import java.util.Date;

public class AMI {
	
	
	String name = ""; //e.g. BitNami Tracks Stack 1.7.3-0 EBS (Windows Server 2008.r1.sp2) 
	
	
	String us_East_AMI_Id = ""; // e.g. ami-82ea18eb
	String europe_AMI_ID = "";
	String us_West_AMI_ID = "";
	String ap_Southeast_AMI_ID = "";
	String ap_Northeast_AMI_ID = "";
	
	
	String webserverImpl = ""; //e.g. Apache, IIS
	
	String amiManifest = ""; // e.g. 979382823631/bitnami-tracks-1.7.3-0-windows-2008.r1.sp2-ebs
	
	String licence = ""; //e.g. public, paid
	
	String opSys = ""; //e.g. Microsoft Windows, Unix...
	
	String submittedBy = ""; //e.g. amazon-catalog@bitrock.com; Lance Larson (user could also submit AMIs 
	
	Date createdOn; //e.g. February 2, 2011 12:56 AM GMT
	
	Date lastUpdate; //

	String backedOn = ""; //EBS or S3; 
}
