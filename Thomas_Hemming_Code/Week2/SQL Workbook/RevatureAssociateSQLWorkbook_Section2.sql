/* 2.1 SELECT
Task – Select all records from the Employee table.
Task – Select all records from the Employee table where last name is King.
Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL. */

SELECT * 
FROM employee;

SELECT * 
FROM employee
WHERE lastname = 'King';

SELECT * 
from employee
where firstname = 'Andrew' AND reportsto IS NULL;



/* 2.2 ORDER BY
Task – Select all albums in Album table and sort result set in descending order by title.
Task – Select first name from Customer and sort result set in ascending order by city */
SELECT * 
FROM album
ORDER BY title DESC;

SELECT firstname 
FROM customer
ORDER BY city;

/* 2.3 INSERT INTO
Task – Insert two new records into Genre table
Task – Insert two new records into Employee table
Task – Insert two new records into Customer table */
INSERT INTO genre
VALUES (26, 'Movies');

INSERT INTO genre
VALUES (27, 'Action');

SELECT * 
FROM employee;

INSERT INTO employee
VALUES (9, 'Hemming', 'Thomas', 'Software Developer', null, '03-MAR-94', '06-MAY-05', '8025 Paseo Del Ocaso', 'San Diego', 'CA', 'United States', '92037', '+1 (858-926-8411)', null, 'hemming.thomas@gmail.com');

INSERT INTO employee
VALUES (10, 'Person', 'Guy', 'Sales Support Agent', null, '08-MAY-70', '06-JUL-05', '6894 Address Street', 'Vancouver', 'BC', 'CANADA', 'V5H Z37', '+1 (605) 538-3942', null, 'person.guy@gmail.com');

SELECT *
FROM customer;

INSERT INTO customer
VALUES (60, 'Person', 'Guy', null, '6894 Address Street', 'Vancouver', 'BC', 'CANADA', 'V5H Z37', '+1 (605) 538-3942', null, 'person.guy@gmail.com', 4);

INSERT INTO customer
VALUES (61, 'Guy', 'Person', null, '6894 Address Street', 'Vancouver', 'BC', 'CANADA', 'V5H Z37', '+1 (605) 538-3942', null, 'guy.person@gmail.com', 4);


/* 2.4 UPDATE
Task – Update Aaron Mitchell in Customer table to Robert Walter
Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR” */

SELECT *
FROM customer;

UPDATE customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

SELECT *
FROM artist
WHERE name = 'Creedence Clearwater Revival';

UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

SELECT *
FROM artist
WHERE name = 'CCR';


/* 2.5 LIKE
Task – Select all invoices with a billing address like “T%” */

SELECT *
FROM invoice
WHERE billingaddress LIKE 'T%';

/* 2.6 BETWEEN
Task – Select all invoices that have a total between 15 and 50
Task – Select all employees hired between 1st of June 2003 and 1st of March 2004 */

SELECT *
FROM invoice
WHERE total BETWEEN 15 AND 50;

SELECT * 
FROM employee
WHERE hiredate BETWEEN DATE '2003-06-01' AND DATE '2004-03-01';

/* 2.7 DELETE
Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).*/

/*  does not work due to pk/fk constraints
DELETE FROM customer
WHERE firstname = 'Robert' AND lastname = 'Walter'; */ 

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
WHERE firstname = 'Robert' AND lastname = 'Walter';


