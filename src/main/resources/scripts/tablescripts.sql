CREATE TABLE USERLOGIN(ID SERIAL,USERNAME VARCHAR(10),PASSWORD VARCHAR(10), PRIMARY KEY (ID));
INSERT INTO  USERLOGIN(ID,USERNAME,PASSWORD) VALUES (1,"ADMIN","ADMIN");
CREATE TABLE ATSLOG (ID SERIAL,COMPANY VARCHAR(20),LOCATION VARCHAR(10), Delverydate date,NOTE TEXT);
INSERT INTO ATSLOG (ID,COMPANY ,LOCATION , Delverydate ,NOTE ) VALUES ('1','Kgisl','KOVAI','2019-11-09','TALANeX update')
