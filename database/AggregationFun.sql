SELECT AVG(salary) AS avg_salary
FROM employees;



SELECT COUNT(*) AS total_employees
FROM employees;


SELECT MAX(salary) AS max_salary
FROM employees;


SELECT MIN(salary) AS min_salary
FROM employees;


SELECT SUM(salary) AS total_salary
FROM employees;




SELECT department_id, AVG(salary) AS avg_salary
FROM employees
GROUP BY department_id;



SELECT job_id, COUNT(*) AS total_employees
FROM employees
GROUP BY job_id;


SELECT department_id, SUM(salary) AS total_salary
FROM employees
GROUP BY department_id
HAVING SUM(salary) > 50000;



SELECT AVG(commission_pct) AS avg_commission
FROM employees
WHERE commission_pct IS NOT NULL;


SELECT COUNT(*) AS high_salary_employees
FROM employees
WHERE salary > 10000;



SELECT job_id,
       MAX(salary) AS max_salary,
       MIN(salary) AS min_salary
FROM employees
GROUP BY job_id;




SELECT manager_id, SUM(salary) AS total_salary
FROM employees
GROUP BY manager_id;



SELECT department_id, job_id, SUM(salary) AS total_salary
FROM employees
GROUP BY department_id, job_id;


SELECT job_id, COUNT(*) AS employee_count
FROM employees
GROUP BY job_id
HAVING COUNT(*) > 5;


SELECT department_id,
       COUNT(*) AS total_employees,
       AVG(salary) AS avg_salary,
       MAX(salary) AS max_salary,
       MIN(salary) AS min_salary
FROM employees
GROUP BY department_id;



SELECT department_id
FROM employees
GROUP BY department_id
HAVING AVG(salary) > 8000
   AND COUNT(*) < 10;



SELECT department_id, SUM(salary) AS total_salary
FROM employees
GROUP BY department_id
ORDER BY SUM(salary) DESC
FETCH FIRST 1 ROW ONLY;


SELECT department_id,
       SUM(salary) AS Total_Salary,
       AVG(salary) AS Average_Salary
FROM employees
GROUP BY department_id;


