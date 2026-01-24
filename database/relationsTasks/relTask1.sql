CREATE TABLE player(
	id NUMBER PRIMARY KEY,
	name varchar(50) NOT NULL UNIQUE ,
	age NUMBER 
);


CREATE TABLE manager(
	id NUMBER PRIMARY KEY,
	name varchar(50) NOT NULL,
	salary NUMBER,
	UNIQUE(id, name)
);


CREATE TABLE managerv_2(
	id NUMBER PRIMARY KEY ,
	name varchar(50),
	age NUMBER 
);