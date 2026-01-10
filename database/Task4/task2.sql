SELECT REPLACE('database' , 'a', '@') AS DB FROM dual;


SELECT 'oldest file', REPLACE('oldest file' , 'old' , 'new') AS replaced FROM dual;


CREATE TABLE PRODUCT (
    product_name VARCHAR2(50)
);


INSERT ALL
    INTO PRODUCT (product_name) VALUES ('Laptop')
    INTO PRODUCT (product_name) VALUES ('Smartphone')
    INTO PRODUCT (product_name) VALUES ('Headphones')
SELECT * FROM dual;


SELECT Lpad(product_name, 15, '*') AS lpad FROM PRODUCT;


SELECT Rpad(product_name, 15, '#') AS lpad FROM PRODUCT;