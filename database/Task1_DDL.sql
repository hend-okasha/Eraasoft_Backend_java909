CREATE TABLE Manger(id VARCHAR2(10),
					name VARCHAR2(50),
					age Number,
					birth_date DATE,
					address VARCHAR2(50));


ALTER TABLE Manger DROP COLUMN address;


ALTER TABLE Manger ADD ( city_address VARCHAR2(50),
						 street VARCHAR2(50) );


ALTER TABLE Manger RENAME COLUMN name TO full_name;


ALTER TABLE MANGER READ ONLY;




CREATE TABLE OWNER AS SELECT id, full_name, birth_date FROM MANGER;

RENAME MANGER TO MASTER;


DROP MASTER, OWNER;


