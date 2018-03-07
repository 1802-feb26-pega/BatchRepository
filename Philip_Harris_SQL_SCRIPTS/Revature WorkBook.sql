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
    VALUES(100,'Philip','Harris','Revature','13000 Revature Way','Reston','VA','USA',13579,1234567890,0987654321,'pjh@yahoo.com'
    ,5);
    
     INSERT INTO CUSTOMER (CUSTOMERID,FIRSTNAME,LASTNAME,COMPANY,ADDRESS,CITY,state,country,postalcode,phone,fax,email,supportrepid)
    VALUES(101,'2Philip','2Harris','Revature','13000 Revature Way','Reston','VA','USA',13579,1234560000,0000654321,'harrisphilip@yahoo.com'
    ,2);
    
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


--Part 2