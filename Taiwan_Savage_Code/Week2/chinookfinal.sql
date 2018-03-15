--Chinook Scripts
--2.1--
-- 1 --
   SELECT * 
   FROM EMPLOYEE;
   
   
-- 2 --
   SELECT * 
   FROM employee
   WHERE LASTNAME = 'King';
-- 3 --
    Select * 
    FROM employee
    Where FIRSTNAME = 'Andrew' 
    AND
    REPORTSTO is NULL;
    
--2.2--
-- 1
    SELECT * 
    FROM ALBUM
    ORDER BY TITLE DESC;
    
-- 2
    SELECT firstname 
    FROM customer
    ORDER BY city;
    
-- 2.3
-- 1
    INSERT INTO GENRE (genreid,name)
    VALUES (26, 'Haus');
    
    INSERT INTO GENRE (genreid,name)
    VALUES (27, 'Trap');
    
    Select * 
    FROM GENRE;
    
-- 2
    INSERT INTO EMPLOYEE (employeeid,lastname,firstname,title,reportsto,birthdate,hiredate,address,city,state,country,postalcode,
    phone,fax,email)
    VALUES(1994,'Savage','Taiwan','Mr.',NULL,DATE '1994-04-28', DATE '2018-02-26','13 Fake Way',
    'Reston','VA','USA', 19960,99999999,9999999999,'tai@gmail.com');
    
    INSERT INTO EMPLOYEE (employeeid,lastname,firstname,title,reportsto,birthdate,hiredate,address,city,state,country,postalcode,
    phone,fax,email)
    VALUES(1994,'Savage2','Taiwan2','Mr.',NULL,DATE '1994-04-28', DATE '2018-02-26','13 Fake Way',
    'Reston','VA','USA', 19960,99999999,9999999999,'tai@gmail.com');
    
    Select * 
    FROM employee;
    
-- 3
    INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'Tai','Sav','Revature','13000 Revature Way','Reston','VA','USA',19999,99999999,99999999999,'tai@fake.com'
    ,2);
    
     INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'Tai2','Sav2','Revature','13000 fake Way','Reston','VA','USA',19999,99999999,99999999999,'tai@fake.com'
    ,3);
    
    commit;
    
    SELECT *
    FROM CUSTOMER;
    
--2.4 
-- 1
    UPDATE CUSTOMER
    SET firstname = 'Robert', lastname = 'Walter'
    WHERE firstname = 'Aaron' AND lastname = 'Mitchell';
    
    Select *
    FROM customer;

-- 2
    UPDATE ARTIST
    SET name = 'CCR'
    WHERE name='Creedence Clearwater Revival';
    
    SELECT *
    FROM ARTIST
    WHERE name 
    LIKE 'C%';
    
-- 5
-- 1
    SELECT *
    FROM INVOICE
    where BILLINGADDRESS
    LIKE 'T%';
    
-- 2.6
-- 1
    SELECT *
    FROM INVOICE
    WHERE total
    BETWEEN 15
    AND
    50;
    
-- 2
    SELECT * 
    FROM employee
    WHERE hiredate 
    BETWEEN DATE '2003-06-01' 
    AND
    DATE '2004-03-01';
    
-- 2.7
-- 1
ALTER TABLE InvoiceLine DROP CONSTRAINT FK_InvoiceLineInvoiceId;
ALTER TABLE Invoice DROP CONSTRAINT FK_INVOICE_CUSTOMERID;

ALTER TABLE InvoiceLine ADD CONSTRAINT FK_InvoiceLineInvoice_Id
    FOREIGN KEY (InvoiceId) REFERENCES Invoice (InvoiceId) ON DELETE CASCADE ;
    

ALTER TABLE Invoice ADD CONSTRAINT FK_Invoice_Customer_Id
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId) ON DELETE CASCADE ;

    DELETE 
    FROM CUSTOMER
    WHERE firstname = 'Robert'
    AND
    lastname = 'Walter';
    
--3
-- 3.1
-- 1

Create OR REPLACE function get_current_time
RETURN TIMESTAMP IS l_systimestamp TIMESTAMP;
BEGIN 
    SELECT systimestamp
    INTO l_systimestamp
    FROM DUAL;
    Return l_systimestamp;
END;
/
SELECT get_current_time() FROM DUAL;

-- 2

create or replace function get_media_type_len
RETURN INT is l int;
BEGIN 
    Select LENGTH(name)
    INTO l
    FROM MEDIATYPE
    WHERE name = 'Protected MPEG-4 video file';
    RETURN l;
