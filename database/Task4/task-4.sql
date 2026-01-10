CREATE TABLE STUDENTS (
    name   VARCHAR2(50),
    marks  NUMBER(3)
);


INSERT ALL
    INTO STUDENTS (name, marks) VALUES ('Ahmed', 85)
    INTO STUDENTS (name, marks) VALUES ('Sara', 92)
    INTO STUDENTS (name, marks) VALUES ('Omar', 76)
    INTO STUDENTS (name, marks) VALUES ('Mona', 88)
    INTO STUDENTS (name, marks) VALUES ('Youssef', 90)
SELECT * FROM dual;


SELECT name, marks, CASE
        WHEN marks >= 90 THEN 'A'
        WHEN marks BETWEEN 80 AND 89 THEN 'B'
        WHEN marks BETWEEN 70 AND 79 THEN 'C'
        ELSE 'F'
    END AS grade
    FROM STUDENTS;