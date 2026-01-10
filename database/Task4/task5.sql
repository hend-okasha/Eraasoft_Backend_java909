SELECT name, marks,    DECODE(
        FLOOR(marks/10),
        10, 'A',   
        9, 'A',   
        8, 'B',   
        7, 'C',    
        'F'        
    ) AS grade FROM STUDENTS;


CREATE TABLE ORDERS (
    status   CHAR(1)
);


INSERT ALL
    INTO ORDERS (status) VALUES ('P')
    INTO ORDERS (status) VALUES ('S')
    INTO ORDERS (status) VALUES ('D')
    INTO ORDERS (status) VALUES ('P')
    INTO ORDERS (status) VALUES ('S')
SELECT * FROM dual;
    

SELECT
    status,
    DECODE(status,
           'P', 'Pending',
           'S', 'Shipped',
           'D', 'Delivered',
           'Unknown') AS full_status
FROM ORDERS;

