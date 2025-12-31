-- 1. Find all employees where emp_id is between 100 and 105
SELECT * 
FROM employees 
WHERE EMPLOYEE_ID BETWEEN 100 AND 105;



-- 2. Find all employees with specific emp_id values (151, 152, 153, 154, 155)
SELECT * 
FROM employees 
WHERE EMPLOYEE_ID IN (151, 152, 153, 154, 155);



-- 3. Retrieve employees where first_name starts with 'P' or 'p'
SELECT * 
FROM employees 
WHERE UPPER(first_name) LIKE 'P%';



-- 4. Retrieve employees where first_name ends with 'A' or 'a'
SELECT * 
FROM employees 
WHERE UPPER(first_name) LIKE '%A';




-- 5. Retrieve employees where first_name contains 'A' or 'a'
SELECT * 
FROM employees 
WHERE UPPER(first_name) LIKE '%A%';

-- 6. Retrieve employees where the third character of first_name is 'e' or 'E'
SELECT * 
FROM employees 
WHERE UPPER(first_name) LIKE '__E%';


-- 7. Retrieve employees who don't have a manager assigned (manager_id is NULL)
SELECT * 
FROM employees 
WHERE manager_id IS NULL;


-- 8. Find all employees who have a manager assigned
SELECT * 
FROM employees 
WHERE manager_id IS NOT NULL;

-- 9. Insert a new employee without assigning a manager
INSERT INTO employees (EMPLOYEE_ID, first_name, last_name, email, hire_date, job_id, salary, manager_id, department_id)
VALUES (207, 'John', 'Doe', 'JDOE', TO_DATE('2025-01-01', 'YYYY-MM-DD'), 'IT_PROG', 5000, NULL, 60);


-- 10. Find employees who work in 'AD_VP' OR 'IT_PROG' job roles
SELECT * 
FROM employees 
WHERE job_id IN ('AD_VP', 'IT_PROG');



-- 11. Retrieve all employees sorted by last_name in ascending order
SELECT * 
FROM employees 
ORDER BY last_name ASC;



-- 12. Retrieve all employees sorted by hire_date in descending order
SELECT * 
FROM employees 
ORDER BY hire_date DESC;



-- 13. Sort by department (ascending) then by salary (descending) within each department
SELECT * 
FROM employees 
ORDER BY department_id ASC, salary DESC;



-- 14. Retrieve all employees with last_name in lowercase
SELECT EMPLOYEE_ID, first_name, LOWER(last_name) AS last_name, email, hire_date, job_id, salary
FROM employees;

-- 15. Retrieve all employees with first_name in uppercase
SELECT EMPLOYEE_ID, UPPER(first_name) AS first_name, last_name, email, hire_date, job_id, salary
FROM employees;

-- 16. Retrieve employees with first_name and last_name in title case
SELECT EMPLOYEE_ID,
       CONCAT(UPPER(LEFT(first_name, 1)), LOWER(SUBSTRING(first_name, 2))) AS first_name,
       CONCAT(UPPER(LEFT(last_name, 1)), LOWER(SUBSTRING(last_name, 2))) AS last_name,
       email, hire_date, job_id, salary
FROM employees;



-- 17. Find employees whose last_name is 'smith' (case-insensitive)
SELECT * 
FROM employees 
WHERE LOWER(last_name) = 'smith';