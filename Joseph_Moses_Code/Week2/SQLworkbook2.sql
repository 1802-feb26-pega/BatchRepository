--2.1 SELECT

--Task – Select all records from the Employee table.
SELECT *
FROM Employee;

--Task – Select all records from the Employee table where last name is King.
SELECT *
FROM Employee
WHERE lastname = 'King';

--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT *
FROM Employee
WHERE 
    (
    firstname = 'Andrew'
    AND reportsto is NULL
    );

--2.2 ORDER BY

--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT *
FROM album
ORDER BY title DESC;

--Task – Select first name from Customer and sort result set in ascending order by city
SELECT firstname
FROM customer
ORDER BY city;

--2.3 INSERT INTO

--Task – Insert two new records into Genre table
INSERT INTO Genre (GenreId, Name) VALUES (26, 'Country');
INSERT INTO Genre (GenreId, Name) VALUES (27, 'Swing');

--Task – Insert two new records into Employee table
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (9, 'Moses', 'Joseph', 'General Manager', TO_DATE('1992-06-29 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2010-05-15 00:00:00','yyyy-mm-dd hh24:mi:ss'), '621 Sunset Dr', 'Fergus Falls', 'MN', 'United States', '56537', '+1 (218) 205-8559', '+1 (218) 300-6000', 'joseph.k.moses@chinookcorp.com');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (10, 'Hanson', 'Andrew', 'Sales Manager', 1, TO_DATE('1991-08-16 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2010-05-16 00:00:00','yyyy-mm-dd hh24:mi:ss'), '1313 Cottenwood Ln', 'Fergus Falls', 'MN', 'United States', '56537', '+1 (218) 736-0141', '+1 (218) 300-6000', 'andrew.hanson@chinookcorp.com');


--Task – Insert two new records into Customer table
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) VALUES (60, 'Alex', 'Johnson', '156 Beech', 'Duluth', 'MN', 'United States', '56890', '+1 (631) 284-3333', 'ajohn@gmail.com', 5);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) VALUES (61, 'Bethany', 'Lund', '3123 34th St S', 'Moorhead', 'MN', 'United States', '56560', '+1 (218) 564-2345', 'bethany.lund@gmail.com', 5);

--2.4 UPDATE

--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE Customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE
    (
    firstname ='Aaron' 
    AND lastname = 'Mitchell'
    );

--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE Artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

--2.5 LIKE

--Task – Select all invoices with a billing address like “T%”
SELECT *
FROM invoice
WHERE billingaddress LIKE 'T%';

--2.6 BETWEEN

--Task – Select all invoices that have a total between 15 and 50
SELECT *
FROM invoice
WHERE total BETWEEN 15 AND 50;

--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT *
FROM employee
WHERE hiredate BETWEEN DATE '2003-06-01' AND DATE '2004-03-01';

--2.7 DELETE

--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
ALTER TABLE invoice
DROP CONSTRAINT fk_invoiceCustomerId;

ALTER TABLE invoice
ADD CONSTRAINT fk_invoinceCustomerId
FOREIGN KEY (customerId) REFERENCES customer(customerId) ON DELETE CASCADE;

ALTER TABLE invoiceline
DROP CONSTRAINT fk_invoiceLineInvoiceId;

ALTER TABLE invoiceline
ADD CONSTRAINT fk_invoinceLineInvoiceId
FOREIGN KEY (invoiceId) REFERENCES invoice(invoiceId) ON DELETE CASCADE;

DELETE FROM customer
WHERE
    (
    firstname = 'Robert'
    AND lastname = 'Walter'
    );