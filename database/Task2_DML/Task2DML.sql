CREATE TABLE Employee (EmployeeID NUMBER(10),
						FirstName VARCHAR2(50),
						LastName VARCHAR2(50),
						Department VARCHAR2(50),
						Salary NUMBER(10));


INSERT ALL 
INTO Employee  (EmployeeID, FirstName, LastName, Department, Salary) VALUES (101 ,'John1', 'Doe1', 'HR', 20000)
INTO Employee  (EmployeeID, FirstName, LastName, Department, Salary) VALUES (102,'John2', 'Doe2', 'IT', 50000)
INTO Employee  (EmployeeID, FirstName, LastName, Department, Salary) VALUES (103,'John3', 'Doe3', 'CS', 40000)
INTO Employee  (EmployeeID, FirstName, LastName, Department, Salary) VALUES (104,'John4', 'Doe4', 'IT', 10000)
INTO Employee  (EmployeeID, FirstName, LastName, Department, Salary) VALUES (105,'John5', 'Doe5', 'ZX', 30000)
SELECT * FROM dual;


UPDATE Employee SET salary = 50000 WHERE EmployeeID = 105;


DELETE FROM Employee WHERE EmployeeID = 101;


SELECT * FROM Employee WHERE Department = 'IT';



SELECT
  EmployeeID,
  FirstName || ' ' || LastName AS FullName,
  Department,
  Salary
FROM Employee;
