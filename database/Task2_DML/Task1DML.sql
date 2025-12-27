CREATE TABLE DOCTOR (
	id NUMBER(10),
	name VARCHAR2(50),
	salary NUMBER(6),
	address VARCHAR2(50));



INSERT ALL
INTO DOCTOR (id, name, salary, address) VALUES (1, 'Hend', 7000, 'Tanta')
INTO DOCTOR (id, name, salary, address) VALUES (2, 'Hager', 8000, 'Cairo')
INTO DOCTOR (id, name, salary, address) VALUES (3, 'Menna', 7000, 'Tanta')
INTO DOCTOR (id, name, salary, address) VALUES (4, 'Ali', 6000, 'Alex')
INTO DOCTOR (id, name, salary, address) VALUES (5, 'Ahmed', 9000, 'Cairo')
INTO DOCTOR (id, name, salary, address) VALUES (6, 'Omar', 8000, 'Cairo')
INTO DOCTOR (id, name, salary, address) VALUES (7, 'Fatma', 9000, 'Tanta')
INTO DOCTOR (id, name, salary, address) VALUES (8, 'Sara', 6000, 'Alex')
INTO DOCTOR (id, name, salary, address) VALUES (9, 'Ali', 7000, 'Tanta')
INTO DOCTOR (id, name, salary, address) VALUES (10, 'Mohammed', 9000, 'Cairo')
SELECT * FROM dual;



UPDATE DOCTOR SET SALARY = 20000 WHERE id = 3;


DELETE FROM DOCTOR WHERE id = 9;


SELECT name || ' ' || salary AS name_with_salary FROM doctor;


SELECT salary, salary*12 AS annual_salary FROM DOCTOR;


SELECT * FROM DOCTOR 
WHERE salary IN (7000, 1000, 2000);



RENAME DOCTOR TO PRD_DOCTOR;


