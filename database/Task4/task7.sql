CREATE TABLE customers (
    full_name VARCHAR2(50)
);


INSERT ALL
    INTO customers (full_name) VALUES ('   Ahmed Ali   ')
    INTO customers (full_name) VALUES ('Sara Mohamed   ')
    INTO customers (full_name) VALUES ('   Omar Hassan')
    INTO customers (full_name) VALUES ('Mona #Hassan#')
    INTO customers (full_name) VALUES ('$Youssef$')
SELECT * FROM dual;


SELECT TRIM(full_name) AS trimmed_name
FROM customers;


SELECT RTRIM(full_name) AS rtrim_name
FROM customers;


SELECT TRIM(BOTH '$' FROM full_name) AS result1,
       TRIM(BOTH '#' FROM full_name) AS result2
FROM customers;


SELECT 'promotion', REPLACE('promotion', 'o', '0') AS replaced_word
FROM dual;


SELECT 'This is a basic course', REPLACE('This is a basic course', 'basic', 'advanced') AS replaced_sentence
FROM dual;



CREATE TABLE department (
    dept_name VARCHAR2(50)
);


INSERT ALL
    INTO department (dept_name) VALUES ('HR')
    INTO department (dept_name) VALUES ('IT')
    INTO department (dept_name) VALUES ('Sales')
SELECT * FROM dual;



SELECT LPAD(dept_name, 15, '*') AS lpad_name
FROM department;



SELECT RPAD(dept_name, 15, '-') AS rpad_name
FROM department;


SELECT TO_CHAR(SYSDATE, 'DD-MON-YYYY') AS formatted_date
FROM dual;



SELECT TO_CHAR(SYSDATE, 'Day, Month YYYY') AS formatted_date2
FROM dual;


SELECT TO_CHAR(12345.67, '99,999.99') AS formatted_number
FROM dual;


SELECT TO_CHAR(7500, 'FM$999,999.00') AS salary
FROM dual;


SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD HH24:MI:SS') AS date_time
FROM dual;



CREATE TABLE student (
    name  VARCHAR2(50),
    score NUMBER(3)
);



INSERT ALL
    INTO student (name, score) VALUES ('Ahmed', 95)
    INTO student (name, score) VALUES ('Sara', 88)
    INTO student (name, score) VALUES ('Omar', 74)
    INTO student (name, score) VALUES ('Mona', 82)
    INTO student (name, score) VALUES ('Youssef', 60)
SELECT * FROM dual;


SELECT
    name,
    score,
    CASE
        WHEN score >= 90 THEN 'A'
        WHEN score BETWEEN 80 AND 89 THEN 'B'
        WHEN score BETWEEN 70 AND 79 THEN 'C'
        ELSE 'F'
    END AS grade
FROM student;


SELECT
    name,
    score,
    CASE
        WHEN score >= 60 THEN 'Pass'
        ELSE 'Fail'
    END AS result
FROM student;


SELECT
    name,
    score,
    CASE
        WHEN score >= 90 THEN 'Excellent'
        WHEN score BETWEEN 80 AND 89 THEN 'Good'
        WHEN score BETWEEN 70 AND 79 THEN 'Average'
        ELSE 'Needs Improvement'
    END AS message
FROM student;


SELECT
    CASE TO_CHAR(SYSDATE, 'DY')
        WHEN 'MON' THEN 'Today is Monday'
        WHEN 'TUE' THEN 'Today is Tuesday'
        WHEN 'WED' THEN 'Today is Wednesday'
        WHEN 'THU' THEN 'Today is Thursday'
        WHEN 'FRI' THEN 'Today is Friday'
        WHEN 'SAT' THEN 'Today is Saturday'
        WHEN 'SUN' THEN 'Today is Sunday'
    END AS today_message
FROM dual;


SELECT name, score,
       DECODE(score,
              100, 'A',
              90,  'B',
              80,  'C',
              70,  'C',
              'F') AS grade
FROM student;



CREATE TABLE status_log (
    status_code CHAR(1)
);

INSERT ALL
    INTO status_log (status_code) VALUES ('N')
    INTO status_log (status_code) VALUES ('I')
    INTO status_log (status_code) VALUES ('C')
    INTO status_log (status_code) VALUES ('N')
SELECT * FROM dual;


SELECT status_code,
       DECODE(status_code,
              'N', 'New',
              'I', 'In Progress',
              'C', 'Completed',
              'Unknown') AS full_status
FROM status_log;



SELECT dept_name,
       DECODE(dept_name,
              'HR', 500,
              'IT', 1000,
              'Sales', 1500,
              300) AS bonus
FROM department;











