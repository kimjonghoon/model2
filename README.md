# MODEL 2 Board Program

## Database Design (Oracle)

	GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE TO java IDENTIFIED BY school;
	ALTER USER java DEFAULT TABLESPACE USERS;
	ALTER USER java TEMPORARY TABLESPACE TEMP;
	
	CONNECT java/school
	
	create table member (
	    email varchar2(60) PRIMARY KEY,
	    passwd varchar2(200) NOT NULL,
	    name varchar2(20) NOT NULL,
	    mobile varchar2(20)
	);
	
	create table board (
	    boardcd varchar2(20),
	    boardnm varchar2(40) NOT NULL,
	    boardnm_ko varchar2(40),
	    constraint PK_BOARD PRIMARY KEY(boardcd)
	);
	
	create table article (
	    articleno number,
	    boardcd varchar2(20),
	    title varchar2(200) NOT NULL,
	    content clob NOT NULL,
	    email varchar2(60),
	    hit number,    
	    regdate date,
	    constraint PK_ARTICLE PRIMARY KEY(articleno),
	    constraint FK_ARTICLE FOREIGN KEY(boardcd) REFERENCES board(boardcd)
	);
	
	create sequence SEQ_ARTICLE
	increment by 1
	start with 1;
	
	create table comments (
	    commentno number,
	    articleno number,    
	    email varchar2(60),    
	    memo varchar2(4000) NOT NULL,
	    regdate date, 
	    constraint PK_COMMENTS PRIMARY KEY(commentno)
	);
	
	create sequence SEQ_COMMENTS
	    increment by 1
	    start with 1;
	
	create table attachfile (
	    attachfileno number,
	    filename varchar2(50) NOT NULL,
	    filetype varchar2(30),
	    filesize number,
	    articleno number,
	    email varchar2(60),
	    constraint PK_ATTACHFILE PRIMARY KEY(attachfileno)
	);
	
	create sequence SEQ_ATTACHFILE
	increment by 1
	start with 1;
	
	CREATE TABLE authorities (
	  email VARCHAR2(60) NOT NULL,
	  authority VARCHAR2(20) NOT NULL,
	  CONSTRAINT fk_authorities FOREIGN KEY(email) REFERENCES member(email)
	);
	
	CREATE UNIQUE INDEX ix_authorities ON authorities(email, authority); 
	
	-- for test records  
	insert into board values ('free', 'Free', '자유게시판');
	insert into board values ('qna', 'Q and A', '묻고 답하기');
	insert into board values ('data', 'Data', '자료실');
	
	INSERT INTO member VALUES ('hong@gmail.org','1111','홍길동','010-1111-1111');
	INSERT INTO member VALUES ('im@gmail.org','1111','임꺽정','010-1111-2222');
	
	INSERT INTO authorities VALUES ('hong@gmail.org','ROLE_USER');
	INSERT INTO authorities VALUES ('hong@gmail.org','ROLE_ADMIN');
	INSERT INTO authorities VALUES ('im@gmail.org','ROLE_USER');
	
	commit;
	
	create table views (
		no number,
		articleNo number,
		ip varchar(60),
		yearMonthDayHour char(10),
		constraint PK_VIEWS PRIMARY KEY(no),
		constraint UNIQUE_VIEWS UNIQUE(articleNo, ip, yearMonthDayHour)
	);

	create sequence SEQ_VIEWS
		increment by 1
		start with 1;

## SetUp

1. $ git clone https://github.com/kimjonghoon/model2

2. Eclipse
   File - New - Dynamic Web Project - Project name: model2
   
   >> refer to http://www.java-school.net/jsp-pjt/Dynamic-Web-Project-Set-Up and http://www.java-school.net/jsp-pjt/bbs-model-2
   
3. Eclipse
   Project Explorer - model2 - Build Path - Configure Build Path...
   Libraries tab - Add External JARs - Add {TOMCAT_HOME}/lib/servlet-api.jar

4. Create {TOMCAT_HOME}/Catalina/localhost/model2.xml

		<?xml version="1.0" encoding="UTF-8"?>
		<Context docBase="{** your path **}/model2/WebContent" reloadable="true">
			<Resource
				name="jdbc/jsppjt"
				auth="Container"
				type="javax.sql.DataSource"
				username="java"
				password="school"
				driverClassName="oracle.jdbc.driver.OracleDriver"
				url="jdbc:oracle:thin:@127.0.0.1:1521:XE"
				maxActive="8"
				maxIdle="4" />
		</Context>
	                                                                             
you have to edit {** your path **}/model2/WebContent

5. copy ojdbc6.jar to {TOMCAT_HOME}/lib/

6. database design
	>> refer to http://www.java-school.net/jsp-pjt/database-design

7. Have to edit in model2/src/log4j.xml 
	>> &lt;param name="File" value="{** Full path of your log file **}"/&gt;

8. Tomcat restart and visit http://localhost:port/model2