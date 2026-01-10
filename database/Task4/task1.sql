CREATE TABLE EMP_TEST(name varchar2(20));

INSERT ALL
	INTO EMP_TEST (name) VALUES ('    ahmed ali   ')
	INTO EMP_TEST (name) VALUES ('ali   ')
	INTO EMP_TEST (name) VALUES ('    hend')
	INTO EMP_TEST (name) VALUES ('mohammed   ')
	INTO EMP_TEST (name) VALUES ('  marwa  ')
SELECT * FROM dual;


SELECT trim(name) AS trimmed_name FROM EMP_TEST;


SELECT LTRIM(name) AS LEADING_trim FROM EMP_TEST;


SELECT RTRIM(name) AS trailing_trim FROM EMP_TEST;


SELECT TRIM(BOTH 'm' FROM name) AS trim_m FROM EMP_TEST;

