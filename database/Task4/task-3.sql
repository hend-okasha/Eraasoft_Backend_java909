SELECT email, hire_date, to_char(hire_date, 'DD-MON-YYYY') AS hire_date FROM EMPLOYEES;


SELECT email, hire_date, to_char(hire_date, 'Month YYYY') AS hire_date FROM EMPLOYEES;


SELECT email,salary, TO_CHAR(salary , '99,999.99') AS FORMATTED_NUMBER FROM EMPLOYEES;


SELECT email,salary, TO_CHAR(salary, 'FM$999,999.00') AS SALARY FROM EMPLOYEES;




