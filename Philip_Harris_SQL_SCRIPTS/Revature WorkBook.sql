--PART 1
--1.0 DONE
--2.1 SELECT
--2.1.1
   SELECT * 
   FROM EMPLOYEE;
--2.1.2
   SELECT * 
   FROM employee
   WHERE LASTNAME = 'King';
--2.1.3
    Select * 
    FROM employee
    Where FIRSTNAME = 'Andrew' 
    AND
    REPORTSTO is NULL;
    
--2.2 ORDER BY
--2.2.1
    SELECT * 
    FROM ALBUM
    ORDER BY TITLE DESC;
    
--2.2.2
    SELECT firstname 
    FROM customer
    ORDER BY city;
    
--2.3 INSERT INTO
--2.3.1
    INSERT INTO GENRE (genreid,name)
    VALUES (26, 'Dubstep');
    
    INSERT INTO GENRE (genreid,name)
    VALUES (27, 'Anime Rock');
    
    Select * 
    FROM GENRE;
    
--2.3.2
    INSERT INTO EMPLOYEE (employeeid,lastname,firstname,title,reportsto,birthdate,hiredate,address,city,state,country,postalcode,
    phone,fax,email)
    VALUES(1992,'Harris','Philip','Dr.',NULL,DATE '1992-04-05', DATE '2018-02-26','13000 Revature Way',
    'Reston','VA','USA', 13579,7572772149,7572770000,'philip.harrisjr@yahoo.com');
    
    INSERT INTO EMPLOYEE (employeeid,lastname,firstname,title,reportsto,birthdate,hiredate,address,city,state,country,postalcode,
    phone,fax,email)
    VALUES(2991,'Harris2','Philip2','Mr.',NULL,DATE '1990-12-25', DATE '2018-02-26','13000 Revature Way',
    'Reston','VA','USA', 13579,7572770000,7572772149,'pharrisjr@yahoo.com');
    
    Select * 
    FROM employee;
    
--2.3.3
    INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'Phil4ip','Harr4is','Revature','13000 Revature Way','Reston','VA','USA',13579,1234567890,0987654321,'pjh@yahoo.com'
    ,5);
    
     INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'3Philip','3Harris','Revature','13000 Revature Way','Reston','VA','USA',13579,1234560000,0000654321,'harrisphilip@yahoo.com'
    ,2);
    
    commit;
    
    SELECT *
    FROM CUSTOMER;
    
--2.4 UPDATE
--2.4.1
    UPDATE CUSTOMER
    SET firstname = 'Robert', lastname = 'Walter'
    WHERE firstname = 'Aaron' AND lastname = 'Mitchell';
    
    Select *
    FROM customer;

--2.4.2
    UPDATE ARTIST
    SET name = 'CCR'
    WHERE name='Creedence Clearwater Revival';
    
    SELECT *
    FROM ARTIST
    WHERE name 
    LIKE 'C%';
    
--2.5 LIKE
--2.5.1
    SELECT *
    FROM invoice
    where BILLINGADDRESS
    LIKE 'T%';
    
--2.6 BETWEEN
--2.6.1
    SELECT *
    FROM invoice
    WHERE total
    BETWEEN 15
    AND
    50;
    
--2.6.2
    SELECT * 
    FROM employee
    WHERE hiredate 
    BETWEEN DATE '2003-06-01' 
    AND
    DATE '2004-03-01';
    
--2.7 DELETE
--2.7.1
ALTER TABLE InvoiceLine DROP CONSTRAINT FK_InvoiceLineInvoiceId;
ALTER TABLE Invoice DROP CONSTRAINT FK_INVOICE_CUSTOMERID;

ALTER TABLE InvoiceLine ADD CONSTRAINT FK_InvoiceLine_InvoiceId
    FOREIGN KEY (InvoiceId) REFERENCES Invoice (InvoiceId) ON DELETE CASCADE ;
    

ALTER TABLE Invoice ADD CONSTRAINT FK_Invoice_CustomerId
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId) ON DELETE CASCADE ;

    DELETE 
    FROM CUSTOMER
    WHERE firstname = 'Robert'
    AND
    lastname = 'Walter';
--3.0 SQL Functions
--3.1 System Defined Functions
--3.1.1

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

