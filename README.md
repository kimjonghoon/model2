model2
============

BBS with JSP model 2 architecture 

How to install

1. $ git clone https://github.com/kimjonghoon/model2

2. Eclipse
   File - New - Dynamic Web Project - Project name: model2
   refer to http://www.java-school.net/jsp-pjt/Dynamic-Web-Project-Set-Up,
            http://www.java-school.net/jsp-pjt/bbs-model-2
   
3. Eclipse
   Project Explorer - model2 - Build Path - Configure Build Path...
   Libraries tab - Add External JARs - Add {TOMCAT_HOME}/lib/servlet-api.jar

4. {TOMCAT_HOME}/Catalina/localhost/model2.xml  
&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;Context
    docBase="{your path}/model2/WebContent"
    reloadable="true"&gt;
	&lt;Resource
		name="jdbc/jsppjt"
		auth="Container"
		type="javax.sql.DataSource"
		username="java"
		password="school"
		driverClassName="oracle.jdbc.driver.OracleDriver"
		url="jdbc:oracle:thin:@127.0.0.1:1521:XE" 
		maxActive="8"
		maxIdle="4" /&gt;
&lt;/Context&gt;                                                                             

4. database design 
   refer to http://www.java-school.net/jsp-pjt/database-design

have to edit
 
model2/src/log4j.xml 
&lt;param name="File"  value="{Full path of log file}"/&gt;