END;
/
SELECT get_media_type_len() FROM DUAL;


--3.2
-- 1
CREATE OR REPLACE FUNCTION get_invoice_avg
RETURN Number is average Number(3,2);
BEGIN 
    Select AVG(total)
    INTO average
    FROM invoice;
    RETURN average;
END;
/
SELECT get_invoice_avg FROM DUAL;


-- 2
CREATE or REPLACE FUNCTION get_mostexp_track
RETURN NUMBER is mx Number(3,2);
BEGIN
    SELECT MAX(unitprice)
    INTO mx
    FROM Track;
    RETURN mx;
END;
/

SELECT get_mostexp_track() FROM DUAL;

--3.3
CREATE OR REPLACE FUNCTION get_avginvoicelineitems
RETURN NUMBER is average NUMBER(3,2);
BEGIN
    SELECT AVG(unitprice)
    INTO average
    FROM Invoiceline;
    RETURN average;
END;
/

SELECT get_avginvoicelineitems() FROM DUAL;

--3.4 

-- 4
-- 1 
CREATE OR REPLACE PROCEDURE get_name_of_employee(cursorParam OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN cursorParam FOR
    SELECT firstname,lastname
    FROM employee;
END;
/

VARIABLE rc REFCURSOR;
EXECUTE get_name_of_employee(:rc);
print rc;

-- 4.2
-- 1
CREATE OR REPLACE PROCEDURE set_employee
IS 
BEGIN
    UPDATE employee
    SET firstname = 'Taiwooo', lastname = 'Virgo'
    WHERE firstname = 'Taiwan2';
END;
/

EXECUTE set_employee();

-- 2
CREATE OR REPLACE PROCEDURE get_managers(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR
    SELECT a.firstname, a.lastname, a.reportsto, b.firstname, b.lastname
    FROM employee a
    JOIN employee b
    ON a.reportsto = b.employeeid
    WHERE a.firstname = 'Nancy';
END;
/

variable n REFCURSOR;
EXECUTE get_managers(:n);
print n;

--4.3
create or replace procedure get_companyname(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR
    SELECT firstname, lastname, company
    FROM CUSTOMER
    WHERE firstname = 'Taiwan' AND lastname = 'Savage';
END;
/

variable a REFCURSOR;
EXECUTE get_companyname(:a);
print a;

--5
--1
CREATE or Replace procedure randomName (cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    DELETE FROM Invoice
    WHERE invoiceid = 316;
    
    OPEN cursorParam FOR
    SELECT *
    FROM INVOICE
    WHERE invoiceid = 316;

Rollback;
END;
/

variable a REFCURSOR;
EXECUTE randomName(:i);
print i;

-- 2
CREATE or replace procedure insertnew
AS
BEGIN
    INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'AnotherTaiwan','StillSavage','Revature','13000 Revature Way','Reston','VA','USA', 17389, 9999999,99999999,'tai@fake.com'
    ,5);
    COMMIT;
END;
/

EXECUTE insertnew();

--6
--6.1
create or replace TRIGGER Trigger1 
BEFORE INSERT OF customerid
ON CUSTOMER 
FOR EACH ROW
BEGIN
INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'tester','test','Revature','13000 Revature Way','Reston','VA','USA',999999999, 99999999, 99999999, 'tai@fake.com'
    ,2);
END;
/

INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'lazy','lazy','Revature','13000 Revature Way','Reston','VA','USA',999,99,99,'fake@tai.com'
    ,5);
--6.2


--6.3



--7
--7.1
SELECT firstname,lastname, invoiceid
FROM Customer
INNER JOIN INVOICE 
ON Customer.customerid = Invoice.customerid;

--7.2
SELECT Customer.Customerid, firstname, lastname, invoiceid
FROM CUSTOMER
LEFT JOIN Invoice 
ON customer.customerid = Invoice.customerid;

--7.3
SELECT name, title
FROM ALBUM
RIGHT JOIN Artist
on ALBUM.artistid = Artist.ARTISTID;

--7.4
SELECT *
FROM ALBUM
CROSS JOIN ARTIST 
Order BY name ASC;

--7.5
SELECT a.FIRSTNAME, a.LASTNAME, a.reportsto, b.FIRSTNAME, b.LASTNAME
FROM employee a
JOIN employee b
ON a.reportsto = b.employeeid;