--3.1.2

create or replace function get_mediatype_len
RETURN INT is l int;
BEGIN 
    Select LENGTH(name)
    INTO l
    FROM MEDIATYPE
    WHERE name = 'Protected MPEG-4 video file';
    RETURN l;
END;
/
SELECT get_mediatype_len() FROM DUAL;


--3.2 System Defiend aggregate functions
--3.2.1
create or replace function get_invoice_avg
RETURN Number is average Number(3,2);
BEGIN 
    Select AVG(total)
    INTO average
    FROM invoice;
    RETURN average;
END;
/
SELECT get_invoice_avg FROM DUAL;


--3.2.2
create or replace function get_most_exp_track
RETURN NUMBER is mx Number(3,2);
BEGIN
    SELECT MAX(unitprice)
    INTO mx
    FROM Track;
    RETURN mx;
END;
/

SELECT get_most_exp_track() FROM DUAL;

--3.3 User defiened Scalar Functions
Create  or replace function get_avg_invoiceline_items
RETURN NUMBER is average NUMBER(3,2);
BEGIN
    SELECT AVG(unitprice)
    INTO average
    FROM Invoiceline;
    RETURN average;
END;
/

SELECT get_avg_invoiceline_items() FROM DUAL;

--3.4 USer Defined Table Valued Functions
--FUNCTIONS DO NOT RETURN MORE THAN ONE RECORD
Create or Replace function get_before_1968
RETURN int is idx int;
BEGIN
    SELECT Count(Firstname)
    INTO idx
    FROM employee
    WHERE BIRTHDATE > DATE '1968-01-01';
    RETURN idx;
END;
/
SELECT get_before_1968() FROM DUAL;

--4.0 STORED PRODEDURES
--4.1 
create or replace PROCEDURE get_nameof_employee(cursorParam OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN cursorParam FOR
    SELECT firstname,lastname
    FROM employee;
END;
/

variable rc REFCURSOR;
EXECUTE get_nameof_employee(:rc);
print rc;

--4.2
--4.2.1
create or replace PROCEDURE set_employee
IS 
BEGIN
    UPDATE employee
    SET firstname = 'Jonny', lastname = 'Quest'
    WHERE firstname = 'Philip2';
END;
/

EXECUTE set_employee();

--4.2.2
create or replace procedure get_managers(cursorParam OUT SYS_REFCURSOR)
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

variable a REFCURSOR;
EXECUTE get_managers(:a);
print a;

--4.3
create or replace procedure get_companyname(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR
    SELECT firstname, lastname, company
    FROM CUSTOMER
    WHERE firstname = '2Philip' AND lastname = '2Harris';
END;
/

variable a REFCURSOR;
EXECUTE get_companyname(:a);
print a;

--5.0 Transactions
--5.1.1
CREATE or Replace procedure blah (cursorParam OUT SYS_REFCURSOR)
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
EXECUTE blah(:a);
print a;

--5.1.2
CREATE or replace procedure ins_new
AS
BEGIN
    INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'theGREATESTPhilip','theGREATESTHarris','Revature','13000 Revature Way','Reston','VA','USA',13579,1234560000,0000654321,'harrisphilip@yahoo.com'
    ,2);
    COMMIT;
END;
/

EXECUTE ins_new();

--6.1
create or replace TRIGGER Q6_1 
BEFORE INSERT OF customerid
ON CUSTOMER 
FOR EACH ROW
BEGIN
INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'test','test','Revature','13000 Revature Way','Reston','VA','USA',13579,1234560000,0000654321,'harrisphilip@yahoo.com'
    ,2);
END;
/

INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(CUSTOMER_SEQ.nextval,'GREATEST','GREATEST','Revature','13000 Revature Way','Reston','VA','USA',13579,1234560000,0000654321,'harrisphilip@yahoo.com'
    ,2);
--6.2
create or replace TRIGGER Q6_2
BEFORE UPDATE ON CUSTOMER 
BEGIN
dbms_output.put_line('IT Works when it wants too'); 
END;
/

--6.3
create or replace TRIGGER Q6_3 
BEFORE DELETE ON CUSTOMER 
BEGIN
dbms_output.put_line('IT Works when it wants too'); 
END;
/


--7.0
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



--Part 2